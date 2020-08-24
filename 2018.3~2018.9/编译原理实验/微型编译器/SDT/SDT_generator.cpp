#include<iostream>
#include<sstream>
#include"SDT_generator.h"
#include"..\symbols\Type.h"
#include"..\symbols\Array.h"
#include"..\lexer\Tag.h"
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
unordered_map<string,Token*> &result_map,set<string> &has_calculate_set){
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
class Ele_begin_0_SDT_genertor:public SDT_genertor{
public: P_NodeValue handle(const P_NodeValue &nodeValue,unordered_map<string,Token*> &result_map,set<string> &has_calculate_set){
cout<<"carry out Ele_begin_0_SDT_genertor"<<endl;
string ele_begin_syn=own(nodeValue,NodeValue::SYN);
string t_syn=child(nodeValue,0,NodeValue::SYN);
if(has_calculate_set.count(ele_begin_syn)==0){
if(has_calculate_set.count(t_syn)==0){
cout<<"lack"<<t_syn<<endl;
return P_NodeValue(new NodeValue(nodeValue->node->child_node_list[0],NodeValue::SYN));
}
result_map[ele_begin_syn]=result_map[t_syn];
has_calculate_set.insert(ele_begin_syn);
}
return nullptr;
}
public: ~Ele_begin_0_SDT_genertor(){}
};
class T_0_SDT_genertor:public SDT_genertor{
public: P_NodeValue handle(const P_NodeValue &nodeValue,unordered_map<string,Token*> &result_map,set<string> &has_calculate_set){
cout<<"carry out T_0_SDT_genertor"<<endl;
string c_inh=child(nodeValue,1,NodeValue::INH);
string b_syn=child(nodeValue,0,NodeValue::SYN);
if(has_calculate_set.count(c_inh)==0){
if(has_calculate_set.count(b_syn)==0){
cout<<"lack"<<b_syn<<endl;
return P_NodeValue(new NodeValue(nodeValue->node->child_node_list[0],NodeValue::SYN));
}
result_map[c_inh]=result_map[b_syn];
has_calculate_set.insert(c_inh);
}
string t_syn=own(nodeValue,NodeValue::SYN);
string c_syn=child(nodeValue,1,NodeValue::SYN);
if(has_calculate_set.count(t_syn)==0){
if(has_calculate_set.count(c_syn)==0){
cout<<"lack"<<c_syn<<endl;
return P_NodeValue(new NodeValue(nodeValue->node->child_node_list[1],NodeValue::SYN));
}
result_map[t_syn]=result_map[c_syn];
has_calculate_set.insert(t_syn);
}
return nullptr;
}
public: ~T_0_SDT_genertor(){}
};
class B_0_SDT_genertor:public SDT_genertor{
public: P_NodeValue handle(const P_NodeValue &nodeValue,unordered_map<string,Token*> &result_map,set<string> &has_calculate_set){
cout<<"carry out B_0_SDT_genertor"<<endl;
string basic_0_content=nodeValue->node->child_node_list[0]->content;
Type* type=new Type(basic_0_content,Tag::BASIC,4);
string b_syn=own(nodeValue,NodeValue::SYN);
if(has_calculate_set.count(b_syn)==0){
result_map[b_syn]=type;
has_calculate_set.insert(b_syn);
}
return nullptr;
}
public: ~B_0_SDT_genertor(){}
};
class C_0_SDT_genertor:public SDT_genertor{
public: P_NodeValue handle(const P_NodeValue &nodeValue,unordered_map<string,Token*> &result_map,set<string> &has_calculate_set){
cout<<"carry out C_0_SDT_genertor"<<endl;
string c_syn=own(nodeValue,NodeValue::SYN);
string c_inh=own(nodeValue,NodeValue::INH);
if(has_calculate_set.count(c_syn)==0){
if(has_calculate_set.count(c_inh)==0){
cout<<"lack"<<c_inh<<endl;
return P_NodeValue(new NodeValue(nodeValue->node,NodeValue::INH));
}
result_map[c_syn]=result_map[c_inh];
has_calculate_set.insert(c_syn);
}
return nullptr;
}
public: ~C_0_SDT_genertor(){}
};
class C_1_SDT_genertor:public SDT_genertor{
public: P_NodeValue handle(const P_NodeValue &nodeValue,unordered_map<string,Token*> &result_map,set<string> &has_calculate_set){
cout<<"carry out C_1_SDT_genertor"<<endl;
string c_inh=child(nodeValue,3,NodeValue::INH);
string c_1_inh=own(nodeValue,NodeValue::INH);
if(has_calculate_set.count(c_inh)==0){
if(has_calculate_set.count(c_1_inh)==0){
cout<<"lack"<<c_1_inh<<endl;
return P_NodeValue(new NodeValue(nodeValue->node,NodeValue::INH));
}
result_map[c_inh]=result_map[c_1_inh];
has_calculate_set.insert(c_inh);
}
string c_1_syn=own(nodeValue,NodeValue::SYN);
string basic_1_content=nodeValue->node->child_node_list[1]->content;
string c_syn=child(nodeValue,3,NodeValue::SYN);
if(has_calculate_set.count(c_1_syn)==0){
if(has_calculate_set.count(c_syn)==0){
cout<<"lack"<<c_syn<<endl;
return P_NodeValue(new NodeValue(nodeValue->node->child_node_list[3],NodeValue::SYN));
}
result_map[c_1_syn]=new Array(atoi((basic_1_content).c_str()),(Type*)result_map[c_syn]);
has_calculate_set.insert(c_1_syn);
}
return nullptr;
}
public: ~C_1_SDT_genertor(){}
};
SDT_Factory SDT_Factory::instance;
SDT_Factory::SDT_Factory(){
factory["ele_begin : T"]=P_SDT_genertor(new Ele_begin_0_SDT_genertor());
factory["T : B C"]=P_SDT_genertor(new T_0_SDT_genertor());
factory["B : 'simple-type-specifier'"]=P_SDT_genertor(new B_0_SDT_genertor());
factory["C : 0"]=P_SDT_genertor(new C_0_SDT_genertor());
factory["C : '[' 'number' ']' C"]=P_SDT_genertor(new C_1_SDT_genertor());
}
SDT_Factory::~SDT_Factory(){}
