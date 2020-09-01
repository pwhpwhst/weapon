#include "Env.h"
using namespace std;




Env::Env(Env *env){
	prev=env;
}

Env::Env(){
	prev=nullptr;
}

void Env::put(Token *w,Id *i){
	table[P_Token(w)]=P_Id(i);
}

P_Id Env::get(Token *w){
	Env* ptr=this;
	while(ptr!=nullptr){
		P_Token p=P_Token(w);
		if(ptr->table.find(p)!=ptr->table.end()){
			return ptr->table[p];
		}
	}
	return nullptr;
}



Env::~Env(){
	prev=nullptr;
}

int Env::POS=0;



