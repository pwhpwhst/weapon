//#define __PRINT_SYMBOL
#define __PRINT_F_FIRST
//#define __PRINT_F_FOLLOW
//#define __PRINT_FORECAST
//#define __PRINT_GRAPH
#define __PRINT_NODE_TREE

#include<iostream>
#include<set>
#include<vector>
#include<deque>
#include<string>
#include<unordered_map>
#include<boost/algorithm/string.hpp>
#include <fstream>

#include"Item.h"
#include"Rule.h"
#include"Node.h"
#include"Lex_Word.h"
using namespace std;
using namespace boost;

void parse_all_symbol(set<string> &terminator,set<string> &non_terminator,const vector<P_Rule> &ruleList);

void get_items_list_and_convert_map(vector<vector<P_Item>> &items_list,unordered_map<int,unordered_map<string,int>> &convert_map,
	const set<string> &non_terminator,const vector<P_Rule> &ruleList,const string start_symbol);

void calculate_f_first(unordered_map<string,set<string>> &f_first,const vector<P_Rule> &ruleList,const set<string> &terminator,const set<string> &non_terminator);

void calculate_f_follow(unordered_map<string,set<string>> &f_follow, unordered_map<string,set<string>> &f_first,
	const vector<P_Rule> &ruleList,const set<string> &non_terminator,const set<string> &terminator,string start_symbol);

void calculate_forecast_list(vector<unordered_map<string,string>> &forecast_list,
	const vector<vector<P_Item>> &items_list,const set<string> &terminator, unordered_map<P_Rule,int> &rule_map,
	 unordered_map<int,unordered_map<string,int>> &convert_map, unordered_map<string,set<string>> &f_follow);

void printStack(Node* &node_tree);

void printGraph(vector<vector<P_Item>> items_list,
unordered_map<int,unordered_map<string,int>> convert_map);

Node* syntax_analyze(const vector<P_Rule> &ruleList, vector<unordered_map<string,string>> &forecast_list,
	unordered_map<int,unordered_map<string,int>> &convert_map,vector<P_Lex_Word> &input);

bool detect_ambigulous( vector<unordered_map<string,string>> &forecast_list,
 const vector<P_Rule> &ruleList,const vector<vector<P_Item>> items_list);


int main(){
//初始化
string start_symbol="ele_begin";
string rule_file="D:\\Users\\Administrator\\Desktop\\project2018.3_2018.9\\2018.3~2018.9\\编译原理实验\\微型编译器\\rule.txt";
string compile_file="D:\\Users\\Administrator\\Desktop\\project2018.3_2018.9\\2018.3~2018.9\\编译原理实验\\微型编译器\\test.txt";


//生成ruleListing
vector<P_Rule> ruleList;
ifstream input_file;
input_file.open(rule_file.data());
string rule_str;
while(getline(input_file,rule_str))
{
	if(rule_str[0]=='='){
		break;
	}
	ruleList.push_back(P_Rule(new Rule(rule_str)));
}
unordered_map<string,string> temp_forecast_map;
vector <string> string_list;
while(getline(input_file,rule_str))
{
	string_list.clear();
	split(string_list,rule_str,is_any_of(":"));
	temp_forecast_map[string_list[0]]=string_list[1];
}


input_file.close();
unordered_map<P_Rule,int> rule_map;
for(int i1=0;i1<ruleList.size();i1++){
	rule_map[ruleList[i1]]=i1;
}


//生成输入
vector<P_Lex_Word>  lex_word_list;
lex_word_list.clear();
word_parser(compile_file,lex_word_list);


//划出所有的终结符号和非终结符号
set<string> terminator;
set<string> non_terminator;
parse_all_symbol(terminator,non_terminator,ruleList);



//计算first函数
unordered_map<string,set<string>> f_first;
calculate_f_first(f_first,ruleList,terminator,non_terminator);

//计算follow函数
unordered_map<string,set<string>> f_follow;
calculate_f_follow(f_follow, f_first,ruleList,non_terminator,terminator, start_symbol);


//构建 LR（0）算法的状态机
vector<vector<P_Item>> items_list;
unordered_map<int,unordered_map<string,int>> convert_map;
get_items_list_and_convert_map(items_list,convert_map,non_terminator,ruleList,start_symbol);


//构建预测表
vector<unordered_map<string,string>> forecast_list;
calculate_forecast_list(forecast_list,items_list,terminator,rule_map,convert_map,f_follow);

for(const auto &e:temp_forecast_map){
	string_list.clear();
	split(string_list,e.first,is_any_of(","));
	forecast_list[atoi(string_list[0].c_str())][string_list[1]]=e.second;
}


if(detect_ambigulous(forecast_list,ruleList,items_list)){
	return -1;
}


//构造语法树
/**
Node *node_tree=syntax_analyze(ruleList,forecast_list,convert_map,lex_word_list);
#ifdef __PRINT_NODE_TREE
printStack(node_tree);
#endif
*/
}


