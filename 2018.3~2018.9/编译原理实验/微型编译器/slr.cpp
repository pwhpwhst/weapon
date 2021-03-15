//#define __PRINT_SYMBOL
//#define __PRINT_F_FIRST
//#define __PRINT_F_FOLLOW
//#define __PRINT_FORECAST
//#define __PRINT_GRAPH
//#define __PRINT_LEX_WORD_LIST
#define __PRINT_NODE_TREE

#include "slr.h"
using namespace std;
using namespace boost;



int Slr::startsWith(string s, string sub){
	if(sub.length()>s.length()){
		return 0;
	}
        return s.find(sub)==0?1:0;
}

int Slr::endsWith(string s,string sub){
	if(sub.length()>s.length()){
		return 0;
	}
        return s.rfind(sub)==(s.length()-sub.length())?1:0;
}

void Slr::log(const string& s){
  //  cout<<s<<endl;
}



int Slr::slr(string rule_file,string compile_file,Env& env,CompileInfo &compileInfo){
//初始化
string start_symbol="ele_begin";

//
log("生成ruleListing");
vector<P_Rule> ruleList;
ifstream input_file;
input_file.open(rule_file.data());
string rule_str;
ostringstream sb;
/*
while(getline(input_file,rule_str))
{
	if(rule_str[0]=='='){
		break;
	}
	rule_str= trim_right_copy(rule_str);
	rule_str= trim_left_copy(rule_str);
	if(startsWith(rule_str,"//")==1||startsWith(rule_str,"/*")==1){
		continue;
	}

	sb<<rule_str;
	if(rule_str[rule_str.length()-1]!=';'){
		cout<<sb.str()<<endl;
		ruleList.push_back(P_Rule(new Rule(sb.str())));
		sb.str("");
	}
}
*/
vector<string> orders;
unordered_map<string,string> temp_forecast_map;
paresOrders(rule_file,orders,temp_forecast_map);
	for( const auto &e:orders){
//		cout<<e<<endl;
		ruleList.push_back(P_Rule(new Rule(e)));
	}


vector <string> string_list;





unordered_map<P_Rule,int> rule_map;
for(int i1=0;i1<ruleList.size();i1++){
	rule_map[ruleList[i1]]=i1;
}


//划出所有的终结符号和非终结符号
log("划出所有的终结符号和非终结符号");
set<string> terminator;
set<string> non_terminator;
set<string> zero_terminator;
parse_all_symbol(terminator,non_terminator,zero_terminator,ruleList);


//计算first函数
log("计算first函数");
unordered_map<string,set<string>> f_first;
calculate_f_first(f_first,ruleList,terminator,non_terminator);


//计算follow函数
log("计算follow函数");
unordered_map<string,set<string>> f_follow;
calculate_f_follow(f_follow, f_first,ruleList,non_terminator,terminator, start_symbol);



//构建 LR（0）算法的状态机
vector<vector<P_Item>> items_list;
log("构建 LR（0）算法的状态机");
unordered_map<int,unordered_map<string,int>> convert_map;
get_items_list_and_convert_map(items_list,convert_map,non_terminator,f_first,ruleList,start_symbol);

unordered_map<string,set<int>> r_rule_item_map;
	ostringstream os;
for(int i1=0;i1<items_list.size();i1++){
    const auto &e1=items_list[i1];
    for(const auto &e2:e1){
        if(e2->rule->symbols.size()==e2->status){
            os.str("");
            os<<e2->rule->rule_name<<" : ";
            for(int i2=0;i2<e2->rule->symbols.size();i2++){
                os<<e2->rule->symbols[i2];
                if(i2!=(e2->rule->symbols.size()-1)){
                    os<<" ";
                }
            }
            if(r_rule_item_map.find(os.str())==r_rule_item_map.end()){
                r_rule_item_map[os.str()]=set<int>();
            }
            r_rule_item_map[os.str()].insert(i1);
        }
    }
}

//构建预测表
log("构建预测表");
vector<unordered_map<string,string>> forecast_list;
calculate_forecast_list(forecast_list,items_list,terminator,rule_map,convert_map,f_follow);

//解决移入-规约冲突
log("解决移入-规约冲突");

for(const auto &e:temp_forecast_map){
	string_list.clear();
	split(string_list,e.first,is_any_of("|"));
    string rule_str=string_list[0];
    string move=string_list[1];
	for(const auto &e1:r_rule_item_map[string_list[0]]){
        string method=forecast_list[e1][string_list[1]];
        if(method.find(",")!=string::npos){
            string_list.clear();
            split(string_list,method,is_any_of(","));
            string s,r;

            for(const auto &e2:string_list){
                if(startsWith(e2,"s")){
                    s=e2;
                }else if(startsWith(e2,"r")){
                P_Rule rule=ruleList[atoi(e2.substr(1).c_str())];
                os.str("");
                os<<rule->rule_name<<" : ";
                for(int i2=0;i2<rule->symbols.size();i2++){
                    os<<rule->symbols[i2];
                    if(i2!=(rule->symbols.size()-1)){
                        os<<" ";
                    }
                }
                if(rule_str==os.str()){
                    r=e2;
                }
                }
            }
            if(e.second=="s"){
                forecast_list[e1][move]=s;
            }else if(e.second=="r"){
                forecast_list[e1][move]=r;
            }
        }
	}

}


if(detect_ambigulous(forecast_list,ruleList,items_list)){
	return -1;
}



//定义上下文
log("定义上下文");
int context_id=0;

//生成输入
log("生成输入");
vector<P_Lex_Word>  total_lex_word_list;
total_lex_word_list.clear();
word_parser(compile_file,total_lex_word_list,env);

#ifdef __PRINT_LEX_WORD_LIST
for(const auto &e:total_lex_word_list){
	cout<<"type="<<e->type<<endl;
	cout<<"content="<<e->content<<endl;
	cout<<endl;
}
#endif


//符号表
log("符号表");
int context_offset=0;
//unordered_map<string,P_Symbol> symbol_context;
vector<P_Lex_Word>  lex_word_list;

for(const auto &e:total_lex_word_list){
	lex_word_list.push_back(e);
	if(e->type=="'\n'"){
		//构造语法树
		lex_word_list.pop_back();
		Node *node_tree=syntax_analyze(ruleList,zero_terminator,forecast_list,convert_map,lex_word_list);
		#ifdef __PRINT_NODE_TREE

		if(node_tree!=nullptr){
//			printStack(node_tree);
			printStackTree(node_tree);
		}
        #endif


		if(node_tree!=nullptr){
			gen_middle_code(env,node_tree,compileInfo);
		}



		lex_word_list.clear();
	}
}
}


