// symbols/Env.cpp
#include "Env.h"
#include<iostream>
using namespace std;




Env::Env(Env *env){
	prev=env;
}

Env::Env(){
	prev=nullptr;
}



bool Env::put(const string& id,const SmbolInfo &info){
    table[id]=info;
    if(table.find(id)==table.end()){
         table[id]=info;
        return true;
    }else{
        return false;
    }
}

 SmbolInfo & Env::get(const string& id){
	Env* ptr=this;

	while(ptr!=nullptr){
		if(ptr->table.find(id)!=ptr->table.end()){
			return ptr->table[id];
		}else{
			ptr=prev;
		}
	}
	return SmbolInfo::DEFAULT_SMBOLINFO;
}


void Env::traversal(){
	Env* ptr=this;

	while(ptr!=nullptr){
            for(const auto& e:ptr->table){
                cout<<"e.first="<<e.first<<endl;
                cout<<"e.second="<<e.second.tag<<endl;
            }
            ptr=prev;
	}
}



Env::~Env(){
	if(prev!=nullptr){
        delete prev;
        prev=nullptr;
	}
}





