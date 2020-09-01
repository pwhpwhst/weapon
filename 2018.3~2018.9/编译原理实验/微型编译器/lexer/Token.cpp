#include "Token.h"
#include "Tag.h"
#include "Integer.h"
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





bool Token::operator==(const Token & o1)  const
{
	if(o1.tag==Tag::NUM){
		return ((Integer*)this)->operator==((const Integer &)o1);
	}
	return false;
}


bool operator==(const P_Token & lh1,const P_Token & lh2)  
{
	return lh1->operator==(*lh2);
}
