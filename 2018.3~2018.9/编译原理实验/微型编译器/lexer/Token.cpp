#include "Token.h"
#include "Tag.h"
#include "Integer.h"
#include "Word.h"
#include <iostream>
using namespace std;



Token::Token(){
	cout<<"创建Token"<<this<<endl;
}

Token::Token(int tag){
	cout<<"创建Token"<<this<<endl;
	this->tag=tag;
}


Token::~Token(){
	cout<<"销毁Token"<<this<<endl;
}


bool Token::operator==(Token const& rh) const{
	return this->tag== rh.tag;
}



