#include<iostream>
#include<sstream>
#include"SDT_generator.h"
#include"..\symbols\Type.h"
#include"..\symbols\Array.h"
#include"..\symbols\Env.h"
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
unordered_map<string,Token*> &result_map,set<string> &has_calculate_set,Env &env){
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
	public: P_NodeValue handle(const P_NodeValue &nodeValue,unordered_map<string,Token*> &result_map,set<string> &has_calculate_set,Env &env){
		cout<<"carry out Ele_begin_0_SDT_genertor"<<endl;
		string ele_begin_syn=own(nodeValue,NodeValue::SYN);
		string condition_syn=child(nodeValue,0,NodeValue::SYN);
		if(has_calculate_set.count(ele_begin_syn)==0){
			if(has_calculate_set.count(condition_syn)==0){
				return P_NodeValue(new NodeValue(nodeValue->node->child_node_list[0],NodeValue::SYN));
			}
			result_map[ele_begin_syn]=result_map[condition_syn];
			has_calculate_set.insert(ele_begin_syn);
		}
		return nullptr;
	}
	public: ~Ele_begin_0_SDT_genertor(){}
};

class Assignment_expression_0_SDT_genertor:public SDT_genertor{
	public: P_NodeValue handle(const P_NodeValue &nodeValue,unordered_map<string,Token*> &result_map,set<string> &has_calculate_set,Env &env){
		cout<<"carry out Assignment_expression_0_SDT_genertor"<<endl;
		string basic_0_content=nodeValue->node->child_node_list[0]->content;
			Integer* num=new Integer(atoi((basic_0_content).c_str()));
		string assignment_expression_syn=own(nodeValue,NodeValue::SYN);
		if(has_calculate_set.count(assignment_expression_syn)==0){
			result_map[assignment_expression_syn]=num;
			has_calculate_set.insert(assignment_expression_syn);
		}
		return nullptr;
	}
	public: ~Assignment_expression_0_SDT_genertor(){}
};

class Declarator_0_SDT_genertor:public SDT_genertor{
	public: P_NodeValue handle(const P_NodeValue &nodeValue,unordered_map<string,Token*> &result_map,set<string> &has_calculate_set,Env &env){
		cout<<"carry out Declarator_0_SDT_genertor"<<endl;
		string basic_0_content=nodeValue->node->child_node_list[0]->content;
			Word* w=new Word(basic_0_content,Tag::ID);
		string declarator_syn=own(nodeValue,NodeValue::SYN);
		if(has_calculate_set.count(declarator_syn)==0){
			result_map[declarator_syn]=w;
			has_calculate_set.insert(declarator_syn);
		}
		return nullptr;
	}
	public: ~Declarator_0_SDT_genertor(){}
};

class Condition_0_SDT_genertor:public SDT_genertor{
	public: P_NodeValue handle(const P_NodeValue &nodeValue,unordered_map<string,Token*> &result_map,set<string> &has_calculate_set,Env &env){
		cout<<"carry out Condition_0_SDT_genertor"<<endl;
		string type_specifier_seq_syn=child(nodeValue,0,NodeValue::SYN);
			if(has_calculate_set.count(type_specifier_seq_syn)==0){
				return P_NodeValue(new NodeValue(nodeValue->node->child_node_list[0],NodeValue::SYN));
			}
			Token* tp1=result_map[type_specifier_seq_syn];
		string declarator_syn=child(nodeValue,1,NodeValue::SYN);
			if(has_calculate_set.count(declarator_syn)==0){
				return P_NodeValue(new NodeValue(nodeValue->node->child_node_list[1],NodeValue::SYN));
			}
			Token* tp2=result_map[declarator_syn];
			Id *id=new Id();
			id->id=(Word*)tp2;
			id->p=(Type*)tp1;
			id->pos=Id::POS;
			Id::POS+=4;
			env.put(*(Word*)tp2,id);
		return nullptr;
	}
	public: ~Condition_0_SDT_genertor(){}
};

