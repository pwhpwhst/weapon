#include<iostream>
#include<vector>
#include<boost/algorithm/string.hpp>
#include "Rule.h"
using namespace std;
using namespace boost;

Rule::Rule(){
}

Rule::Rule(const string &rule_str){


vector <string> string_list;
split(string_list,rule_str,is_any_of(":"));
rule_name= trim_right_copy(string_list[0]);
string temp_string=trim_right_copy(trim_left_copy(string_list[1]));
if(temp_string!=""){
split(symbols,temp_string,is_any_of(" "));
}
}


Rule::Rule(const Rule &rule){
rule_name=rule.rule_name;
symbols=rule.symbols;
first=rule.first;
}


Rule::~Rule(){
}

