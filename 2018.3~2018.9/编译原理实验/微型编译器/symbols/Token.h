#pragma once
#include <memory>
using namespace std;


class Token{

public: int tag;

public: string content;

//Ĭ�Ϲ�����
public: Token();

public: Token(int tag,string content);

public: virtual ~Token();

public: virtual Token* clone();

public: bool operator==(Token const& rh) const;
};

typedef std::shared_ptr<Token> P_Token;

