#pragma once
#include <memory>
using namespace std;


class Token{

public: int tag;

//Ä¬ÈÏ¹¹ÔìÆ÷
public: Token();

public: Token(int tag);

public: virtual ~Token();

public: bool operator==(const Token & lp) const;  
};

typedef std::shared_ptr<Token> P_Token;

