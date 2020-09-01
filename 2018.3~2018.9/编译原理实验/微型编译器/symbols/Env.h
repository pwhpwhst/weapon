#pragma once
#include "..\lexer\Token.h"
#include "..\inter\Id.h"
#include "..\lexer\Tag.h"
#include "..\lexer\Integer.h"
#include <unordered_map>
using namespace std;

struct hash_name_token{
	size_t operator()(const P_Token & p) const{

		return p->tag;
	}
};




class Env{
public: static int POS;




private: unordered_map<P_Token,P_Id> table;
protected: Env *prev;

public: Env();

//ÈÝÆ÷
public: Env(Env *env);

public: void put(Token* w,Id* i);

public: P_Id get(Token *w);

public: virtual ~Env();
};

