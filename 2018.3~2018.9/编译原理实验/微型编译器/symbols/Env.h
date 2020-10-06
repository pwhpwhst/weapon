#pragma once
#include "SmbolInfo.h"
#include <unordered_map>
using namespace std;






class Env{


private: unordered_map<string,SmbolInfo> table;
protected: Env *prev;

public: Env();

public: Env(Env *env);

public: void traversal();

public: bool put(const string& id,const SmbolInfo &info);

public: SmbolInfo &get(const string& id);

public: virtual ~Env();
};

