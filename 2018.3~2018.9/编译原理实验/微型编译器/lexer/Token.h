#pragma once
#include <memory>
using namespace std;


class Token{

public: int tag;

//Ĭ�Ϲ�����
public: Token();

public: Token(int tag);

public: virtual ~Token();

};

typedef std::shared_ptr<Token> P_Token;

