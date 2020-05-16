#include<iostream>
#include<set>
#include<vector>
#include<deque>
#include<string>
#include<unordered_map>
#include<boost/algorithm/string.hpp>

#include"Rule.h"

using namespace std;
using namespace boost;

struct SymbolCmp{
string symbol;
int depth;
bool is_defined;
};

typedef std::shared_ptr<SymbolCmp> P_SymbolCmp;

void parse_all_symbol(set<string> &terminator,set<string> &non_terminator,set<string> &defined_terminator,const vector<P_Rule> &ruleList);
bool pair_compare(P_SymbolCmp a1,P_SymbolCmp a2);
int main(){

//初始化
string rule_strs[]={
"primary-expression : literal",
"primary-expression : 'this'",
"primary-expression : '(' expression ')'",
"primary-expression : id-expression",
"id-expression : unqualified-id",
"id-expression : qualified-id",
"unqualified-id : identifier",
"unqualified-id : operator-function-id",
"unqualified-id : conversion-function-id",
"unqualified-id : '~' class-name",
"unqualified-id : template-id",
"qualified-id : nested-name-specifier unqualified-id",
"qualified-id : '::' nested-name-specifier unqualified-id",
"qualified-id : nested-name-specifier 'template' unqualified-id",
"qualified-id : '::' nested-name-specifier 'template' unqualified-id",
"qualified-id : '::' identifier",
"qualified-id : '::' operator-function-id",
"qualified-id : '::' template-id",
"nested-name-specifier : class-or-namespace-name '::'",
"nested-name-specifier : class-or-namespace-name '::' nested-name-specifier",
"nested-name-specifier : class-or-namespace-name '::' 'template' nested-name-specifier",
"class-or-namespace-name : class-name",
"class-or-namespace-name : namespace-name",
"postfix-expression : primary-expression",
"postfix-expression : postfix-expression '[' expression ']'",
"postfix-expression : postfix-expression '(' ')'",
"postfix-expression : postfix-expression '(' expression-list ')'",
"postfix-expression : simple-type-specifier '(' ')'",
"postfix-expression : simple-type-specifier '(' expression-list ')'",
"postfix-expression : 'typename' nested-name-specifier identifier '(' ')'",
"postfix-expression : 'typename' nested-name-specifier identifier '(' expression-list ')'",
"postfix-expression : 'typename' nested-name-specifier template-id '(' ')'",
"postfix-expression : 'typename' nested-name-specifier template-id '(' expression-list ')'",
"postfix-expression : 'typename' nested-name-specifier 'template' template-id '(' ')'",
"postfix-expression : 'typename' nested-name-specifier 'template' template-id '(' expression-list ')'",
"postfix-expression : 'typename' '::' nested-name-specifier identifier '(' ')'",
"postfix-expression : 'typename' '::' nested-name-specifier identifier '(' expression-list ')'",
"postfix-expression : 'typename' '::' nested-name-specifier template-id '(' ')'",
"postfix-expression : 'typename' '::' nested-name-specifier template-id '(' expression-list ')'",
"postfix-expression : 'typename' '::' nested-name-specifier 'template' template-id '(' ')'",
"postfix-expression : 'typename' '::' nested-name-specifier 'template' template-id '(' expression-list ')'",
"postfix-expression : postfix-expression '.' id-expression",
"postfix-expression : postfix-expression '->' id-expression",
"postfix-expression : postfix-expression '.' 'template' id-expression",
"postfix-expression : postfix-expression '->' 'template' id-expression",
"postfix-expression : postfix-expression '.' pseudo-destructor-name",
"postfix-expression : postfix-expression '->' pseudo-destructor-name",
"postfix-expression : postfix-expression '++'",
"postfix-expression : postfix-expression '--'",
"postfix-expression : 'dynamic_cast' '<' type-id '>' '(' expression ')'",
"postfix-expression : 'static_cast' '<' type-id '>' '(' expression ')'",
"postfix-expression : 'reinterpret_cast' '<' type-id '>' '(' expression ')'",
"postfix-expression : 'const_cast' '<' type-id '>' '(' expression ')'",
"postfix-expression : 'typeid' '(' expression ')'",
"postfix-expression : 'typeid' '(' type-id ')'",
"expression-list : assignment-expression",
"expression-list : expression-list ',' assignment-expression",
"pseudo-destructor-name : type-name '::' '~' type-name",
"pseudo-destructor-name : nested-name-specifier type-name '::' '~' type-name",
"pseudo-destructor-name : nested-name-specifier 'template' template-id '::' '~' type-name",
"pseudo-destructor-name : '~' type-name",
"pseudo-destructor-name : nested-name-specifier '~' type-name",
"pseudo-destructor-name : '::' type-name '::' '~' type-name",
"pseudo-destructor-name : '::' nested-name-specifier type-name '::' '~' type-name",
"pseudo-destructor-name : '::' nested-name-specifier 'template' template-id '::' '~' type-name",
"pseudo-destructor-name : '::' '~' type-name",
"pseudo-destructor-name : '::' nested-name-specifier '~' type-name",
"unary-expression : postfix-expression",
"unary-expression : '++' cast-expression",
"unary-expression : '--' cast-expression",
"unary-expression : unary-operator cast-expression",
"unary-expression : 'sizeof' unary-expression",
"unary-expression : 'sizeof' '(' type-id ')'",
"unary-expression : new-expression",
"unary-expression : delete-expression",
"unary-operator : '*'",
"unary-operator : '&'",
"unary-operator : '+'",
"unary-operator : '-'",
"unary-operator : '!'",
"unary-operator : '~'",
"new-expression : 'new' new-type-id",
"new-expression : 'new' '(' type-id ')'",
"new-expression : '::' 'new' new-type-id",
"new-expression : '::' 'new' '(' type-id ')'",
"new-expression : 'new' new-placement new-type-id",
"new-expression : 'new' new-placement '(' type-id ')'",
"new-expression : '::' 'new' new-placement new-type-id",
"new-expression : '::' 'new' new-placement '(' type-id ')'",
"new-expression : 'new' new-type-id new-initializer",
"new-expression : 'new' '(' type-id ')' new-initializer",
"new-expression : '::' 'new' new-type-id new-initializer",
"new-expression : '::' 'new' '(' type-id ')' new-initializer",
"new-expression : 'new' new-placement new-type-id new-initializer",
"new-expression : 'new' new-placement '(' type-id ')' new-initializer",
"new-expression : '::' 'new' new-placement new-type-id new-initializer",
"new-expression : '::' 'new' new-placement '(' type-id ')' new-initializer",
"new-placement : '(' expression-list ')'",
"new-type-id : type-specifier-seq",
"new-type-id : type-specifier-seq new-declarator",
"new-declarator : ptr-operator",
"new-declarator : ptr-operator new-declarator",
"new-declarator : direct-new-declarator",
"direct-new-declarator : '[' expression ']'",
"direct-new-declarator : direct-new-declarator '[' constant-expression ']'",
"new-initializer : '(' ')'",
"new-initializer : '(' expression-list ')'",
"delete-expression : 'delete' cast-expression",
"delete-expression : 'delete' '[' ']' cast-expression",
"delete-expression : '::' 'delete' cast-expression",
"delete-expression : '::' 'delete' '[' ']' cast-expression",
"cast-expression : unary-expression",
"cast-expression : '(' type-id ')' cast-expression",
"pm-expression : cast-expression",
"pm-expression : pm-expression '.*' cast-expression",
"pm-expression : pm-expression '->*' cast-expression",
"multiplicative-expression : pm-expression",
"multiplicative-expression : multiplicative-expression '*' pm-expression",
"multiplicative-expression : multiplicative-expression '/' pm-expression",
"multiplicative-expression : multiplicative-expression '%' pm-expression",
"additive-expression : multiplicative-expression",
"additive-expression : additive-expression '+' multiplicative-expression",
"additive-expression : additive-expression '-' multiplicative-expression",
"shift-expression : additive-expression",
"shift-expression : shift-expression '<<' additive-expression",
"shift-expression : shift-expression '>>' additive-expression",
"relational-expression : shift-expression",
"relational-expression : relational-expression '<' shift-expression",
"relational-expression : relational-expression '>' shift-expression",
"relational-expression : relational-expression '<=' shift-expression",
"relational-expression : relational-expression '>=' shift-expression",
"equality-expression : relational-expression",
"equality-expression : equality-expression '==' relational-expression",
"equality-expression : equality-expression '!=' relational-expression",
"and-expression : equality-expression",
"and-expression : and-expression '&' equality-expression",
"exclusive-or-expression : and-expression",
"exclusive-or-expression : exclusive-or-expression '^' and-expression",
"inclusive-or-expression : exclusive-or-expression",
"inclusive-or-expression : inclusive-or-expression '|' exclusive-or-expression",
"logical-and-expression : inclusive-or-expression",
"logical-and-expression : logical-and-expression '&&' inclusive-or-expression",
"logical-or-expression : logical-and-expression",
"logical-or-expression : logical-or-expression '||' logical-and-expression",
"conditional-expression : logical-or-expression",
"conditional-expression : logical-or-expression '?' expression ':' assignment-expression",
"assignment-expression : conditional-expression",
"assignment-expression : logical-or-expression assignment-operator assignment-expression",
"assignment-expression : throw-expression",
"assignment-operator : '='",
"assignment-operator : '*='",
"assignment-operator : '/='",
"assignment-operator : '%='",
"assignment-operator : '+='",
"assignment-operator : '-='",
"assignment-operator : '>>='",
"assignment-operator : '<<='",
"assignment-operator : '&='",
"assignment-operator : '^='",
"assignment-operator : '|='",
"expression : assignment-expression",
"expression : expression ',' assignment-expression",
"constant-expression : conditional-expression"
	};


//生成ruleListing
vector<P_Rule> ruleList;
for(auto rule_str:rule_strs){
	ruleList.push_back(P_Rule(new Rule(rule_str)));
}


//划出所有的终结符号和非终结符号
set<string> terminator;
set<string> non_terminator;
set<string> defined_terminator;
parse_all_symbol(terminator,non_terminator,defined_terminator,ruleList);

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

unordered_map<string,int> depth_map;
for(const auto &e:non_terminator){
	depth_map[e]=0;
}



vector<string> stack;
set<string> temp_set;
for(const auto &e1:non_terminator){
	if(depth_map[e1]==0){
		cout<<"插入A:"<<e1<<endl;
		stack.push_back(e1);

		while(stack.size()>0){
			string symbol=stack.back();
			bool is_match=false;
			temp_set.clear();
			for(const auto &e2:ruleList){
				if(symbol==e2->rule_name){
					if(!is_match){
						is_match=true;
					}
					for(const auto &e3:e2->symbols){
						temp_set.insert(e3);
					}
				}else{
					if(is_match){
						break;
					}
				}
			}

			if(temp_set.size()==0){
				depth_map[symbol]=1;
				cout<<"弹出A:"<<symbol<<endl;
				stack.pop_back();
			}else{
				bool is_all_child_confirmed=true;
				string not_confirmed_symbol;
				int max_depth=0;
				for(const auto &e2:temp_set){

					if(non_terminator.count(e2)>0){
						bool is_exist=false;
						for(const auto &e3:stack){
							if(e3==e2){
								is_exist=true;
								break;
							}
						}

						if(is_exist){
							continue;
						}

						if(depth_map[e2]==0){
							not_confirmed_symbol=e2;
							is_all_child_confirmed=false;
							break;
						}else{
							max_depth=depth_map[e2]>max_depth?depth_map[e2]:max_depth;
						}
					}
				}

				if(is_all_child_confirmed){
					depth_map[symbol]=max_depth+1;
					cout<<"弹出B:"<<symbol<<endl;
					stack.pop_back();
				}else{
					cout<<"插入B:"<<not_confirmed_symbol<<endl;
					stack.push_back(not_confirmed_symbol);
				}

			}
		}
	}
}






vector<P_SymbolCmp> result_list;





for(const auto &e:depth_map){
	P_SymbolCmp pSymbolCmp=P_SymbolCmp(new SymbolCmp());
	pSymbolCmp->symbol=e.first;
	pSymbolCmp->depth=e.second;
	if(defined_terminator.count(e.first)>0){
		pSymbolCmp->is_defined=true;
	}else{
		pSymbolCmp->is_defined=false;
	}
	result_list.push_back(pSymbolCmp);
}

sort(result_list.begin(),result_list.end(),pair_compare);

for(const auto &e:result_list){
	cout<<e->symbol<<":"<<e->depth<<","<<e->is_defined<<endl;
}



cout<<"fdsgfdsgfz"<<endl;

}


void parse_all_symbol(set<string> &terminator,set<string> &non_terminator,set<string> &defined_terminator,const vector<P_Rule> &ruleList){
	for(auto rule:ruleList){

		defined_terminator.insert(rule->rule_name);
		if(rule->rule_name[0]=='\''){
			terminator.insert(rule->rule_name);
		}else if(rule->rule_name!="0"){
			non_terminator.insert(rule->rule_name);
		}

		for(auto s:rule->symbols){
			if(s[0]=='\''){
				terminator.insert(s);
			}else if(s!="0"){
				non_terminator.insert(s);
			}
		}
	}
	terminator.insert("$");
}


bool pair_compare(P_SymbolCmp a1,P_SymbolCmp a2){
	if(a1->depth==a2->depth){
		return a1->is_defined<a2->is_defined;
	}else{
		return a1->depth<a2->depth;
	}
	
}