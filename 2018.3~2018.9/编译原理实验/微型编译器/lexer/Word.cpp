#include "Word.h"
#include <iostream>
using namespace std;


Word::Word(){
}

Word::Word(const string &lexme,int tag):Token(tag){
	this->lexme=lexme;
}

Word::Word(const Word& word):Token(word.tag){
	this->lexme=word.lexme;
}


Word::~Word(){
}

bool operator==(const Word& lh,const Word& rh){
	if(lh.tag!=rh.tag){
		return false;
	}
	if(lh.lexme!=rh.lexme){
		return false;
	}
	return true;
}