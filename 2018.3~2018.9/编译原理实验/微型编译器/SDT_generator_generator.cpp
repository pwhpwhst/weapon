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
//#include"SLR\Rule.h"

using namespace std;
using namespace boost;

const string project_index="SDT\\SDT_generator.cpp";
//const string rule_file="F:\\codeWeaponStore\\2018.3~2018.9\\编译原理实验\\微型编译器\\rule.txt";
const string rule_file="E:\\Users\\Administrator\\Desktop\\project2018.3_2018.9\\2018.3~2018.9\\编译原理实验\\微型编译器\\pwh的试验\\rule.txt";


int startsWith(string s, string sub){
	if(sub.length()>s.length()){
		return 0;
	}
        return s.find(sub)==0?1:0;
}

int endsWith(string s,string sub){
	if(sub.length()>s.length()){
		return 0;
	}
        return s.rfind(sub)==(s.length()-sub.length())?1:0;
}

string replaceAll(string str,string sub,string replacement){
    int pos;
    pos = str.find(sub);
    while(pos != -1){
        // str.length()求字符的长度，注意str必须是string类型
        str.replace(pos,string(sub).length(),replacement);
        pos = str.find(sub);
    }

    return str;
}

string upperFirst(string str){
	str[0]=str[0]-32;
	return str;
}