void calculate_f_first(unordered_map<string,set<string>> &f_first,const vector<P_Rule> &ruleList,
	const set<string> &terminator,const set<string> &non_terminator){
// 计算first函数

set<P_Rule> has_calculate_first_set;
vector<P_Rule> rule_stack;
set<string> symbol_temp_set;
set<P_Rule> in_stack_rules;
for(auto &e:ruleList){
	if(has_calculate_first_set.count(e)==0){
		rule_stack.push_back(e);
		in_stack_rules.insert(e);
		while(rule_stack.size()>0){
			P_Rule rule=rule_stack.back();
			if(terminator.count(rule->symbols[0])>0){
				rule->first.push_back(rule->symbols[0]);
				has_calculate_first_set.insert(rule);
				rule_stack.pop_back();
				in_stack_rules.erase(rule);
				continue;
			}
				bool has_calculate=true;
				P_Rule un_calculate_rule=nullptr;
				symbol_temp_set.clear();

				for(auto &e2:ruleList){
					if(in_stack_rules.count(e2)==0&&e2->rule_name==rule->symbols[0]){
						if(has_calculate_first_set.count(e2)==0){
							has_calculate=false;
							un_calculate_rule=e2;
							break;
						}else{
							for(const auto &e3:e2->first){
								symbol_temp_set.insert(e3);
							}
						}
					}
				}

				if(has_calculate){
					for(const auto &e3:symbol_temp_set){
						rule->first.push_back(e3);
					}
					has_calculate_first_set.insert(rule);
					rule_stack.pop_back();
					in_stack_rules.erase(rule);
				}else{
					if(in_stack_rules.count(un_calculate_rule)==0){
						rule_stack.push_back(un_calculate_rule);
						in_stack_rules.insert(un_calculate_rule);
					}

				}

		}

	}
}


for(auto rule:ruleList){
	f_first[rule->rule_name]=set<string>();
}

for(auto rule:ruleList){
	for(auto s:rule->first){
		f_first[rule->rule_name].insert(s);
	}
}


#ifdef __PRINT_F_FIRST
cout<<"f_first"<<endl;
for(const auto result:f_first){
	cout<<result.first<<":"<<endl;
	for(auto it=result.second.begin();it!=result.second.end();++it){
		cout<<*it<<",";
	}
	cout<<endl;
}
for(const auto &e:non_terminator){
	if(f_first.find(e)==f_first.end()){
		cout<<"不存在:"<<e<<endl;
	}
}
#endif
}


