#include "Id.h"
using namespace std;


Id::Id(){
}


Id::~Id(){
	if(id!=nullptr){
		delete id;
		id=nullptr;
	}
	if(p!=nullptr){
		delete p;
		p=nullptr;
	}
}

int Id::POS = 0;