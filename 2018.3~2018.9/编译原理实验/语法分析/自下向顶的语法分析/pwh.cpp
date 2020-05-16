#include<iostream>
#include<set>
#include<vector>
#include<deque>
#include<string>
#include<unordered_map>
#include<boost/algorithm/string.hpp>

#include"Item.h"
#include"Rule.h"
#include"Node.h"
using namespace std;
using namespace boost;



Node* syntax_analyze(const vector<P_Rule> &ruleList,string start_symbol,string input);
void printStack(Node* &node_tree);

int main(){

//初始化
string rule_strs[]={
	"ele_begin : ele",
	"ele : ele '+' term",
	"ele : term",
	"term : term '*' factor",
	"term : factor",
	"factor : '(' ele ')'",
	"factor : 'id'"
	};

string start_symbol="ele_begin";
string input="'id' '*' '(' 'id' '+' 'id' ')' $";



//生成ruleListing
vector<P_Rule> ruleList;
for(auto rule_str:rule_strs){
	ruleList.push_back(P_Rule(new Rule(rule_str)));
}

Node* node_tree=syntax_analyze(ruleList,start_symbol,input);

cout<<"深度优先遍历:"<<endl;
printStack(node_tree);
cout<<"dasfasfs"<<endl;
}

void printStack(Node* &node_tree){
	vector<Node*> item_node_stack2;
	item_node_stack2.push_back(node_tree);
	set<Node*> node_set;
	while(item_node_stack2.size()>0){
		Node *present_node=item_node_stack2.back();
		if(present_node->l_node!=nullptr&&node_set.count(present_node->l_node)==0){
			item_node_stack2.push_back(present_node->l_node);
		}else{
			node_set.insert(present_node);
			cout<<present_node<<":"<<endl;
			cout<<"symbol="<<present_node->symbol<<endl;
			cout<<"is_first_child="<<present_node->is_first_child<<endl;
			cout<<"parent="<<present_node->parent<<endl;
			cout<<"l_node="<<present_node->l_node<<endl;
			cout<<"r_node="<<present_node->r_node<<endl;
			cout<<endl;
			item_node_stack2.pop_back();
			if(present_node->r_node!=nullptr){
				item_node_stack2.push_back(present_node->r_node);
			}
		}
	}
}


