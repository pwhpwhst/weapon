#pragma once
#include "Type.h"
class Array:public Type{
public: Type *of;
public: int size=1;
public: Array(int sz,Type *p);
public: virtual ~Array();
};