void Slr::calculate_f_first(unordered_map<string,set<string>> &f_first,const vector<P_Rule> &ruleList,
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



			for(int i1=0;i1<rule->symbols.size();i1++){

				for(auto &e2:ruleList){
						if(in_stack_rules.count(e2)==0&&e2->rule_name==rule->symbols[i1]){
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

				if(!has_calculate){
					break;
				}

				if(symbol_temp_set.count("'0'")>0){
					if(i1!=(rule->symbols.size()-1)){
						symbol_temp_set.erase("'0'");
					}
				}else{
					break;
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


bool Slr::detect_ambigulous( vector<unordered_map<string,string>> &forecast_list,
	 const vector<P_Rule> &ruleList,const vector<vector<P_Item>> items_list){
bool flag=false;
set<int> item_set;
set<int> rule_set;
	for(int i1=0;i1<forecast_list.size();i1++){
		const auto e1=forecast_list[i1];
		for(const auto &e2:e1){
		    if(e2.first=="0"){
                continue;
		    }
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

void Slr::calculate_f_follow(unordered_map<string,set<string>> &f_follow, unordered_map<string,set<string>> &f_first,
	const vector<P_Rule> &ruleList,const set<string> &non_terminator,const set<string> &terminator,string start_symbol){

for(const auto &e:non_terminator){
	f_follow[e]=set<string>();
}

f_follow[start_symbol].insert("'$'");


for(const auto &e:ruleList){
	for(int i1=0;i1<e->symbols.size();i1++){
		if(non_terminator.count(e->symbols[i1])>0&&i1!=e->symbols.size()-1){
			for(int i2=i1+1;i2<e->symbols.size();i2++){
				if(non_terminator.count(e->symbols[i2])>0){
					bool is_contained_zero_symbol=false;
					for(const auto &e2:f_first[e->symbols[i2]]){
						if(e2!="0"){
							f_follow[e->symbols[i1]].insert(e2);
						}else{
							is_contained_zero_symbol=true;
						}
					}
					if(!is_contained_zero_symbol){
						break;
					}
				}else{
					if(e->symbols[i2]!="0"){
						f_follow[e->symbols[i1]].insert(e->symbols[i2]);
						break;
					}
				}
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

				if(has_calculate_follow_set.count(rule->rule_name)>0){

					for(int i1=rule->symbols.size()-1;i1>=0;i1--){
							if(non_terminator.count(rule->symbols[i1])>0){
								for(const auto &e3:f_follow[rule->rule_name]){
									f_follow[rule->symbols[i1]].insert(e3);
								}
							}
							if(rule->symbols[i1]=="0"||non_terminator.count(rule->symbols[i1])>0&&f_first[rule->symbols[i1]].count("0")>0){
							}else{
								break;
							}

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


void Slr::printGraph(vector<vector<P_Item>> items_list,
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


void Slr::printStack(Node* &node_tree){
	cout<<"深度优先遍历:"<<endl;
	vector<Node*> item_node_stack2;
	item_node_stack2.push_back(node_tree);
	set<Node*> node_set;
	while(item_node_stack2.size()>0){
		Node *present_node=item_node_stack2.back();

		if(present_node->child_node_list.size()>0&&node_set.count(present_node->child_node_list[0])==0){
			item_node_stack2.push_back(present_node->child_node_list[0]);
		}else{
			node_set.insert(present_node);
			cout<<present_node<<":"<<endl;
			cout<<"symbol="<<present_node->symbol<<endl;
			cout<<"content="<<present_node->content<<endl;
			cout<<"offset="<<present_node->offset<<endl;
			cout<<"parent="<<present_node->parent<<endl;
			cout<<"child_node_list="<<&(present_node->child_node_list)<<endl;
			cout<<endl;
			item_node_stack2.pop_back();
			if(present_node->parent!=nullptr){
				if((present_node->offset+1)<present_node->parent->child_node_list.size()){
					item_node_stack2.push_back(present_node->parent->child_node_list[present_node->offset+1]);
				}
			}

		}
	}
}


void Slr::printStackTree(Node* &node_tree){
	cout<<"打印语法树:"<<endl;
	deque<Node*> item_node_stack2;
	item_node_stack2.push_back(node_tree);
	set<Node*> node_set;
	unordered_map<string,unordered_map<string,int>> plan_map;
	unordered_map<string,Node*> plan2_map;

	ostringstream os;
	const int padding_left=4;
	const int branch_length=4;

//先用深度优先遍历计算要打印的块的长和高
	while(item_node_stack2.size()>0){
		Node *present_node=item_node_stack2.back();

		if(present_node->child_node_list.size()>0&&node_set.count(present_node->child_node_list[0])==0){
			item_node_stack2.push_back(present_node->child_node_list[0]);
		}else{
			node_set.insert(present_node);
			os.str("");
			os<<present_node;
			string present_str=os.str();
			plan_map[present_str]=unordered_map<string,int>();

			int size1=present_node->symbol.size()+padding_left;
			int size2=0;
			if(present_node->child_node_list.size()>0){
				for(const auto &e:present_node->child_node_list){
					os.str("");
					os<<e;
					size2+=plan_map[os.str()]["size_row"];
				}
			}
			plan_map[present_str]["size_row"]=size1>size2?size1:size2;
			plan2_map[present_str]=present_node;
			item_node_stack2.pop_back();

			size1=1;
			size2=0;
			if(present_node->child_node_list.size()>0){
				for(const auto &e:present_node->child_node_list){
					os.str("");
					os<<e;
					size2=plan_map[os.str()]["size_col"]>size2?plan_map[os.str()]["size_col"]:size2;
				}
				size2+=branch_length;
			}
			plan_map[present_str]["size_col"]=size2>size1?size2:size1;

			if(present_node->parent!=nullptr){
				if((present_node->offset+1)<present_node->parent->child_node_list.size()){
					item_node_stack2.push_back(present_node->parent->child_node_list[present_node->offset+1]);
				}
			}

		}
	}

	item_node_stack2.push_front(node_tree);
	os.str("");
	os<<node_tree;
	plan_map[os.str()]["pos_x"]=0;
	plan_map[os.str()]["pos_y"]=0;
	while(item_node_stack2.size()>0){
		Node *present_node=item_node_stack2.back();
		os.str("");
		os<<present_node;
		string present_str=os.str();
		if(present_node->child_node_list.size()>0){
			int brother_x_offset=0;
			for(const auto &e:present_node->child_node_list){
				os.str("");
				os<<e;
				plan_map[os.str()]["pos_x"]=plan_map[present_str]["pos_x"]+brother_x_offset;
				brother_x_offset+=plan_map[os.str()]["size_row"];
				plan_map[os.str()]["pos_y"]=plan_map[present_str]["pos_y"]+branch_length;
				item_node_stack2.push_front(e);
			}

		}
		item_node_stack2.pop_back();
	}


	vector<vector<char>> output;
	os.str("");
	os<<node_tree;
	for(int i1=0;i1<plan_map[os.str()]["size_col"];i1++){
		 output.push_back(vector<char>());
		for(int i2=0;i2<plan_map[os.str()]["size_row"];i2++){
			output.back().push_back(' ');
		}
	}

	for(auto &e:plan_map){
		for(int i1=0;i1<plan2_map[e.first]->symbol.size();i1++){
			output[e.second["pos_y"]][e.second["pos_x"]+i1]=plan2_map[e.first]->symbol[i1];
		}

		if(plan2_map[e.first]->child_node_list.size()>0){
			output[e.second["pos_y"]+1][e.second["pos_x"]]='|';

				os.str("");
				os<<plan2_map[e.first]->child_node_list[0];
				int beg_pos=plan_map[os.str()]["pos_x"];
				os.str("");
				os<<plan2_map[e.first]->child_node_list.back();
				int end_pos=plan_map[os.str()]["pos_x"];
				for(int i1=beg_pos;i1<=end_pos;i1++){
					output[e.second["pos_y"]+2][i1]='-';
				}

				for(int i1=0;i1<plan2_map[e.first]->child_node_list.size();i1++){
					os.str("");
					os<<plan2_map[e.first]->child_node_list[i1];
					output[e.second["pos_y"]+3][plan_map[os.str()]["pos_x"]]='|';
				}


		}

	}



	for(const auto &e:output){
		for(const auto &e1:e){
			cout<<e1;
		}
		cout<<endl;
	}

}


void Slr::parse_all_symbol(set<string> &terminator,set<string> &non_terminator,set<string> &zero_terminator,const vector<P_Rule> &ruleList){
	for(auto rule:ruleList){
		for(auto s:rule->symbols){
			if(s[0]=='\''){
				terminator.insert(s);
			}else if(s!="0"){
				non_terminator.insert(s);
			}else{
				terminator.insert(s);
			}
		}
		if(rule->symbols.size()==1&&rule->symbols[0]=="0"){
			non_terminator.insert(rule->rule_name);
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


void Slr::get_items_list_and_convert_map(vector<vector<P_Item>> &items_list,unordered_map<int,unordered_map<string,int>> &convert_map,
	const set<string> &non_terminator,unordered_map<string,set<string>> &f_first,const vector<P_Rule> &ruleList,const string start_symbol){

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

	for(const auto &e:ruleList){
		if(e->rule_name==start_symbol){
			P_Item _p_item(new Item(e,0));
			items_list[0].push_back(_p_item);
			_items_set.insert(_p_item);
			_visited_items_set.insert(_p_item);
			rule_name_set.insert(_p_item->rule->symbols[_p_item->status]);
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

			for(int i1=0;i1<=e->symbols.size();i1++){
					P_Item _p_item(new Item(e,i1));
					if(_items_set.count(_p_item)==0){
						items_list[0].push_back(_p_item);
						_items_set.insert(_p_item);
						_visited_items_set.insert(_p_item);
					}
					if(i1!=e->symbols.size()){
						if(e->symbols[i1]=="0"||non_terminator.count(e->symbols[i1])>0&&
							f_first[e->symbols[i1]].count("0")>0){
						}else{
							break;
						}
					}
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
	move_symbol_set.erase("0");

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

				for(int i1=0;i1<=e->symbols.size();i1++){
					P_Item _p_item(new Item(e,i1));
					if(_items_set.count(_p_item)==0){
						_items.push_back(_p_item);
						_items_set.insert(_p_item);
						_visited_items_set.insert(_p_item);
					}
					if(i1!=e->symbols.size()){
						if(e->symbols[i1]=="0"||non_terminator.count(e->symbols[i1])>0&&
							f_first[e->symbols[i1]].count("0")>0){
						}else{
							break;
						}
					}
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



void Slr::calculate_forecast_list(vector<unordered_map<string,string>> &forecast_list,
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
//专门针对 0 begin
string r="";
for(const auto &e2:items_list[i1]){
	if(e2->status==e2->rule->symbols.size()){
		if(r==""){
			r="r"+to_string(rule_map[e2->rule]);
		}else{
			r+=",r"+to_string(rule_map[e2->rule]);
		}
	}
}
if(r!=""){
	_map["0"]=r;
}


//专门针对 0 end
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

Node* Slr::syntax_analyze(const vector<P_Rule> &ruleList,set<string> zero_terminator,vector<unordered_map<string,string>> &forecast_list,
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
		item_node_stack1.push_back(P_ItemNode(new ItemNode()));
		Node *node=new Node();
		node->symbol=(*p_input)->type;
		node->content=(*p_input)->content;
		node->parent=nullptr;
		node->offset=0;
		item_node_stack1.back()->node=node;
		item_node_stack1.back()->item_status=atoi(action.substr(1).c_str());
		++p_input;
	}else if(action[0]=='r'){
		P_Rule best_rule=ruleList[atoi(action.substr(1).c_str())];
		Node *parent_node=new Node();
		parent_node->symbol=best_rule->rule_name;
		parent_node->offset=0;
		parent_node->parent=nullptr;

		int zero_count=0;
		for(const auto &e:best_rule->symbols){
			if(e=="0"){
				zero_count++;
			}
		}

		Node *pre_child_node=nullptr;
		if(best_rule->symbols[0]=="0"){
			pre_child_node=new Node();
			pre_child_node->symbol="0";
			pre_child_node->content="";
			pre_child_node->offset=0;
		}else{
			pre_child_node=item_node_stack1[item_node_stack1.size()-best_rule->symbols.size()+zero_count]->node;
		}
		pre_child_node->parent=parent_node;
		parent_node->child_node_list.push_back(pre_child_node);

		if(best_rule->symbols.size()>1){
			for(int i1=1,i2=item_node_stack1.size()-best_rule->symbols.size()+zero_count+1;i1<best_rule->symbols.size();i1++){
				Node *present_child_node=nullptr;
				if(best_rule->symbols[i1]=="0"){
					present_child_node=new Node();
					present_child_node->symbol="0";
					present_child_node->content="";
				}else{
					present_child_node=item_node_stack1[i2]->node;
					i2++;
				}
				present_child_node->parent=parent_node;
				present_child_node->offset=i1;
				parent_node->child_node_list.push_back(present_child_node);
			}
		}

		for(int i1=best_rule->symbols.size()-1;i1>=0;i1--){
			if(best_rule->symbols[i1]=="0"||zero_terminator.count(best_rule->symbols[i1])>0){

			}else{
				item_node_stack1.pop_back();
			}
		}

		top_item=item_node_stack1.back();
		item_node_stack1.push_back(P_ItemNode(new ItemNode()));
		item_node_stack1.back()->node=parent_node;
		item_node_stack1.back()->item_status=convert_map[top_item->item_status][parent_node->symbol];
	}else{
		cout<<"遇到意外输入:"<<"item_status:"<<top_item->item_status<<",input_type:"<<input_type<<endl;
		break;
	}

}
	return resultTree;
}



void Slr::gen_middle_code(Env &env,Node* &node_tree,CompileInfo &compileInfo){

	cout<<"生成中间代码:"<<endl;

	set<string> has_calculate_set;
	unordered_map<string,Token*> result_map; //这个起到类似上下文的作用
	vector<P_NodeValue> stack;
	stack.push_back(P_NodeValue(new NodeValue(node_tree,NodeValue::SYN)));

	while(stack.size()>0){
			auto top=stack.back();

			//cout<<top->node->get_rule_str()<<endl;
			P_SDT_genertor sdt_genertor=SDT_Factory::instance.factory[top->node->get_rule_str()];

			if(sdt_genertor!=nullptr){

				P_NodeValue p_nodeValue=sdt_genertor->handle(top,result_map,has_calculate_set,env,compileInfo);
				if(compileInfo.errInfo!=""){
                    break;
				}
				if(p_nodeValue!=nullptr){
					stack.push_back(p_nodeValue);
				}else{
					stack.pop_back();
				}
			}else{
				break;
			}
	}
	for(auto &e:result_map){
		if(e.second!=nullptr){
			delete e.second;
		}
	}
}



void Slr::paresOrders(const string& rule_file,vector<string>& orders,unordered_map<string,string> &temp_forecast_map){
	ifstream input_file;
	input_file.open(rule_file.data());
	string rule_str;
    ostringstream os;
    int status=6;
	int bracketsCount=0;
	vector <string> string_list;
    string left;
	string right;
    unordered_map<string,string> order_map;

 while(getline(input_file,rule_str)){
		if(rule_str[0]=='='){
			break;
		}
		if(rule_str[0]=='-'){
			continue;
		}
		rule_str=trim_left_copy(trim_right_copy(rule_str));


		if(status==6){
			if(startsWith(rule_str,"//")==1){
				status=5;break;
			}else if(startsWith(rule_str,"/*")==1&&endsWith(rule_str,"*/")==1){
				status=5;break;
			}else if(startsWith(rule_str,"/*")==1&&endsWith(rule_str,"*/")!=1){
				status=5;break;
			}else if(startsWith(rule_str,"/*")!=1&&endsWith(rule_str,"*/")==1){
				status=5;break;
			}

			string_list.clear();
			split(string_list,rule_str,is_any_of("{"));

			int _status=0;
			string rule_str1="";
			string rule_str2="";
			for(int i1=0;i1<string_list.size();i1++){
				string e=string_list[i1];
				if(_status==0){
					if(endsWith(e," ")!=1&&endsWith(e,"\t")!=1){
						rule_str1+=e;
						if(i1!=(string_list.size()-1)){
							rule_str1+="{";
						}
					}else{
						rule_str1+=e;
						rule_str1= trim_right_copy(rule_str1);
						_status=1;
					}
				}else if(_status==1){
					rule_str2+="{";
					rule_str2+=trim_left_copy(trim_right_copy(e));
				}
			}
			left=rule_str1;
			orders.push_back(left);
			//cout<<"left="<<rule_str1<<endl;
			rule_str2= trim_right_copy(rule_str2);
			rule_str2= trim_left_copy(rule_str2);



			if(rule_str2!=""){
				os.str("");
				for(int i1=0;i1<rule_str2.length();i1++){
					os<<rule_str2[i1];
					if(rule_str2[i1]=='{'){
						bracketsCount++;
					}else if(rule_str2[i1]=='}'){
						bracketsCount--;
					}
				}
				if(bracketsCount==0){
					status=6;
					right=os.str();
					order_map[left]=right;
					left="";
					right="";
					//cout<<"right="<<os.str()<<endl;
					os.str("");
				}else{
					status=3;
				}
			}else{
				status=6;
				left="";
				right="";
			}



		}else if(status==3){
			if(startsWith(rule_str,"//")==1){
				continue;
			}else if(startsWith(rule_str,"/*")==1&&endsWith(rule_str,"*/")==1){
				continue;
			}else if(startsWith(rule_str,"/*")==1&&endsWith(rule_str,"*/")!=1){
				status=4;continue;
			}else if(startsWith(rule_str,"/*")!=1&&endsWith(rule_str,"*/")==1){
				status=5;break;
			}


			if(rule_str!=""){
				for(int i1=0;i1<rule_str.length();i1++){
					os<<rule_str[i1];
					if(rule_str[i1]=='{'){
						bracketsCount++;
					}else if(rule_str[i1]=='}'){
						bracketsCount--;
					}
				}
				if(bracketsCount==0){
					status=6;
					right=os.str();
					order_map[left]=right;
					left="";
					right="";
					//cout<<"right="<<os.str()<<endl;
					os.str("");
				}else{
					status=3;
				}
			}

		}else if(status==4){
			if(startsWith(rule_str,"//")==1){
				status=5;break;
			}else if(startsWith(rule_str,"/*")==1&&endsWith(rule_str,"*/")==1){
				status=5;break;
			}else if(startsWith(rule_str,"/*")==1&&endsWith(rule_str,"*/")!=1){
				status=5;break;
			}else if(startsWith(rule_str,"/*")!=1&&endsWith(rule_str,"*/")==1){
				status=3;continue;
			}
		}
	}



log("解决预测表冲突");
while(getline(input_file,rule_str))
{
	string_list.clear();
	split(string_list,rule_str,is_any_of("|"));
	temp_forecast_map[string_list[0]+"|"+string_list[1]]=string_list[2];
}
input_file.close();
}






Slr::Slr(){

}


Slr::~Slr(){
}

/**

深度优先遍历:
T
|
----------------------------
|                          |
B                          C
|                          |
-                          ---------------------------
|                          |      |           |      |
'simple-type-specifier'    '['    'number'    ']'    C
                                                     |
                                                     ---------------------------
                                                     |      |           |      |
                                                     '['    'number'    ']'    C
                                                                               |
                                                                               -
                                                                               |
                                                                               0

ele_begin : T		{$$=$1;}
T : B C			{$2.next=$1;$$=$2;}
B : 'simple-type-specifier'	{Type* type=new Type($1,Tag::BASIC,4);$$=type;}
C : 0	{$$=$$.next;}
C : '[' 'number' ']' C	{$4.next=$$.next;$$=new Array(atoi(($2).c_str()),(Type*)$4);}

3
4
*/
