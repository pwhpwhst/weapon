#include "Integer.h"
#include "Tag.h"
using namespace std;


Integer::Integer(){
}

Integer::Integer(int value):Token(Tag::NUM){
	this->value=value;
}

Integer::~Integer(){

}


bool Integer::operator==(const Integer & o1)  const
{
	return this->value==o1.value;
}