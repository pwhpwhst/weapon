#pragma once
#include"Rule.h"
#include <memory>
using namespace std;

class Item{

public: P_Rule rule;
public: int status;


//默认构造器
public: Item();

//自定义构造器
public: Item(const P_Rule &rule,int status);

//容器
public: Item(const Item &item);

public: virtual ~Item();


};

typedef std::shared_ptr<Item> P_Item;