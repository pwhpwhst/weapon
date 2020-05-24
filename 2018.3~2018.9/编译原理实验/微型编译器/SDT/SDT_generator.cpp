#include<iostream>
#include"SDT_generator.h"
using namespace std;

SDT_genertor::SDT_genertor(){}

SDT_genertor::~SDT_genertor(){}

class Default_SDT_genertor:public SDT_genertor{

public: Default_SDT_genertor(){}

public: void handle(Env &env,const Node &node){
		cout<<&node<<":"<<endl;
		cout<<"symbol="<<node.symbol<<endl;
		cout<<"content="<<node.content<<endl;
		cout<<"is_first_child="<<node.is_first_child<<endl;
		cout<<"parent="<<node.parent<<endl;
		cout<<"l_node="<<node.l_node<<endl;
		cout<<"r_node="<<node.r_node<<endl;
		cout<<endl;
}

public: ~Default_SDT_genertor(){}

};

SDT_Factory SDT_Factory::instance;
SDT_Factory::SDT_Factory(){
	factory["ele_begin : T"]=P_SDT_genertor(new Default_SDT_genertor());
	factory["B : 'simple-type-specifier'"]=P_SDT_genertor(new Default_SDT_genertor());
	factory["C : 0"]=P_SDT_genertor(new Default_SDT_genertor());
	factory["C : '[' 'number' ']' C"]=P_SDT_genertor(new Default_SDT_genertor());
}

SDT_Factory::~SDT_Factory(){}



