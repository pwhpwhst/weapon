#pragma once
#include <memory>
using namespace std;


class Id{

public: int pos;

public: int size;

//Ä¬ÈÏ¹¹ÔìÆ÷
public: Id();

public: Id(int pos,int size);

public: virtual ~Id();


};

typedef std::shared_ptr<Id> P_Id;