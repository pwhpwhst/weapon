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

int begin=rule_str.find_first_of(':')+1;
int end=rule_str.find_first_of('{');
if(end==-1){
	end=rule_str.size();
}
int length=end-begin;
string temp_string=rule_str.substr(begin,length);
temp_string=trim_right_copy(trim_left_copy(temp_string));
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

