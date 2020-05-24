#pragma once
#include <memory>
#include "Token.h"
using namespace std;


class Integer : public Token{

public: int value;

//Ä¬ÈÏ¹¹ÔìÆ÷
public: Integer();

public: Integer(int value);

public: virtual ~Integer();

};

typedef std::shared_ptr<Integer> P_Integer;