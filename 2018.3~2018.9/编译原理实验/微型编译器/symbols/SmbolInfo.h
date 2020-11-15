// symbols/SmbolInfo.cpp
#pragma once
#include "Token.h"
#include <string>

using namespace std;


class SmbolInfo{

public: static SmbolInfo DEFAULT_SMBOLINFO;

public: static int REGIST_SEQ;

public: int tag;

//用于词法-文法系统
public: string identifier_name;

//类型系统专用
public: Token* type;

//中间代码，寄存器数字下标
public: int registNum;

public: SmbolInfo();

public: SmbolInfo(int tag);

public: SmbolInfo& operator = (const SmbolInfo& smbolInfo);

public: virtual ~SmbolInfo();
};
