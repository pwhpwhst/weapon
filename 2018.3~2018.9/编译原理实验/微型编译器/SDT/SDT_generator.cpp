#include<iostream>
#include<sstream>
#include"SDT_generator.h"
#include"..\symbols\Env.h"
#include"..\symbols\CompileInfo.h"
#include"..\symbols\Tag.h"
#include"..\symbols\Token.h"
using namespace std;
NodeValue::NodeValue(){}
NodeValue::NodeValue(Node *node,int value_type){
this->node=node;
this->value_type=value_type;
}
NodeValue::~NodeValue(){}
SDT_genertor::SDT_genertor(){}
inline string SDT_genertor::child(const P_NodeValue &nodeValue,int index,int value_type){
os.str("");
os<<nodeValue->node->child_node_list[index]<<"_"<<value_type;
return os.str();
}

inline string SDT_genertor::own(const P_NodeValue &nodeValue,int value_type){
os.str("");
os<<nodeValue->node<<"_"<<value_type;
return os.str();
}
SDT_genertor::~SDT_genertor(){}
class Default_SDT_genertor:public SDT_genertor{
public: Default_SDT_genertor(){}
public: P_NodeValue handle(const P_NodeValue &nodeValue,
unordered_map<string,Token*> &result_map,set<string> &has_calculate_set,Env &env,CompileInfo &compileInfo){
cout<<nodeValue->node<<":"<<endl;
cout<<"symbol="<<nodeValue->node->symbol<<endl;
cout<<"content="<<nodeValue->node->content<<endl;
cout<<"offset="<<nodeValue->node->offset<<endl;
cout<<"parent="<<nodeValue->node->parent<<endl;
cout<<"child_node_list="<<&(nodeValue->node->child_node_list)<<endl;
cout<<endl;
return nullptr;
}
public: ~Default_SDT_genertor(){}
};

class Type_specifier_seq_0_SDT_genertor:public SDT_genertor{
	public: P_NodeValue handle(const P_NodeValue &nodeValue,unordered_map<string,Token*> &result_map,set<string> &has_calculate_set,Env &env,CompileInfo &compileInfo){
		cout<<"carry out Type_specifier_seq_0_SDT_genertor"<<endl;
	string type_specifier_seq_syn=own(nodeValue,NodeValue::SYN);
	string type_specifier_syn=child(nodeValue,0,NodeValue::SYN);
	{
	if(has_calculate_set.count(type_specifier_syn)==0){
		return P_NodeValue(new NodeValue(nodeValue->node->child_node_list[0],NodeValue::SYN));
	}
	if(has_calculate_set.count(type_specifier_seq_syn)==0){
		result_map[type_specifier_seq_syn]=result_map[type_specifier_syn]->clone();
		has_calculate_set.insert(type_specifier_seq_syn);
	}
	}
	delete result_map[type_specifier_syn];
	result_map[type_specifier_syn]=nullptr;
	return nullptr;
	}
	public: ~Type_specifier_seq_0_SDT_genertor(){}
};


class Type_specifier_0_SDT_genertor:public SDT_genertor{
	public: P_NodeValue handle(const P_NodeValue &nodeValue,unordered_map<string,Token*> &result_map,set<string> &has_calculate_set,Env &env,CompileInfo &compileInfo){
		cout<<"carry out Type_specifier_0_SDT_genertor"<<endl;
	string type_specifier_syn=own(nodeValue,NodeValue::SYN);
	string basic_0_content=nodeValue->node->child_node_list[0]->content;
	{
	if(has_calculate_set.count(type_specifier_syn)==0){
		result_map[type_specifier_syn]=new Token(Tag::TYPE,basic_0_content);
		has_calculate_set.insert(type_specifier_syn);
	}
	}
	return nullptr;
	}
	public: ~Type_specifier_0_SDT_genertor(){}
};


class Ele_begin_0_SDT_genertor:public SDT_genertor{
	public: P_NodeValue handle(const P_NodeValue &nodeValue,unordered_map<string,Token*> &result_map,set<string> &has_calculate_set,Env &env,CompileInfo &compileInfo){
		cout<<"carry out Ele_begin_0_SDT_genertor"<<endl;
	string ele_begin_syn=own(nodeValue,NodeValue::SYN);
	string condition_syn=child(nodeValue,0,NodeValue::SYN);
	{
	if(has_calculate_set.count(condition_syn)==0){
		return P_NodeValue(new NodeValue(nodeValue->node->child_node_list[0],NodeValue::SYN));
	}
	if(has_calculate_set.count(ele_begin_syn)==0){
		result_map[ele_begin_syn]=result_map[condition_syn]->clone();
		has_calculate_set.insert(ele_begin_syn);
	}
	}
	delete result_map[condition_syn];
	result_map[condition_syn]=nullptr;
	return nullptr;
	}
	public: ~Ele_begin_0_SDT_genertor(){}
};