class Type_specifier_seq_0_SDT_genertor:public SDT_genertor{
	public: P_NodeValue handle(const P_NodeValue &nodeValue,unordered_map<string,Token*> &result_map,set<string> &has_calculate_set,Env &env){
		cout<<"carry out Type_specifier_seq_0_SDT_genertor"<<endl;
		string type_specifier_seq_syn=own(nodeValue,NodeValue::SYN);
		string type_specifier_syn=child(nodeValue,0,NodeValue::SYN);
		if(has_calculate_set.count(type_specifier_seq_syn)==0){
			if(has_calculate_set.count(type_specifier_syn)==0){
				return P_NodeValue(new NodeValue(nodeValue->node->child_node_list[0],NodeValue::SYN));
			}
			result_map[type_specifier_seq_syn]=result_map[type_specifier_syn];
			has_calculate_set.insert(type_specifier_seq_syn);
		}
		return nullptr;
	}
	public: ~Type_specifier_seq_0_SDT_genertor(){}
};

class Type_specifier_seq_1_SDT_genertor:public SDT_genertor{
	public: P_NodeValue handle(const P_NodeValue &nodeValue,unordered_map<string,Token*> &result_map,set<string> &has_calculate_set,Env &env){
		cout<<"carry out Type_specifier_seq_1_SDT_genertor"<<endl;
		string type_specifier_seq_inh=child(nodeValue,1,NodeValue::INH);
		string type_specifier_syn=child(nodeValue,0,NodeValue::SYN);
		if(has_calculate_set.count(type_specifier_seq_inh)==0){
			if(has_calculate_set.count(type_specifier_syn)==0){
				return P_NodeValue(new NodeValue(nodeValue->node->child_node_list[0],NodeValue::SYN));
			}
			result_map[type_specifier_seq_inh]=result_map[type_specifier_syn];
			has_calculate_set.insert(type_specifier_seq_inh);
		}
		string type_specifier_seq_1_syn=own(nodeValue,NodeValue::SYN);
		string type_specifier_seq_syn=child(nodeValue,1,NodeValue::SYN);
		if(has_calculate_set.count(type_specifier_seq_1_syn)==0){
			if(has_calculate_set.count(type_specifier_seq_syn)==0){
				return P_NodeValue(new NodeValue(nodeValue->node->child_node_list[1],NodeValue::SYN));
			}
			result_map[type_specifier_seq_1_syn]=result_map[type_specifier_seq_syn];
			has_calculate_set.insert(type_specifier_seq_1_syn);
		}
		return nullptr;
	}
	public: ~Type_specifier_seq_1_SDT_genertor(){}
};

class Type_specifier_0_SDT_genertor:public SDT_genertor{
	public: P_NodeValue handle(const P_NodeValue &nodeValue,unordered_map<string,Token*> &result_map,set<string> &has_calculate_set,Env &env){
		cout<<"carry out Type_specifier_0_SDT_genertor"<<endl;
		string basic_0_content=nodeValue->node->child_node_list[0]->content;
			Type* type=new Type(basic_0_content,Tag::BASIC,4);
		string type_specifier_syn=own(nodeValue,NodeValue::SYN);
		if(has_calculate_set.count(type_specifier_syn)==0){
			result_map[type_specifier_syn]=type;
			has_calculate_set.insert(type_specifier_syn);
		}
		return nullptr;
	}
	public: ~Type_specifier_0_SDT_genertor(){}
};

SDT_Factory SDT_Factory::instance;
SDT_Factory::SDT_Factory(){
factory["ele_begin : condition"]=P_SDT_genertor(new Ele_begin_0_SDT_genertor());
factory["assignment-expression : 'number'"]=P_SDT_genertor(new Assignment_expression_0_SDT_genertor());
factory["declarator : 'identifier'"]=P_SDT_genertor(new Declarator_0_SDT_genertor());
factory["condition : type-specifier-seq declarator '=' assignment-expression"]=P_SDT_genertor(new Condition_0_SDT_genertor());
factory["type-specifier-seq : type-specifier"]=P_SDT_genertor(new Type_specifier_seq_0_SDT_genertor());
factory["type-specifier-seq : type-specifier type-specifier-seq"]=P_SDT_genertor(new Type_specifier_seq_1_SDT_genertor());
factory["type-specifier : 'simple-type-specifier'"]=P_SDT_genertor(new Type_specifier_0_SDT_genertor());
}
SDT_Factory::~SDT_Factory(){}
