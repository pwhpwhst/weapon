#pragma once
#include"Rule.h"
#include <memory>
using namespace std;

class Item{

public: P_Rule rule;
public: int status;


//Ĭ�Ϲ�����
public: Item();

//�Զ��幹����
public: Item(const P_Rule &rule,int status);

//����
public: Item(const Item &item);

public: virtual ~Item();


};

typedef std::shared_ptr<Item> P_Item;