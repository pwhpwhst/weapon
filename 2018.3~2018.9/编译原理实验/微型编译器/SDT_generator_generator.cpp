#include <iostream>
#include <io.h>
#include <string>
#include <fstream>
#include <sstream>
#include <vector>
#include <set>
#include <unordered_map>
#include<boost/algorithm/string.hpp>
#include<regex>
#include"SLR\Rule.h"

using namespace std;
using namespace boost;

const string project_index="SDT\\SDT_generator.cpp";
//const string rule_file="F:\\codeWeaponStore\\2018.3~2018.9\\编译原理实验\\微型编译器\\rule.txt";
const string rule_file="D:\\Users\\Administrator\\Desktop\\project2018.3_2018.9\\2018.3~2018.9\\编译原理实验\\微型编译器\\pwh的试验\\rule.txt";

void get_symbol_para(const string& str,set<string> &result);
int startsWith(string s, string sub);
int endsWith(string s,string sub);
void lower_first_char(string &word);
string gen_attribute_name(const string &e1,const Rule &rule,unordered_map<string,set<string>> &map1,unordered_map<string,int> &map2,unordered_map<string,int> &map3);
string gen_attribute_fun(const string &e1,const Rule &rule);
int main(){

	ifstream input_file;
	input_file.open(rule_file.data());
	
	ofstream fs;
	fs.open(project_index.c_str());
	if (!fs){
	return 0;
	}
	fs<<"#include<iostream>"<<endl;
	fs<<"#include<sstream>"<<endl;
	fs<<"#include\"SDT_generator.h\""<<endl;
	fs<<"#include\"..\\symbols\\Type.h\""<<endl;
	fs<<"#include\"..\\symbols\\Array.h\""<<endl;
	fs<<"#include\"..\\symbols\\Env.h\""<<endl;
	fs<<"#include\"..\\lexer\\Tag.h\""<<endl;
	fs<<"using namespace std;"<<endl;
	fs<<"NodeValue::NodeValue(){}"<<endl;
	fs<<"NodeValue::NodeValue(Node *node,int value_type){"<<endl;
	fs<<"this->node=node;"<<endl;
	fs<<"this->value_type=value_type;"<<endl;
	fs<<"}"<<endl;
	fs<<"NodeValue::~NodeValue(){}"<<endl;
	fs<<"SDT_genertor::SDT_genertor(){}"<<endl;
	fs<<"inline string SDT_genertor::child(const P_NodeValue &nodeValue,int index,int value_type){"<<endl;
	fs<<"os.str(\"\");"<<endl;
	fs<<"os<<nodeValue->node->child_node_list[index]<<\"_\"<<value_type;"<<endl;
	fs<<"return os.str();"<<endl;
	fs<<"}"<<endl;
	fs<<""<<endl;
	fs<<"inline string SDT_genertor::own(const P_NodeValue &nodeValue,int value_type){"<<endl;
	fs<<"os.str(\"\");"<<endl;
	fs<<"os<<nodeValue->node<<\"_\"<<value_type;"<<endl;
	fs<<"return os.str();"<<endl;
	fs<<"}"<<endl;
	fs<<"SDT_genertor::~SDT_genertor(){}"<<endl;
	fs<<"class Default_SDT_genertor:public SDT_genertor{"<<endl;
	fs<<"public: Default_SDT_genertor(){}"<<endl;
	fs<<"public: P_NodeValue handle(const P_NodeValue &nodeValue,"<<endl;
	fs<<"unordered_map<string,Token*> &result_map,set<string> &has_calculate_set,Env &env){"<<endl;
	fs<<"cout<<nodeValue->node<<\":\"<<endl;"<<endl;
	fs<<"cout<<\"symbol=\"<<nodeValue->node->symbol<<endl;"<<endl;
	fs<<"cout<<\"content=\"<<nodeValue->node->content<<endl;"<<endl;
	fs<<"cout<<\"offset=\"<<nodeValue->node->offset<<endl;"<<endl;
	fs<<"cout<<\"parent=\"<<nodeValue->node->parent<<endl;"<<endl;
	fs<<"cout<<\"child_node_list=\"<<&(nodeValue->node->child_node_list)<<endl;"<<endl;
	fs<<"cout<<endl;"<<endl;
	fs<<"return nullptr;"<<endl;
	fs<<"}"<<endl;
	fs<<"public: ~Default_SDT_genertor(){}"<<endl;
	fs<<"};"<<endl<<endl;

//读取 rule.txt begin
	vector<vector<string>> symbol_list;
	string rule_str;
	unordered_map<string,int> symbol_static_map;
	while(getline(input_file,rule_str)){
		vector <string> string_list;
		split(string_list,rule_str,is_any_of(":"));
		string rule_name= trim_right_copy(string_list[0]);
		if(rule_name[0]>='a'&&rule_name[0]<='z'){
			rule_name[0]=rule_name[0]-32;
		}
		for(int i1=0;i1<rule_name.length();i1++){
			if(rule_name[i1]=='-'){
				rule_name[i1]='_';
			}
		}
		if(symbol_static_map.find(rule_name)==symbol_static_map.end()){
			symbol_static_map[rule_name]=0;
		}
		string class_name=rule_name+"_"+to_string(symbol_static_map[rule_name])+"_SDT_genertor";
		symbol_static_map[rule_name]=symbol_static_map[rule_name]+1;

		int begin=0;
		int end=rule_str.find_last_of('{');
		if(end==-1){
			end=rule_str.size();
		}
		int length=end-begin;
		string rule_string=rule_str.substr(begin,length);
		rule_string=trim_right_copy(trim_left_copy(rule_string));
		string rule_string2=rule_string;
		for(int i1=0;i1<rule_string.length();i1++){
			if(rule_string[i1]=='-'){
				rule_string[i1]='_';
			}
		}


		begin=rule_str.find_last_of('{');
		end=rule_str.find_last_of('}');
		length=end-begin+1;
		string action_string=rule_str.substr(begin,length);


		vector<string> temp;
		temp.push_back(rule_string);
		temp.push_back(class_name);
		temp.push_back(action_string);
		temp.push_back(rule_string2);
		symbol_list.push_back(temp);
	}
	input_file.close();


//读取 rule.txt end


	vector<string> string_list;
	vector<string> string_list2;
	set<string> symbol_set;
	set<string> node_all_symbol_set;
	unordered_map<string,set<string>> map1;
	unordered_map<string,int> map2;
	unordered_map<string,int> map3;
	unordered_map<string,bool> map4;
	for(const auto &e:symbol_list){
		Rule rule(e[0]);
		node_all_symbol_set.clear();
		map1.clear();
		map2.clear();
		map3.clear();
		map4.clear();
		fs<<"class "<<e[1]<<":public SDT_genertor{"<<endl;
		fs<<"\tpublic: P_NodeValue handle(const P_NodeValue &nodeValue,unordered_map<string,Token*> &result_map,set<string> &has_calculate_set,Env &env){"<<endl;
		fs<<"\t\tcout<<\"carry out "<<e[1]<<"\"<<endl;"<<endl;	//打印执行过程

		string cmd_strs=e[2];
		cmd_strs=cmd_strs.replace(cmd_strs.find("{"),1,"");
		cmd_strs=cmd_strs.replace(cmd_strs.find("}"),1,"");
		string_list.clear();
		split(string_list,cmd_strs,is_any_of(";"));//string_list 此时保存着指令
		
		

		for(const auto &e2:string_list){
			string e21=trim_right_copy(trim_left_copy(e2));

			if(e21!=""){
				cout<<e2<<":"<<endl;
				string_list2.clear();
				split(string_list2,e21,is_any_of("="));
				symbol_set.clear();
			string left_attribute_name="";
			string left_para_name="";
			unordered_map<string,string> map5;
			if(e21.find("=")!=string::npos){
					//声明左侧元素
					get_symbol_para(string_list2[0],symbol_set);
					
						for(const auto &e1:symbol_set){
							if(node_all_symbol_set.count(e1)==0){
								string attribute_name=gen_attribute_name(e1,rule,map1,map2,map3);
								left_attribute_name=attribute_name;
								left_para_name=e1;
								string attribute_fun=gen_attribute_fun(e1,rule);
								fs<<"\t\tstring "<<attribute_name<<"="<<attribute_fun<<endl; //string c_inh=child(nodeValue,1,NodeValue::INH);
							}
							node_all_symbol_set.insert(e1);
						}
						cout<<"left:"<<endl;
						for(const auto &e:symbol_set){
							cout<<e<<",";
						}
						cout<<endl;
					//声明右侧元素
					symbol_set.clear();
					get_symbol_para(string_list2[1],symbol_set);
					
					for(const auto &e1:symbol_set){

						if(node_all_symbol_set.count(e1)==0){
							bool is_terminate=false;
							vector <string> string_list2;
							if(startsWith(e1,"$$")==0){
								string temp_s=e1.substr(1);
								split(string_list2,temp_s,is_any_of("."));
								string symbol=rule.symbols[atoi(string_list2[0].c_str())-1];
								if(symbol[0]=='\''||symbol=="0"){
									is_terminate=true;
								}
							}
							map4[e1]=is_terminate;
							
							string attribute_name;
							if(!is_terminate){
								attribute_name=gen_attribute_name(e1,rule,map1,map2,map3);
								string attribute_fun=gen_attribute_fun(e1,rule);
								fs<<"\t\tstring "<<attribute_name<<"="<<attribute_fun<<endl;
								map5[e1]="result_map["+attribute_name+"]";
							}else{
								attribute_name="basic_"+to_string(atoi(string_list2[0].c_str())-1)+"_content";
								fs<<"\t\tstring "<<attribute_name<<"=nodeValue->node->child_node_list["<<to_string(atoi(string_list2[0].c_str())-1)<<"]->content;"<<endl;
								map5[e1]=attribute_name;
							}
							

						}
						node_all_symbol_set.insert(e1);							
						

					}
					cout<<"right:"<<endl;
					for(const auto &e1:symbol_set){
						cout<<e1<<",";
					}
					cout<<endl;

			if(left_attribute_name!=""){
				fs<<"\t\tif(has_calculate_set.count("<<left_attribute_name<<")==0){"<<endl;
			}
		}
		
		for(const auto &e1:symbol_set){
			if(map4[e1]){
				continue;
			}
			string attribute_name=gen_attribute_name(e1,rule,map1,map2,map3);
			fs<<"\t\t\tif(has_calculate_set.count("<<attribute_name<<")==0){"<<endl;
//			fs<<"\t\t\t\tcout<<\"lack\"<<"<<attribute_name<<"<<endl;"<<endl;	//打印
			fs<<"\t\t\t\treturn P_NodeValue(new NodeValue(";
			if(startsWith(e1,"$$")==1){
				fs<<"nodeValue->node,";
			}else{
					vector <string> string_list2;
					string temp_s=e1.substr(1);
					split(string_list2,temp_s,is_any_of("."));
					fs<<"nodeValue->node->child_node_list["<<to_string(atoi(string_list2[0].c_str())-1)<<"],";
			}
			if(endsWith(e1,"next")==1){
				fs<<"NodeValue::INH));"<<endl;
			}else{
				fs<<"NodeValue::SYN));"<<endl;
			}
			fs<<"\t\t\t}"<<endl;
		}

//具体内容

		if(left_attribute_name!=""){
			string_list2[0]=string_list2[0].replace(string_list2[0].find(left_para_name),left_para_name.size(),"result_map["+left_attribute_name+"]");
		}

		for(const auto &e:map5){
			if(endsWith(e.first,"next")==1){
				while(string_list2[1].find(e.first)!=string::npos){
					string_list2[1]=string_list2[1].replace(string_list2[1].find(e.first),e.first.size(),e.second);
				}
			}
		}

		for(const auto &e:map5){
			if(endsWith(e.first,"next")==0){
				while(string_list2[1].find(e.first)!=string::npos){
					string_list2[1]=string_list2[1].replace(string_list2[1].find(e.first),e.first.size(),e.second);
				}
			}
		}
		if(string_list2.size()==2){
			fs<<"\t\t\t"<<string_list2[0]<<"="<<string_list2[1]<<";"<<endl;
		}else{
			fs<<"\t\t\t"<<string_list2[0]<<";"<<endl;
		}
		
		
		if(left_attribute_name!=""){
			fs<<"\t\t\thas_calculate_set.insert("<<left_attribute_name<<");"<<endl;
		}

		if(left_attribute_name!=""){
			fs<<"\t\t}"<<endl;
		}


	}
			}
		
		fs<<"\t\treturn nullptr;"<<endl;
		fs<<"\t}"<<endl;
		fs<<"\tpublic: ~"<<e[1]<<"(){}"<<endl;
		fs<<"};"<<endl<<endl;
	}
	//输出factory begin

	fs<<"SDT_Factory SDT_Factory::instance;"<<endl;
	fs<<"SDT_Factory::SDT_Factory(){"<<endl;
	for(const auto &e:symbol_list){
		fs<<"factory[\""<<e[3]<<"\"]=P_SDT_genertor(new "<<e[1]<<"());"<<endl;
	}
	fs<<"}"<<endl;
	fs<<"SDT_Factory::~SDT_Factory(){}"<<endl;
	//输出factory end

	fs.close();

	return 0;
}


