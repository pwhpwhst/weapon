#pragma once
#include<vector>
#include<string>
#include <memory>
using namespace std;

class Rule{

public: string rule_name;
public: vector<string> symbols;

public: vector<string> first;



//Ĭ�Ϲ�����
public: Rule();

//�Զ��幹����
public: Rule(const string &rule_str);

//����
public: Rule(const Rule &rule);

public: virtual ~Rule();


};

typedef std::shared_ptr<Rule> P_Rule;