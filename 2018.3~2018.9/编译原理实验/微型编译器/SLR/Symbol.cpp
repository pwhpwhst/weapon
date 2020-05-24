#include "Symbol.h"
using namespace std;

Symbol::Symbol(){
}

Symbol::Symbol(const string &name,const string &type,int &offset){

	this->name=name;
	this->type=type;
	this->offset=offset;
	if(type=="int"){
		offset+=4;
	}

}


Symbol::Symbol(const Symbol &symbol){
	name=symbol.name;
	type=symbol.type;
	offset=symbol.offset;
}


Symbol::~Symbol(){
}