int main(){
/*
1-匹配ruleStr中
2-匹配ruleStr结束，匹配代码段中
3-匹配代码段
4-注释中
5-异常
6-完成匹配，准备下一次匹配
*/

/*
==
//
/*
*/

/*
无 翻译体
0-9a-zA-Z;
....{
}
*/



	ifstream input_file;
	input_file.open(rule_file.data());
	string rule_str;
	ostringstream os;
//	os.str("");
	int status=6;
	int bracketsCount=0;
// 构造ordered_map begin
	vector <string> string_list;


	string left;
	string right;
	vector<string> orders;
	unordered_map<string,string> order_map;
	while(getline(input_file,rule_str)){
		if(rule_str[0]=='='){
			break;
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

// 构造ordered_map end

//构建文件头开始

	ofstream fs;
	fs.open(project_index.c_str());
	if (!fs){
	return 0;
	}
	fs<<"#include<iostream>"<<endl;
	fs<<"#include<sstream>"<<endl;
	fs<<"#include\"SDT_generator.h\""<<endl;
	fs<<"#include\"..\\symbols\\Env.h\""<<endl;
	fs<<"#include\"..\\symbols\\CompileInfo.h\""<<endl;
	fs<<"#include\"..\\symbols\\Tag.h\""<<endl;
	fs<<"#include\"..\\symbols\\Token.h\""<<endl;
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
	fs<<"unordered_map<string,Token*> &result_map,set<string> &has_calculate_set,Env &env,CompileInfo &compileInfo){"<<endl;
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
//构建文件头结束


unordered_map<string,int> class_count_map;
unordered_map<string,string> rule_class_map;
regex partten("(\\$(\\$|\\d+)(?!\\.next))|(\\$(\\$|\\d+)\\.next)");
regex numPartten("\\d+");
regex declarePartten("Declare\\s+\\$\\d+\\s*;");
for(const auto &e:order_map){

	//获取rule的首项 的 className 和 sclassName
	string_list.clear();
	split(string_list,e.first,is_any_of(":"));
	string str=trim_right_copy(trim_left_copy(string_list[0]));
	string className=upperFirst(replaceAll(str,"-","_"));
	string sclassName=replaceAll(str,"-","_");


	if(class_count_map.find(className)==class_count_map.end()){
		class_count_map[className]=0;
	}
	int count=class_count_map[className];
	class_count_map[className]=class_count_map[className]+1;
	rule_class_map[e.first]=className+"_"+to_string(count);

	vector<string> childNameList;
	str=trim_right_copy(trim_left_copy(string_list[1]));
	string_list.clear();
	split(string_list,str,is_any_of(" "));
	for(const auto& e1:string_list){
		childNameList.push_back(e1);
	}




	//拆解命令
	vector<string> orders;
	os.str("");
	for(const auto &e1:e.second){
		if(e1!=';'&&e1!='}'&&e1!='{'){
			os<<e1;
		}else{
			os<<e1;
			orders.push_back(os.str());
			os.str("");
		}
	}


	//析出参数
	set<string> symbol_set;

	for(const auto& e1:orders){

		std::regex_iterator<std::string::const_iterator> begin(e1.cbegin(), e1.cend(), partten);
		for (auto iter = begin; iter != std::sregex_iterator(); iter++)
		{
			symbol_set.insert(iter->str());
		}
	}


	unordered_map<string,int> _classNameCountMap;
	unordered_map<string,int> _indexCountMap;


	fs<<"class "<<className<<"_"<<count<<"_SDT_genertor:public SDT_genertor{"<<endl;
	fs<<"	public: P_NodeValue handle(const P_NodeValue &nodeValue,unordered_map<string,Token*> &result_map,set<string> &has_calculate_set,Env &env,CompileInfo &compileInfo){"<<endl;
//	fs<<"		cout<<\"carry out "<<className<<"_"<<count<<"_SDT_genertor\"<<endl;"<<endl;


	//参数定义部分
	unordered_map<string,string> parameterMap;
	for(const auto& e1:symbol_set){

		fs<<"\t";
		if(startsWith(e1,"$$")==1){

			if(_classNameCountMap.find(sclassName)==_classNameCountMap.end()){
				_classNameCountMap[sclassName]=0;
			}

			if(_indexCountMap.find("$")==_indexCountMap.end()){
				int num=_classNameCountMap[sclassName];
				_indexCountMap["$"]=num;
				_classNameCountMap[sclassName]=_classNameCountMap[sclassName]+1;
			}

			string symbol="";

			if(endsWith(e1,"next")==1){
				symbol=sclassName;
				if(_indexCountMap["$"]!=0){
					symbol+="_"+to_string(_indexCountMap["$"]);
				}
				symbol+="_inh";
				fs<<"string "<<symbol<<"=own(nodeValue,NodeValue::INH);"<<endl;
			}else{
				symbol=sclassName;
				if(_indexCountMap["$"]!=0){
					symbol+="_"+to_string(_indexCountMap["$"]);
				}
				symbol+="_syn";
				fs<<"string "<<symbol<<"=own(nodeValue,NodeValue::SYN);"<<endl;
			}

			parameterMap[e1]=symbol;

		}else{

			std::regex_iterator<std::string::const_iterator> numBegin(e1.cbegin(), e1.cend(), numPartten);
			string symbol="";
			if(childNameList[atoi(numBegin->str().c_str())-1][0]=='\''){
				symbol="basic_"+to_string(atoi(numBegin->str().c_str())-1)+"_content";
				fs<<"string "<<symbol<<"=nodeValue->node->child_node_list["<<to_string(atoi(numBegin->str().c_str())-1)<<"]->content;"<<endl;
			}else{
				string _className=replaceAll(childNameList[atoi(numBegin->str().c_str())-1],"-","_");

				if(_classNameCountMap.find(_className)==_classNameCountMap.end()){
					_classNameCountMap[_className]=0;
				}

				if(_indexCountMap.find(numBegin->str())==_indexCountMap.end()){
					int num=_classNameCountMap[_className];
					_indexCountMap[numBegin->str()]=num;
					cout<<_className<<":"<<num<<":"<<numBegin->str()<<endl;
					_classNameCountMap[_className]=_classNameCountMap[_className]+1;
				}


				if(endsWith(e1,"next")==1){

					symbol=_className;
					cout<<"dasd:"<<_indexCountMap[numBegin->str()]<<endl;
					if(_indexCountMap[numBegin->str()]!=0){
						symbol+="_"+to_string(_indexCountMap[numBegin->str()]);
					}
					symbol+="_inh";
					fs<<"string "<<symbol<<"=child(nodeValue,"<<atoi(numBegin->str().c_str())-1<<",NodeValue::INH);"<<endl;

				}else{
					symbol=_className;


					if(_indexCountMap[numBegin->str()]!=0){
						symbol+="_"+to_string(_indexCountMap[numBegin->str()]);
					}
					symbol+="_syn";
					fs<<"string "<<symbol<<"=child(nodeValue,"<<atoi(numBegin->str().c_str())-1<<",NodeValue::SYN);"<<endl;

				}
			}
			parameterMap[e1]=symbol;
		}
	}


//一般命令构造
	for(const auto& e1:orders){

		int tabNum=1;

		if(regex_match(e1,declarePartten)){
			std::regex_iterator<std::string::const_iterator> parameterIt(e1.cbegin(), e1.cend(), partten);
			std::regex_iterator<std::string::const_iterator> numIt(parameterIt->str().cbegin(), parameterIt->str().cend(), numPartten);

			string symbol=parameterMap[parameterIt->str()];
			string nodeValueType="NodeValue::SYN";
			if(endsWith(e1,"next")==1){
				nodeValueType="NodeValue::INH";
			}

			for(int i1=0;i1<tabNum;i1++){fs<<"\t";}
			fs<<"if(has_calculate_set.count("<<symbol<<")==0){"<<endl;
			for(int i1=0;i1<tabNum;i1++){fs<<"\t";}
			fs<<"\treturn P_NodeValue(new NodeValue(nodeValue->node->child_node_list["<<(atoi(numIt->str().c_str())-1)<<"],"<<nodeValueType<<"));"<<endl;
			for(int i1=0;i1<tabNum;i1++){fs<<"\t";}
			fs<<"}"<<endl;
		}else if(startsWith(e1,"$")){
			string_list.clear();
			split(string_list,e1,is_any_of("="));
			string str=trim_right_copy(trim_left_copy(string_list[0]));
			string lSymbol=parameterMap[str];
			for(int i1=0;i1<tabNum;i1++){fs<<"\t";}
			fs<<"if(has_calculate_set.count("<<lSymbol<<")==0){"<<endl;
			str=e1;
			while(str.find("$")!=string::npos){
				for(const auto& e2:parameterMap){
					if(e2.first.find("next")!=string::npos){
						while(str.find(e2.first)!=string::npos){
							string _replacement=e2.second.find("basic")!=string::npos?e2.second:"result_map["+e2.second+"]";
							str=str.replace(str.find(e2.first),e2.first.size(),_replacement);
						}
					}
				}
				for(const auto& e2:parameterMap){
					while(str.find(e2.first)!=string::npos){
							string _replacement=e2.second.find("basic")!=string::npos?e2.second:"result_map["+e2.second+"]";
							str=str.replace(str.find(e2.first),e2.first.size(),_replacement);
					}
				}
			}
			for(int i1=0;i1<tabNum+1;i1++){fs<<"\t";}
			fs<<str<<endl;
			for(int i1=0;i1<tabNum+1;i1++){fs<<"\t";}
			fs<<"has_calculate_set.insert("<<lSymbol<<");"<<endl;
			for(int i1=0;i1<tabNum;i1++){fs<<"\t";}
			fs<<"}"<<endl;
		}else{

			string str=e1;
			while(str.find("$")!=string::npos){
				for(const auto& e2:parameterMap){
					if(e2.first.find("next")!=string::npos){
						while(str.find(e2.first)!=string::npos){
							string _replacement=e2.second.find("basic")!=string::npos?e2.second:"result_map["+e2.second+"]";
							str=str.replace(str.find(e2.first),e2.first.size(),_replacement);
						}
					}
				}
				for(const auto& e2:parameterMap){
					while(str.find(e2.first)!=string::npos){
							string _replacement=e2.second.find("basic")!=string::npos?e2.second:"result_map["+e2.second+"]";
							str=str.replace(str.find(e2.first),e2.first.size(),_replacement);
					}
				}
			}
			for(int i1=0;i1<tabNum;i1++){fs<<"\t";}
			fs<<str<<endl;

		}

	}


	for(auto& e1:parameterMap){
		if(startsWith(e1.first,"$$")!=1&&startsWith(e1.second,"basic")!=1){
			fs<<"\tdelete result_map["+e1.second+"];"<<endl;
			fs<<"\tresult_map["+e1.second+"]=nullptr;"<<endl;
		}
	}


	fs<<"\treturn nullptr;"<<endl;


	fs<<"	}"<<endl;
	fs<<"	public: ~"<<className<<"_"<<count<<"_SDT_genertor(){}"<<endl;
	fs<<"};"<<endl;
	fs<<endl;
	fs<<endl;

}


//构造文件尾
fs<<"SDT_Factory SDT_Factory::instance;"<<endl;
fs<<"SDT_Factory::SDT_Factory(){"<<endl;
for(const auto &e:rule_class_map){
	fs<<"factory[\""<<e.first<<"\"]=P_SDT_genertor(new "<<e.second<<"_SDT_genertor());"<<endl;
}
fs<<"}"<<endl;
fs<<"SDT_Factory::~SDT_Factory(){}"<<endl;
}