class Condition_0_SDT_genertor:public SDT_genertor{
	public: P_NodeValue handle(const P_NodeValue &nodeValue,unordered_map<string,Token*> &result_map,set<string> &has_calculate_set,Env &env,CompileInfo &compileInfo){
		cout<<"carry out Condition_0_SDT_genertor"<<endl;
	string type_specifier_seq_syn=child(nodeValue,0,NodeValue::SYN);
	string declarator_syn=child(nodeValue,1,NodeValue::SYN);
	string assignment_expression_syn=child(nodeValue,3,NodeValue::SYN);
	{
	if(has_calculate_set.count(type_specifier_seq_syn)==0){
		return P_NodeValue(new NodeValue(nodeValue->node->child_node_list[0],NodeValue::SYN));
	}
	if(has_calculate_set.count(declarator_syn)==0){
		return P_NodeValue(new NodeValue(nodeValue->node->child_node_list[1],NodeValue::SYN));
	}
	if(has_calculate_set.count(assignment_expression_syn)==0){
		return P_NodeValue(new NodeValue(nodeValue->node->child_node_list[3],NodeValue::SYN));
	}
	SmbolInfo& info=env.get(result_map[declarator_syn]->content);
	if(info.tag==Tag::DEFAULT){
	SmbolInfo newInfo;
	newInfo.tag=Tag::ID;
	newInfo.type=result_map[type_specifier_seq_syn]->clone();
	SmbolInfo::REGIST_SEQ++;
	newInfo.registNum= SmbolInfo::REGIST_SEQ;
	env.put(result_map[declarator_syn]->content,newInfo);
	os.str("");
	os<<compileInfo.interCode<<"p"<<env.get(result_map[declarator_syn]->content).registNum<<"="<<result_map[assignment_expression_syn]->content<<endl;
	compileInfo.interCode=os.str();
	}
	else{
	compileInfo.errInfo=result_map[declarator_syn]->content+" is dumplicate!";
	}
	}
	delete result_map[assignment_expression_syn];
	result_map[assignment_expression_syn]=nullptr;
	delete result_map[declarator_syn];
	result_map[declarator_syn]=nullptr;
	delete result_map[type_specifier_seq_syn];
	result_map[type_specifier_seq_syn]=nullptr;
	return nullptr;
	}
	public: ~Condition_0_SDT_genertor(){}
};


class Assignment_expression_0_SDT_genertor:public SDT_genertor{
	public: P_NodeValue handle(const P_NodeValue &nodeValue,unordered_map<string,Token*> &result_map,set<string> &has_calculate_set,Env &env,CompileInfo &compileInfo){
		cout<<"carry out Assignment_expression_0_SDT_genertor"<<endl;
	string assignment_expression_syn=own(nodeValue,NodeValue::SYN);
	string basic_0_content=nodeValue->node->child_node_list[0]->content;
	{
	if(has_calculate_set.count(assignment_expression_syn)==0){
		result_map[assignment_expression_syn]=new Token(Tag::INT,basic_0_content);
		has_calculate_set.insert(assignment_expression_syn);
	}
	}
	return nullptr;
	}
	public: ~Assignment_expression_0_SDT_genertor(){}
};


class Declarator_0_SDT_genertor:public SDT_genertor{
	public: P_NodeValue handle(const P_NodeValue &nodeValue,unordered_map<string,Token*> &result_map,set<string> &has_calculate_set,Env &env,CompileInfo &compileInfo){
		cout<<"carry out Declarator_0_SDT_genertor"<<endl;
	string declarator_syn=own(nodeValue,NodeValue::SYN);
	string basic_0_content=nodeValue->node->child_node_list[0]->content;
	{
	if(has_calculate_set.count(declarator_syn)==0){
		result_map[declarator_syn]=new Token(Tag::ID,basic_0_content);
		has_calculate_set.insert(declarator_syn);
	}
	}
	return nullptr;
	}
	public: ~Declarator_0_SDT_genertor(){}
};


SDT_Factory SDT_Factory::instance;
SDT_Factory::SDT_Factory(){
factory["declarator : 'identifier'"]=P_SDT_genertor(new Declarator_0_SDT_genertor());
factory["type-specifier-seq : type-specifier"]=P_SDT_genertor(new Type_specifier_seq_0_SDT_genertor());
factory["type-specifier : 'simple-type-specifier'"]=P_SDT_genertor(new Type_specifier_0_SDT_genertor());
factory["ele_begin : condition"]=P_SDT_genertor(new Ele_begin_0_SDT_genertor());
factory["assignment-expression : 'number'"]=P_SDT_genertor(new Assignment_expression_0_SDT_genertor());
factory["condition : type-specifier-seq declarator '=' assignment-expression"]=P_SDT_genertor(new Condition_0_SDT_genertor());
}
SDT_Factory::~SDT_Factory(){}
