#pragma once
#include "..\lexer\Word.h"
#include "..\inter\Id.h"
#include "..\lexer\Tag.h"
#include "..\lexer\Integer.h"
#include <unordered_map>
using namespace std;

struct hash_name_word{
	size_t operator()(const Word & p) const{
		return p.tag;
	}
};




class Env{
public: static int POS;




private: unordered_map<Word,P_Id,hash_name_word> table;
protected: Env *prev;

public: Env();

//ÈÝÆ÷
public: Env(Env *env);

public: void put(const Word& w,Id* i);

public: P_Id get(const Word& w);

public: virtual ~Env();
};