void get_symbol_para(const string& str,set<string> &result){
	ostringstream os;
	int stat=0; //初始状态

	for(const auto &e:str){
		if(e=='$'){
			if(stat==0){
				stat=1;//$
				os<<e;
			}else if(stat==1){
				stat=2;//$$
				os<<e;
			}else if(stat==2){

			}else if(stat==3){
				stat=1;//$
				result.insert(os.str());
				os.str("$");
			}else if(stat==4||stat==5||stat==6||stat==7){
				stat=0;
				os.str("");
			}
		}else if(e>='0'&&e<='9'){

			if(stat==0){

			}else if(stat==1){
				stat=3;//$1
				os<<e;
			}else if(stat==2){
				stat=0;
				result.insert(os.str());
				os.str("");
			}else if(stat==3){
				stat=3;
				os<<e;
			}else if(stat==4||stat==5||stat==6||stat==7){
				stat=0;
				os.str("");
			}

		}else if(e=='.'){

			if(stat==0){

			}else if(stat==1||stat==4||stat==5||stat==6||stat==7){
				stat=0;
				os.str("");
			}else if(stat==2){
				stat=4; //$$.
				os<<e;
			}else if(stat==3){
				stat=4; //$1.
				os<<e;
			}

		}else if(e=='n'){
			if(stat==0){

			}else if(stat==1||stat==5||stat==6||stat==7){
				stat=0;
				os.str("");
			}else if(stat==2){
				stat=0; //$$.
				result.insert(os.str());
				os.str("");
			}else if(stat==3){
				stat=0; //$$.
				result.insert(os.str());
				os.str("");
			}else if(stat==4){
				stat=5; //$1.n
				os<<e;
			}

		}
		else if(e=='e'){
			if(stat==0){

			}else if(stat==1||stat==6||stat==7){
				stat=0;
				os.str("");
			}else if(stat==2){
				stat=0; 
				result.insert(os.str());
				os.str("");
			}else if(stat==3){
				stat=0;
				result.insert(os.str());
				os.str("");
			}else if(stat==5){
				stat=6; //$1.ne
				os<<e;
			}
		}
		else if(e=='x'){
			if(stat==0){

			}else if(stat==1||stat==4||stat==5||stat==7){
				stat=0;
				os.str("");
			}else if(stat==2){
				stat=0; 
				result.insert(os.str());
				os.str("");
			}else if(stat==3){
				stat=0;
				result.insert(os.str());
				os.str("");
			}else if(stat==6){
				stat=7;
				os<<e;
			}
		}
		else if(e=='t'){
			if(stat==0){

			}else if(stat==1||stat==4||stat==5||stat==6){
				stat=0;
				os.str("");
			}else if(stat==2){
				stat=0; 
				result.insert(os.str());
				os.str("");
			}else if(stat==3){
				stat=0;
				result.insert(os.str());
				os.str("");
			}else if(stat==7){
				stat=0;
				os<<e;
				result.insert(os.str());
				os.str("");
			}
		}else{
			if(stat==0){

			}else if(stat==1||stat==4||stat==5||stat==6||stat==7){
				stat=0;
				os.str("");
			}else if(stat==2){
				stat=0; 
				result.insert(os.str());
				os.str("");
			}else if(stat==3){
				stat=0;
				result.insert(os.str());
				os.str("");
			}		
		}
	}
	if(stat==2||stat==3){
		result.insert(os.str());
	}
}