Node* syntax_analyze(const vector<P_Rule> &ruleList,string start_symbol,string input){
	//划出所有的终结符号和非终结符号
set<string> terminator;
set<string> non_terminator;
for(auto rule:ruleList){
	for(auto s:rule->symbols){
		if(s[0]=='\''){
			terminator.insert(s);
		}else if(s!="0"){
			non_terminator.insert(s);
		}
	}
}
terminator.insert("$");




vector<vector<P_Item>> items_list;
unordered_map<int,unordered_map<string,int>> convert_map;
vector<P_Item> items0;
items_list.push_back(items0);

for(auto e:ruleList){
	items_list[0].push_back(P_Item(new Item(e,0)));
}




set<int> status_set;
deque<int> status_que;
status_que.push_front(0);

deque<string> rule_name_deq;
set<string> rule_name_set;
set<string> move_symbol_set;
class P_Item_Cmp
{
		public : bool operator ()(const P_Item &c1,const P_Item &c2) const{
		if(c1->rule<c2->rule){
			return true;
		}else if(c2->rule<c1->rule){
			return false;
		}else{
			if(c1->status<c2->status){
				return true;
			}else{
				return false;
			}
		}

	}
};

//构造状态转移图
while(status_que.size()>0){
	int status_number=status_que.back();
	//收集所有移动符号
	for(P_Item e:items_list[status_number]){
		if(e->rule->symbols.size()>e->status){
			move_symbol_set.insert(e->rule->symbols[e->status]);
		}
	}

	for(string symble:move_symbol_set){
		vector<P_Item> _items;
		set<P_Item,P_Item_Cmp> _items_set;
		bool is_final_item=false;
		for(P_Item e:items_list[status_number]){
			if(e->rule->symbols.size()>e->status){
				if(e->rule->symbols[e->status]==symble){
					P_Item _p_item(new Item(e->rule,e->status+1));
					if(_p_item->rule->rule_name==start_symbol&&_p_item->status==1){
						is_final_item=true;
					}
					_items.push_back(_p_item);
					_items_set.insert(_p_item);
				}
			}
		}


		for(auto e:_items){
			if(e->rule->symbols.size()>e->status){
			rule_name_set.insert(e->rule->symbols[e->status]);
			}
		}
		for(auto e:rule_name_set){
			rule_name_deq.push_front(e);
		}


		while(rule_name_deq.size()>0){
			string rule_name=rule_name_deq.back();
			for(auto e:ruleList){
				if(e->rule_name==rule_name){
						if(non_terminator.count(e->symbols[0])>0&&rule_name_set.count(e->symbols[0])==0){
							rule_name_set.insert(e->symbols[0]);
							rule_name_deq.push_front(e->symbols[0]);
						}
				}
			}
			rule_name_deq.pop_back();
		}

		for(auto e:ruleList){
			if(rule_name_set.count(e->rule_name)>0){
				P_Item _p_item(new Item(e,0));

				if(_items_set.count(_p_item)==0){
					_items.push_back(_p_item);
					_items_set.insert(_p_item);
				}
			}
		}

		bool isExist=false;
		int items_list_index=-1;
		for(int i1=0;i1<items_list.size();i1++){
			if(items_list[i1].size()==_items.size()){
				bool flag=true;
				for(auto e:items_list[i1]){
					if(_items_set.count(e)==0){
						flag=false;
						break;
					}
				}
				if(flag){
					items_list_index=i1;
					isExist=true;
					break;
				}
			}
		}
		if(!isExist){
			items_list.push_back(_items);
			status_que.push_front(items_list.size()-1);
			items_list_index=items_list.size()-1;
			
			if(is_final_item){
				if(convert_map.find(items_list_index)==convert_map.end()){
					convert_map[items_list_index]=unordered_map<string,int>();
				}
				convert_map[items_list_index]["$"]=-1;
			}
		}

		if(convert_map.find(status_number)==convert_map.end()){
			convert_map[status_number]=unordered_map<string,int>();
		}
		convert_map[status_number][symble]=items_list_index;


		rule_name_deq.clear();
		rule_name_set.clear();
	}

	move_symbol_set.clear();
	status_que.pop_back();
}


cout<<"打印转移状态图的点"<<endl;

for(int i1=0;i1<items_list.size();i1++){
	cout<<i1<<":"<<endl;
	for(auto e:items_list[i1]){
		cout<<e->rule->rule_name;
		cout<<" :";
		for(auto e2:e->rule->symbols){
			cout<<" "<<e2;
		}
		cout<<" "<<e->status<<endl;
	}
}

cout<<"打印转移状态图的弧"<<endl;


for(const auto result:convert_map){
	cout<<result.first<<":"<<endl;
	for(auto it=result.second.begin();it!=result.second.end();++it){
		cout<<it->first<<":"<<it->second<<endl;
	}
	cout<<endl;
}




vector<string> input_arr;
split(input_arr,input,is_any_of(" "));

struct ItemNode{
Node *node;
int item_status;
};

typedef std::shared_ptr<ItemNode> P_ItemNode;

vector<P_ItemNode> item_node_stack1;
vector<Node*> item_node_stack2;

for(int i1=(input_arr.size()-1);i1>=0;i1--){
	Node *node=new Node();
	node->symbol=input_arr[i1];
	node->is_first_child=false;
	node->parent=nullptr;
	node->l_node=nullptr;
	node->r_node=nullptr;
	item_node_stack2.push_back(node);
}

 item_node_stack1.push_back(P_ItemNode(new ItemNode()));
 item_node_stack1[0]->node=nullptr;
 item_node_stack1[0]->item_status=0;

bool finished_flag=false;
Node* resultTree=nullptr;
while(!finished_flag){
	P_ItemNode top_item=item_node_stack1.back();


	Node *input_node=nullptr;
	if(item_node_stack2.size()>0){
		input_node=item_node_stack2.back();
	}


	if(item_node_stack2.size()==1 &&input_node->symbol==start_symbol){
		resultTree=input_node;
		item_node_stack2.pop_back();
		finished_flag=true;
	}else if(input_node!=nullptr&&convert_map[top_item->item_status].find(input_node->symbol)!=convert_map[top_item->item_status].end()){
		if(input_node->symbol!="$"){
			item_node_stack1.push_back(P_ItemNode(new ItemNode()));
			item_node_stack1.back()->node=input_node;
			item_node_stack1.back()->item_status=convert_map[top_item->item_status][input_node->symbol];
			cout<<"压入:"<<input_node->symbol<<endl;
		}
		item_node_stack2.pop_back();
	}else{
		int length=-1;
		P_Rule best_rule=nullptr;
		for(P_Rule e:ruleList){
			if((((int)e->symbols.size())-length)>=0&&top_item->node->symbol==e->symbols.back()&&e->symbols.size()<=(item_node_stack1.size()-1)){
				bool match_flag=true;
				for(int i1=(e->symbols.size()-1),i2=item_node_stack1.size()-1;i1>=0;i1--,i2--){
					if(e->symbols[i1]!=item_node_stack1[i2]->node->symbol){
						match_flag=false;
						break;
					}
				}	
				if(match_flag){
					length=(int)e->symbols.size();
					best_rule=e;
				}
			}
		}
		cout<<"找规约rule:"<<best_rule->rule_name<<endl;

		Node *parent_node=new Node();
		parent_node->symbol=best_rule->rule_name;
		parent_node->is_first_child=false;
		parent_node->parent=nullptr;
		for(int i1=item_node_stack1.size()-best_rule->symbols.size();i1<item_node_stack1.size();i1++){
			item_node_stack1[i1]->node->parent=parent_node;
			if((i1+1)<item_node_stack1.size()){
				item_node_stack1[i1]->node->r_node=item_node_stack1[i1+1]->node;
			}else{
				item_node_stack1[i1]->node->r_node=nullptr;
			}
			item_node_stack1[i1]->node->is_first_child=false;
		}
		parent_node->l_node=item_node_stack1[item_node_stack1.size()-best_rule->symbols.size()]->node;
		item_node_stack1[item_node_stack1.size()-best_rule->symbols.size()]->node->is_first_child=true;
		parent_node->r_node=nullptr;

		for(int i1=0;i1<best_rule->symbols.size();i1++){
			cout<<"弹出:"<<item_node_stack1.back()->node->symbol<<endl;
			item_node_stack1.pop_back();
		}

		item_node_stack2.push_back(parent_node);

	}

}
	return resultTree;
}
