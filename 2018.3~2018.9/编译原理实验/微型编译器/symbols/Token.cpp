#include "Token.h"
#include <iostream>
using namespace std;



Token::Token(){
}

Token::Token(int tag,string content){
	this->tag=tag;
	this->content=content;
}




Token::~Token(){
}

Token * Token::clone(){
    Token* p= new Token();
	p->tag=this->tag;
	p->content=this->content;
	return p;
}

bool Token::operator==(Token const& rh) const{
	return this->tag== rh.tag;
	return this->content== rh.content;
}



