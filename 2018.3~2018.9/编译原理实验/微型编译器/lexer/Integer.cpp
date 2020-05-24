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