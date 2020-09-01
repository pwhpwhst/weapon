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