bool detect_ambigulous( vector<unordered_map<string,string>> &forecast_list,
	 const vector<P_Rule> &ruleList,const vector<vector<P_Item>> items_list){
bool flag=false;
set<int> item_set;
set<int> rule_set;
	for(int i1=0;i1<forecast_list.size();i1++){
		const auto e1=forecast_list[i1];
		for(const auto &e2:e1){
			if(e2.second.find(",")!=string::npos){
				flag=true;
				cout<<"存在二义性:"<<i1<<","<<e2.first<<":"<<e2.second<<endl;
				item_set.insert(i1);
				vector <string> string_list;
				split(string_list,e2.second,is_any_of(","));
				for(const auto &e:string_list){
					if(e[0]=='s'){
						item_set.insert(atoi(e.substr(1).c_str()));
					}else{
						rule_set.insert(atoi(e.substr(1).c_str()));
					}
				}
				
			}
		}
	}

	if(flag){
		cout<<"打印转移状态图的点"<<endl;
		for(const int &i1:item_set){
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

		cout<<"打印文法:"<<endl;
		for(const int &i1:rule_set){
			auto &rule=ruleList[i1];
			cout<<i1<<":"<<endl;
			cout<<rule->rule_name;
			cout<<" :";
			for(auto e2:rule->symbols){
				cout<<" "<<e2;
			}
			cout<<endl;
		}

	}

	return flag;
}

void calculate_f_follow(unordered_map<string,set<string>> &f_follow, unordered_map<string,set<string>> &f_first,
	const vector<P_Rule> &ruleList,const set<string> &non_terminator,const set<string> &terminator,string start_symbol){

for(const auto &e:non_terminator){
	f_follow[e]=set<string>();
}

f_follow[start_symbol].insert("'$'");

for(const auto &e:ruleList){
	for(int i1=0;i1<e->symbols.size();i1++){
		if(non_terminator.count(e->symbols[i1])>0&&i1!=e->symbols.size()-1){
			if(non_terminator.count(e->symbols[i1+1])>0){
				for(const auto &e2:f_first[e->symbols[i1+1]]){
					f_follow[e->symbols[i1]].insert(e2);
				}
			}else{
				f_follow[e->symbols[i1]].insert(e->symbols[i1+1]);
			}
		}
	}
}

set<string> has_calculate_follow_set;
vector<P_Rule> rule_stack;
set<P_Rule> in_stack_rules;
set<string> symbol_temp_set;

for(const auto &e:ruleList){
	if(has_calculate_follow_set.count(e->symbols.back())==0){
		rule_stack.push_back(e);
		in_stack_rules.insert(e);
		while(rule_stack.size()>0){
			P_Rule rule=rule_stack.back();


			if(terminator.count(rule->symbols.back())>0){
				rule_stack.pop_back();
				in_stack_rules.erase(rule);
				continue;
			}

			if(has_calculate_follow_set.count(rule->rule_name)>0){
				for(const auto &e3:f_follow[rule->rule_name]){
					f_follow[rule->symbols.back()].insert(e3);
				}
				rule_stack.pop_back();
				in_stack_rules.erase(rule);
			}else{
				
				bool has_calculate=true;
				P_Rule un_calculate_rule=nullptr;
				symbol_temp_set.clear();
				for(const auto &e2:ruleList){
					if(in_stack_rules.count(e2)==0&&e2->symbols.back()==rule->rule_name){
						if(has_calculate_follow_set.count(e2->rule_name)==0){
							has_calculate=false;
							un_calculate_rule=e2;
							break;
						}else{
							for(const auto &e3:f_follow[e2->rule_name]){
								symbol_temp_set.insert(e3);
							}
						}
					}
				}

				if(has_calculate){
					for(const auto &e3:symbol_temp_set){
						f_follow[rule->rule_name].insert(e3);
					}
					has_calculate_follow_set.insert(rule->rule_name);
				}else{
					if(in_stack_rules.count(un_calculate_rule)==0){
						rule_stack.push_back(un_calculate_rule);
						in_stack_rules.insert(un_calculate_rule);
					}

				}
			}
		}
	}
}

#ifdef __PRINT_F_FOLLOW
cout<<"f_follow"<<endl;
for(const auto result:f_follow){
	cout<<result.first<<":"<<endl;
	for(auto it=result.second.begin();it!=result.second.end();++it){
		cout<<*it<<",";
	}
	cout<<endl;
}
#endif

}


void printGraph(vector<vector<P_Item>> items_list,
unordered_map<int,unordered_map<string,int>> convert_map){
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
}


void printStack(Node* &node_tree){
	cout<<"深度优先遍历:"<<endl;
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
			cout<<"content="<<present_node->content<<endl;
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


void parse_all_symbol(set<string> &terminator,set<string> &non_terminator,const vector<P_Rule> &ruleList){
	for(auto rule:ruleList){
		for(auto s:rule->symbols){
			if(s[0]=='\''){
				terminator.insert(s);
			}else if(s!="0"){
				non_terminator.insert(s);
			}
		}
	}
	terminator.insert("'$'");


#ifdef __PRINT_SYMBOL
cout<<"终端符号："<<endl;
for(const auto &e:terminator){
	cout<<e<<endl;
}
cout<<endl;

cout<<"非终端符号："<<endl;
for(const auto &e:non_terminator){
	cout<<e<<endl;
}
cout<<endl;
#endif


}


void get_items_list_and_convert_map(vector<vector<P_Item>> &items_list,unordered_map<int,unordered_map<string,int>> &convert_map,
	const set<string> &non_terminator,const vector<P_Rule> &ruleList,const string start_symbol){
	
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

	vector<P_Item> items0;
	items_list.push_back(items0);

	//构造item0
	deque<string> rule_name_deq;
	set<string> rule_name_set;
	set<P_Item,P_Item_Cmp> _items_set;
	set<P_Item,P_Item_Cmp> _visited_items_set;


	P_Item _p_item(new Item(ruleList[0],0));
	items_list[0].push_back(_p_item);
	_items_set.insert(_p_item);
	_visited_items_set.insert(_p_item);
	rule_name_set.insert(items_list[0][0]->rule->symbols[items_list[0][0]->status]);

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
				items_list[0].push_back(_p_item);
				_items_set.insert(_p_item);
				_visited_items_set.insert(_p_item);
			}
		}
	}
	rule_name_set.clear();


	set<int> status_set;
	deque<int> status_que;
	status_que.push_front(0);


	set<string> move_symbol_set;

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
		_items_set.clear();
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
					_visited_items_set.insert(_p_item);
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
					_visited_items_set.insert(_p_item);
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
				convert_map[items_list_index]["'$'"]=-1;
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

