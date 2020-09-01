#pragma once
#include <memory>
#include "..\symbols\Type.h"
#include "..\lexer\Word.h"
using namespace std;


class Id{

public: static int POS;
      

public: Word* id;
public: Type* p;
public: int pos;


//Ä¬ÈÏ¹¹ÔìÆ÷
public: Id();

public: Id(Word* id,Type* p,int pos);

public: virtual ~Id();

};

typedef std::shared_ptr<Id> P_Id;