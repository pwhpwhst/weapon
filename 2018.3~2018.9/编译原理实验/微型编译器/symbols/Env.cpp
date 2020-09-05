#include "Env.h"
#include<iostream>
using namespace std;




Env::Env(Env *env){
	prev=env;
}

Env::Env(){
	prev=nullptr;
}

void Env::put(const Word& w,Id *i){
	table[w]=i;
}

Id* Env::get(const Word& w){
	Env* ptr=this;

	while(ptr!=nullptr){
		if(ptr->table.find(w)!=ptr->table.end()){
			return ptr->table[w];
		}else{
			ptr=prev;
		}
	}
	return nullptr;
}



Env::~Env(){

	for(auto &entity:table){
		delete entity.second;
	}
	prev=nullptr;
}

int Env::POS=0;