_items_set.clear();
P_Item p_search_item(new Item());
for(const auto &e:ruleList){
	p_search_item->rule=e;
	for(int i1=0;i1<=e->symbols.size();i1++){
		p_search_item->status=i1;
		if(_visited_items_set.count(p_search_item)==0){
			_items_set.insert(P_Item(new Item(*(p_search_item.get()))));
		}
	}
}

if(_items_set.size()>0){
	cout<<"存在自由rule:"<<endl;
	int i1=0;
	for(const auto &e:_items_set){
		cout<<i1<<":"<<endl;
		cout<<e->rule->rule_name;
		cout<<" :";
		for(auto e2:e->rule->symbols){
			cout<<" "<<e2;
		}
		cout<<" "<<e->status<<endl;
		i1++;
	}
	throw;
}


#ifdef __PRINT_GRAPH
printGraph(items_list,convert_map);
#endif

}



void calculate_forecast_list(vector<unordered_map<string,string>> &forecast_list,
	const vector<vector<P_Item>> &items_list,const set<string> &terminator, unordered_map<P_Rule,int> &rule_map,
	 unordered_map<int,unordered_map<string,int>> &convert_map, unordered_map<string,set<string>> &f_follow){
for(int i1=0;i1<items_list.size();i1++){
	unordered_map<string,string> _map;
	for(auto &e1:terminator){
		string s="";
		string r="";
		if(convert_map[i1].find(e1)!=convert_map[i1].end()){
			s="s"+to_string(convert_map[i1][e1]);
		}
		
		for(const auto &e2:items_list[i1]){
			if(e2->status==e2->rule->symbols.size()&&f_follow[e2->rule->rule_name].count(e1)>0){
				if(r==""){
					r="r"+to_string(rule_map[e2->rule]);
				}else{
					r+=",r"+to_string(rule_map[e2->rule]);
				}
			}
		}

		if(s==""){
			s=r;
		}else if(r!=""){
			s+=","+r;
		}
		if(s!=""){
			if(s=="s-1,r0"){
				s="acc";
			}
			_map[e1]=s;
		}
	}
	forecast_list.push_back(_map);
}

#ifdef __PRINT_FORECAST
for(int i1=0;i1<forecast_list.size();i1++){
	cout<<i1<<":"<<endl;
	for(const auto &e:forecast_list[i1]){
		cout<<e.first<<":"<<e.second<<endl;
	}
	cout<<endl;
}
#endif
}

Node* syntax_analyze(const vector<P_Rule> &ruleList, vector<unordered_map<string,string>> &forecast_list,
	unordered_map<int,unordered_map<string,int>> &convert_map,vector<P_Lex_Word> &input){

struct ItemNode{
Node *node;
int item_status;
};

typedef std::shared_ptr<ItemNode> P_ItemNode;

vector<P_ItemNode> item_node_stack1;
 item_node_stack1.push_back(P_ItemNode(new ItemNode()));
 item_node_stack1[0]->node=nullptr;
 item_node_stack1[0]->item_status=0;
Node* resultTree=nullptr;
bool finished_flag=false;
auto p_input=input.begin();

while(!finished_flag){

	P_ItemNode top_item=item_node_stack1.back();

	string input_type;
	if(p_input!=input.end()){
		input_type=(*p_input)->type;
	}else{
		input_type="'$'";
	}

	string action=forecast_list[top_item->item_status][input_type];

	if(action=="acc"){
		resultTree=top_item->node;
		finished_flag=true;
	}else if(action[0]=='s'){
//		cout<<"A1"<<endl;
		item_node_stack1.push_back(P_ItemNode(new ItemNode()));
		Node *node=new Node();
		node->symbol=(*p_input)->type;
		node->content=(*p_input)->content;
		node->is_first_child=false;
		node->parent=nullptr;
		node->l_node=nullptr;
		node->r_node=nullptr;
		item_node_stack1.back()->node=node;
		item_node_stack1.back()->item_status=atoi(action.substr(1).c_str());
		++p_input;
	}else if(action[0]=='r'){
		P_Rule best_rule=ruleList[atoi(action.substr(1).c_str())];
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
			item_node_stack1.pop_back();
		}

		top_item=item_node_stack1.back();
		item_node_stack1.push_back(P_ItemNode(new ItemNode()));
		item_node_stack1.back()->node=parent_node;
		item_node_stack1.back()->item_status=convert_map[top_item->item_status][parent_node->symbol];
	}

}

	return resultTree;
}