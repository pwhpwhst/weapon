#include "Token.h"
#include "Tag.h"
#include "Integer.h"
#include "Word.h"
#include <iostream>
using namespace std;



Token::Token(){
	cout<<"����Token"<<this<<endl;
}

Token::Token(int tag){
	cout<<"����Token"<<this<<endl;
	this->tag=tag;
}


Token::~Token(){
	cout<<"����Token"<<this<<endl;
}


bool Token::operator==(Token const& rh) const{
	return this->tag== rh.tag;
}



