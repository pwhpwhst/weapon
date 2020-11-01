#pragma once
#include "Token.h"
// #include <unordered_map>
using namespace std;


class SmbolInfo{

public: static SmbolInfo DEFAULT_SMBOLINFO;

public: static int REGIST_SEQ;

public: int tag;

public: Token* type;

public: int registNum;

public: SmbolInfo();

public: SmbolInfo(int tag);

public: virtual ~SmbolInfo();
};
