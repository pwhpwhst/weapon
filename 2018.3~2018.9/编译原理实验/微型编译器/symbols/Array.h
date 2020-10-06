#pragma once
#include "Type.h"
using namespace std;


class Array:public Type{

public: int num;

public: Type *type;

public: Array(int num,Type* type);

public: virtual Token* clone();

public: ~Array();
};



