#pragma once
#include<string>
#include "Token.h"
using namespace std;
class Word : public Token{

public : string lexme ="";

public : Word();

public : Word(const string &s,int tag);

public: virtual ~Word();

};