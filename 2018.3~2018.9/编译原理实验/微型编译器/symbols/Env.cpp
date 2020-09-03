#include "Env.h"
using namespace std;




Env::Env(Env *env){
	prev=env;
}

Env::Env(){
	prev=nullptr;
}

void Env::put(const Word& w,Id *i){
	table[w]=P_Id(i);
}

P_Id Env::get(const Word& w){
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
	prev=nullptr;
}

int Env::POS=0;



