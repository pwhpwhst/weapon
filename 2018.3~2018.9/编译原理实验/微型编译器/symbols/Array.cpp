#include "Array.h"

Array::Array(int num,Type* type):Type("array"){
    this->num=num;
    this->type=type;
}

Array::~Array(){
    if(type!=nullptr){
        delete type;
        type=nullptr;
    }
}


Token* Array::clone(){
  Type* type= (Type* )this->type->clone();
   return new Array(this->num,type);
}
