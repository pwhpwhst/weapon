#include "Type.h"
#include "Tag.h"

Type::Type(){
    this->content=Tag::TYPE;
}

Type::Type(string type){
    this->tag=Tag::TYPE;
    this->content=type;
}

Type::~Type(){}

Token * Type::clone(){
    Type* p= new Type();
	p->tag=this->tag;
	p->content=this->content;
	return p;
}
