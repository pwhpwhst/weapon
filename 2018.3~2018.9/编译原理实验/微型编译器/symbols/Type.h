#pragma once
#include <memory>
#include "Token.h"
using namespace std;


class Type:public Token{

//默认构造器
public: Type();

public: Type(string type);

public: virtual ~Type();

public: virtual Token* clone();
};

