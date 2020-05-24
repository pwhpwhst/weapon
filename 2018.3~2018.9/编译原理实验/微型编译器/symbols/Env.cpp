#include "Env.h"
using namespace std;

Env::Env(Env *env){
	prev=env;
}

Env::Env(){
	prev=nullptr;
}

void Env::put(P_Token w,P_Id i){
	table[w]=i;
}

P_Id Env::get(const P_Token &w){
	Env* ptr=this;
	while(ptr!=nullptr){
		if(ptr->table.find(w)!=ptr->table.end()){
			return ptr->table[w];
		}
	}
	return nullptr;
}



Env::~Env(){
	prev=nullptr;
}