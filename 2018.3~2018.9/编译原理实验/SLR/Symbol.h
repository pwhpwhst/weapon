#pragma once
#include<string>
#include <memory>
using namespace std;

class Symbol{

public: string name;
public: string type;

public: int offset;


//Ĭ�Ϲ�����
public: Symbol();

//�Զ��幹����
public: Symbol(const string &name,const string &type,int &offset);

//����
public: Symbol(const Symbol &symbol);

public: virtual ~Symbol();


};

typedef std::shared_ptr<Symbol> P_Symbol;