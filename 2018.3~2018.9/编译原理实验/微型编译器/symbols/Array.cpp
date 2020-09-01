#include "Array.h"
#include "../lexer/Tag.h"
using namespace std;


Array::Array(int sz,Type *p):Type("[]",Tag::INDEX,width){
	size=sz;
	of=p;
}

Array::~Array(){
	delete of;
}


