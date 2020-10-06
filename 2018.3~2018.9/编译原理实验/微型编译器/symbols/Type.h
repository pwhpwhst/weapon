#pragma once
#include <memory>
#include "Token.h"
using namespace std;


class Type:public Token{

//Ä¬ÈÏ¹¹ÔìÆ÷
public: Type();

public: Type(string type);

public: virtual ~Type();

public: virtual Token* clone();
};

