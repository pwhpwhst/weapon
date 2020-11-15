// symbols/SmbolInfo.cpp
#pragma once
#include "Token.h"
#include <string>

using namespace std;


class SmbolInfo{

public: static SmbolInfo DEFAULT_SMBOLINFO;

public: static int REGIST_SEQ;

public: int tag;

//���ڴʷ�-�ķ�ϵͳ
public: string identifier_name;

//����ϵͳר��
public: Token* type;

//�м���룬�Ĵ��������±�
public: int registNum;

public: SmbolInfo();

public: SmbolInfo(int tag);

public: SmbolInfo& operator = (const SmbolInfo& smbolInfo);

public: virtual ~SmbolInfo();
};
