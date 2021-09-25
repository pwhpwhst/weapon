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

SDT_Factory SDT_Factory::instance;
SDT_Factory::SDT_Factory(){
}
SDT_Factory::~SDT_Factory(){}
