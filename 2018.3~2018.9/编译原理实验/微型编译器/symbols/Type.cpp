#include "Type.h"
using namespace std;


Type::Type(const string &s,int tag,int w):Word(s,tag){
	width=w;
}

Type::~Type(){
}

const string Type::INT="int";