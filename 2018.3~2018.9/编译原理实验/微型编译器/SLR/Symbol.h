#pragma once
#include<string>
#include <memory>
using namespace std;

class Symbol{

public: string name;
public: string type;

public: int offset;


//默认构造器
public: Symbol();

//自定义构造器
public: Symbol(const string &name,const string &type,int &offset);

//容器
public: Symbol(const Symbol &symbol);

public: virtual ~Symbol();


};

typedef std::shared_ptr<Symbol> P_Symbol;