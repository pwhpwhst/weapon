#pragma once
#include "..\lexer\Token.h"
#include "..\inter\Id.h"
#include <unordered_map>
using namespace std;

class Env{
private: unordered_map<P_Token,P_Id> table;
protected: Env *prev;

public: Env();

//ÈÝÆ÷
public: Env(Env *env);

public: void put(P_Token w,P_Id i);

public: P_Id get(const P_Token &w);

public: virtual ~Env();
};

