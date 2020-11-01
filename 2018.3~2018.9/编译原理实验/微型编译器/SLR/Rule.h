#pragma once
#include<vector>
#include<string>
#include <memory>
using namespace std;

class Rule{

public: string rule_name;
public: vector<string> symbols;

public: vector<string> first;



//默认构造器
public: Rule();

//自定义构造器
public: Rule(const string &rule_str);

//容器
public: Rule(const Rule &rule);

public: virtual ~Rule();


};

typedef std::shared_ptr<Rule> P_Rule;