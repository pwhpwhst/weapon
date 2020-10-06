#pragma once
#include "Token.h"
// #include <unordered_map>
using namespace std;


class SmbolInfo{

public: static SmbolInfo DEFAULT_SMBOLINFO;

public: int tag;

public: Token* type;

public: SmbolInfo();

public: SmbolInfo(int tag);

public: virtual ~SmbolInfo();
};
