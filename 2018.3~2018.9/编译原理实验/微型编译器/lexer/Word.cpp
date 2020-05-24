#include "Word.h"
using namespace std;


Word::Word(){
}

Word::Word(const string &lexme,int tag):Token(tag){
	this->lexme=lexme;
}


Word::~Word(){
}