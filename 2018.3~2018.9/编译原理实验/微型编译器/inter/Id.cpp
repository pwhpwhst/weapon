#include "Id.h"
using namespace std;


Id::Id(){
}

Id::Id(Word *id,Type *p,int pos){
	this->id=id;
	this->p=p;
	this->pos=pos;
}


Id::~Id(){
	delete id;
	id=nullptr;
	delete p;
	p=nullptr;
}

int Id::POS = 0;