int startsWith(string s, string sub){
        return s.find(sub)==0?1:0;
}

int endsWith(string s,string sub){
        return s.rfind(sub)==(s.length()-sub.length())?1:0;
}

void lower_first_char(string &word){
	if(word.size()>0&&word[0]>='A'&&word[0]<='Z'){
		word[0]+=32;
	}
}


string gen_attribute_name(const string &e1,const Rule &rule,unordered_map<string,set<string>> &map1,unordered_map<string,int> &map2,unordered_map<string,int> &map3){
		string attribute_name="";
		string param_name;
		if(startsWith(e1,"$$")==1){
			attribute_name=rule.rule_name;
			lower_first_char(attribute_name);
			param_name="$$";
		}else{
			vector <string> string_list;
			string temp_s=e1.substr(1);
			split(string_list,temp_s,is_any_of("."));
			param_name="$"+string_list[0];
			attribute_name=rule.symbols[atoi(string_list[0].c_str())-1];
			lower_first_char(attribute_name);
		}

		if(map3.find(param_name)!=map3.end()){
			if(map3[param_name]==0){
				attribute_name+="_";
			}else{
				attribute_name+="_";
				attribute_name+=to_string(map3[param_name]);
				attribute_name+="_";
			}
		}else{
			if(map1.find(attribute_name)==map1.end()||map1.find(attribute_name)!=map1.end()&&map1[attribute_name].count(param_name)==0){
				if(map2.find(attribute_name)==map2.end()){
					map2[attribute_name]=0;
				}
				int num=map2[attribute_name];
				map3[param_name]=num;
				map2[attribute_name]=map2[attribute_name]+1;
				map1[attribute_name].insert(param_name);

				if(num==0){
					attribute_name+="_";
				}else{
					attribute_name+="_";
					attribute_name+=to_string(num);
					attribute_name+="_";
				}
			}
		}


		if(endsWith(e1,"next")==1){
			attribute_name+="inh";
		}else{
			attribute_name+="syn";
		}
		return attribute_name;
}

string gen_attribute_fun(const string &e1,const Rule &rule){
		string attribute_fun="";
		if(startsWith(e1,"$$")==1){
			attribute_fun="own(nodeValue,";

		}else{
			attribute_fun="child(nodeValue,";
			attribute_fun+=to_string(atoi(e1.substr(1).c_str())-1)+",";
		}
		if(endsWith(e1,"next")==1){
			attribute_fun+="NodeValue::INH);";
		}else{
			attribute_fun+="NodeValue::SYN);";
		}
		return attribute_fun;
}

	