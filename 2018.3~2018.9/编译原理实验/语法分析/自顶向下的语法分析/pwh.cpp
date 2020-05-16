#include <iostream>
#include<string>
#include<vector>
#include<bitset>
#include<set>
#include<unordered_map>
#include<boost/algorithm/string.hpp>
#include "Rule.h"
#include "Extra.h"
#include "Node.h"

using namespace std;
using namespace boost;


void calFirst(set<string>& result,
const vector<string>& input,
 unordered_map<string,set<string>>& f_first,
 unordered_map<string,set<string>>& f_follow);


int main(){
//初始化
string rule_strs[]={
	"ele : term ele1",
	"ele1 : '+' term ele1",
	"ele1 : 0",
	"term : factor term1",
	"term1 : '*' factor term1",
	"term1 : 0",
	"factor : '(' ele ')'",
	"factor : 'id'"
	};

string start_symbol="ele";

vector<P_Rule> ruleList;

for(auto rule_str:rule_strs){
	ruleList.push_back(P_Rule(new Rule(rule_str)));
}


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


//计算first函数
vector<P_Rule> stack;
unordered_map<P_Rule,int> visited_map;

for(auto rule:ruleList){
	visited_map[rule]=0;
}


for(int i1=0;i1<ruleList.size();i1++){
	if(visited_map[ruleList[i1]]!=1){
		if(ruleList[i1]->symbols.size()>0){
			stack.push_back(ruleList[i1]);
			while(stack.size()>0){
				P_Rule rule = stack.back();
				if((rule->symbols)[0][0]!='\''&&(rule->symbols)[0][0]!='0'){

					bool is_confirmed=false;
					for(int i2=0;i2<ruleList.size();i2++){
						if(ruleList[i2]->rule_name==(rule->symbols)[0]){
							if(visited_map[ruleList[i2]]==1){
								for(int i3=0;i3<ruleList[i2]->first.size();i3++){
									rule->first.push_back((ruleList[i2]->first)[i3]);
								}
								is_confirmed=true;

							}else{
								stack.push_back(ruleList[i2]);
							}
						}
					}
					if(is_confirmed){
						stack.pop_back();
						visited_map[rule]=1;
					}

				}else{
					rule->first.push_back((rule->symbols)[0]);
					stack.pop_back();
					visited_map[rule]=1;
				}
			}
			
		}else{
			visited_map[ruleList[i1]]=1;
		}
		
	}
}



unordered_map<string,set<string>> f_first;
for(auto rule:ruleList){
	f_first[rule->rule_name]=set<string>();
}

for(auto rule:ruleList){
	for(auto s:rule->first){
		f_first[rule->rule_name].insert(s);
	}
}

/**
cout<<"f_first"<<endl;
for(const auto result:f_first){
	cout<<result.first<<":"<<endl;
	for(auto it=result.second.begin();it!=result.second.end();++it){
		cout<<*it<<",";
	}
	cout<<endl;
	
}
*/




//计算fellow函数

unordered_map<string,set<string>> f_follow;
set<string> rule_name_set;

for(auto it=ruleList.begin();it!=ruleList.end();++it){
	rule_name_set.insert((*it)->rule_name);
	f_follow[(*it)->rule_name]=set<string>();
}

f_follow[start_symbol].insert("$");

for(auto it=rule_name_set.begin();it!=rule_name_set.end();++it){
	for(int i1=0;i1<ruleList.size();i1++){
		for(int i2=0;i2<(ruleList[i1]->symbols).size();i2++){
			if((ruleList[i1]->symbols)[i2]==*it){
				if((i2+1)<(ruleList[i1]->symbols).size()){
					if((ruleList[i1]->symbols)[i2+1][0]!='\''&&(ruleList[i1]->symbols)[i2+1][0]!='0'){
						for(auto it2=f_first[(ruleList[i1]->symbols)[i2+1]].begin();it2!=f_first[(ruleList[i1]->symbols)[i2+1]].end();++it2){
							if(*it2!="0"){
								f_follow[*it].insert(*it2);
							}
						}
					}else{
						if((ruleList[i1]->symbols)[i2+1][0]!='0'){
							f_follow[*it].insert((ruleList[i1]->symbols)[i2+1]);						
						}
					}
				}
			}
		}
	}
}






vector<string> stack2;
unordered_map<string,int> follow_map;
for(auto rule:ruleList){
	if(!((rule->symbols).size()==1&&(rule->symbols)[0]=="0")){
		int index;
		for(int i2=(rule->symbols).size()-1;i2>=0;i2--){
			if((rule->symbols)[i2][0]=='0'
				||(rule->symbols)[i2][0]!='0'&&(rule->symbols)[i2][0]!='\''&&f_first[(rule->symbols)[i2]].find("0")!=f_first[(rule->symbols)[i2]].end()){
			}else{
				index=i2;
				break;
			}
		}

		for(int i1=index;i1<(rule->symbols).size();i1++){
			if((rule->symbols)[i1][0]!='0'&&(rule->symbols)[i1][0]!='\''&&(rule->symbols)[i1]!=rule->rule_name){
				follow_map[rule->rule_name+","+(rule->symbols)[i1]]=0;
			}
		}
	}
}


vector <string> str_temp;
for(auto results2_item:f_follow){
		for(auto s:follow_map){
			if(endsWith(s.first,","+results2_item.first)==1&&s.second==0){
				stack2.push_back(s.first);
			}
		}

		while(stack2.size()>0){
			str_temp.clear();
			split(str_temp,stack2.back(),is_any_of(","));

			bool is_pop=true;
			for(auto s:follow_map){
				if(endsWith(s.first,","+str_temp[0])&&s.second==0){
					stack2.push_back(s.first);
					is_pop =false;
				}
			}

			if(is_pop){
				follow_map[stack2.back()]=1;
				stack2.pop_back();
				for(auto it=f_follow[str_temp[0]].begin();it!=f_follow[str_temp[0]].end();++it){
					f_follow[str_temp[1]].insert(*it);
				}
			}

		}

}

/**
cout<<"f_follow"<<endl;
for(const auto result:f_follow){
	cout<<result.first<<":"<<endl;
	for(auto it=result.second.begin();it!=result.second.end();++it){
		cout<<*it<<",";
	}
	cout<<endl;
}
*/

//计算预测表

unordered_map<string,unordered_map<string,vector<P_Rule>>> forecast_map;

for(auto it=non_terminator.begin();it!=non_terminator.end();++it){
	forecast_map[*it]=unordered_map<string,vector<P_Rule>>();
	for(auto it2=terminator.begin();it2!=terminator.end();++it2){
		forecast_map[*it][*it2]=vector<P_Rule>();
	}
}


set<string> temp_result;
for(auto rule:ruleList){
	temp_result.clear();
	calFirst(temp_result,rule->symbols,f_first,f_follow);
	bool is_contain_empty=false;
	for(auto it=temp_result.begin();it!=temp_result.end();++it){
		if(*it!="0"){
			forecast_map[rule->rule_name][*it].push_back(rule);
		}else{
			is_contain_empty=true;
		}
	}

	if(is_contain_empty){
		for(auto follow_symbol:f_follow[rule->rule_name]){
			forecast_map[rule->rule_name][follow_symbol].push_back(rule);
		}
	}
}

/**
for(auto item1:forecast_map){
	cout<<item1.first<<":"<<endl;
	for(auto item2:item1.second){
		if(item2.second.size()>0){
			cout<<item2.first<<":";
			cout<<item2.second[0]->rule_name<<" ";
			for(auto symbol:item2.second[0]->symbols){
				cout<<symbol<<" ";
			}
			cout<<endl;
		}
	}
}
*/

bool is_ambiguous=false;
for(auto item1:forecast_map){
	for(auto item2:item1.second){
		if(item2.second.size()>1){
			is_ambiguous=true;
			break;
		}
	}
	if(is_ambiguous){
		break;
	}
}

if(is_ambiguous){
	cout<<"二义性语法"<<endl;
}else{
	cout<<"非二义性语法"<<endl;
}


//生成语法树
// unordered_map<string,unordered_map<string,vector<P_Rule>>> forecast_map;
string symbols="'id' '+' 'id' '*' '(' 'id' '+' 'id' ')' $";

vector <string> symbol_list;
split(symbol_list,symbols,is_any_of(" "));


Node *root=new Node();
root->symbol="ele";
vector<Node*> node_stack;
cout<<"pushA:"<<root->symbol<<endl;
node_stack.push_back(root);

unordered_map<Node*,int> node_confirmed_map;

bool is_success=true;
auto it=symbol_list.begin();

	while(it!=symbol_list.end()&&node_stack.size()>0){
		Node *node=node_stack.back();
		if((node->symbol)[0]=='\''){
			if(node->symbol!=*it){
				is_success=false;
				break;
			}
			node_confirmed_map[node]=1;
			cout<<"popB:"<<node->symbol<<endl;
			node_stack.pop_back();
			++it;
		}else if((node->symbol)=="0"){
			node_confirmed_map[node]=1;
			cout<<"popB:"<<node->symbol<<endl;
			node_stack.pop_back();
		}else{
			if(node->l_node!=nullptr){
				Node *p=node->l_node;
				while(p!=nullptr){
					if(node_confirmed_map[p]==1){
						p=p->r_node;
					}else{
						break;
					}
				}
				if(p!=nullptr){
					cout<<"pushB:"<<p->symbol<<endl;
					node_stack.push_back(p);
				}else{
					node_confirmed_map[node]=1;
					cout<<"popB:"<<node->symbol<<endl;
					node_stack.pop_back();
				}
			}else if(forecast_map[node->symbol][*it].size()>0){
				Node *pre_node;
				for(auto it2=forecast_map[node->symbol][*it][0]->symbols.begin();it2!=forecast_map[node->symbol][*it][0]->symbols.end();++it2){
					Node *_node=new Node();
					_node->symbol=*it2;
					if(it2==forecast_map[node->symbol][*it][0]->symbols.begin()){
						_node->is_first_child=true;
						_node->parent=node;
						_node->l_node=nullptr;
						_node->r_node=nullptr;
						node->l_node=_node;
						cout<<"pushB:"<<_node->symbol<<endl;
						node_stack.push_back(_node);
					}else{
						_node->is_first_child=false;
						_node->parent=pre_node;
						_node->l_node=nullptr;
						_node->r_node=nullptr;
						pre_node->r_node=_node;
					}
					pre_node=_node;
				}
			}else{
				is_success=false;
				break;
			}
			
		} 
	}

if(is_success){
	cout<<"匹配成功"<<endl;
}else{
	cout<<"匹配失败"<<endl;
}










}


void calFirst(set<string>& result,
const vector<string>& input,
 unordered_map<string,set<string>>& f_first,
 unordered_map<string,set<string>>& f_follow){

	decltype(input.begin()) last_it;
	for( auto  it=input.begin();it!=input.end();++it){
		last_it=it;
		if((*it)[0]=='\''){
			result.insert(*it);
			return;
		}else if(*it=="0"){
			result.insert(*it);
		}else{
			
			bool is_contain_empty=false;
			for( auto  it2=f_first[*it].begin();it2!=f_first[*it].end();++it2){
				if(*it2!="0"){
					result.insert(*it2);
				}else{
					is_contain_empty=true;
				}
			}
			if(!is_contain_empty){
				break;
			}
		}
	}

if(*last_it!="0"&&f_first[*last_it].find("0")!=f_first[*last_it].end()){
	result.insert("0");
}

}