#pragma once
#include "../lexer/Word.h"
class Type:public Word{

public: const static string INT;

public: int width=0;

public: Type(const string &s,int tag,int w);

public: virtual ~Type();
};

typedef std::shared_ptr<Word> P_Type;