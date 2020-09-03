#pragma once


#include <iostream>
#include <set>
#include <vector>
#include <deque>
#include <string>
#include <unordered_map>
#include <boost/algorithm/string.hpp>
#include <fstream>
#include <sstream>

#include"SLR\Item.h"
#include"SLR\Rule.h"
#include"SLR\Node.h"
#include"SLR\Lex_Word.h"
#include"symbols\Env.h"
#include"symbols\Array.h"
#include"inter\Id.h"
#include"SDT\SDT_generator.h"
#include"lexer\Tag.h"



class Slr{


private: void parse_all_symbol(set<string> &terminator,set<string> &non_terminator,set<string> &zero_terminator,const vector<P_Rule> &ruleList);

private: void get_items_list_and_convert_map(vector<vector<P_Item>> &items_list,unordered_map<int,unordered_map<string,int>> &convert_map,
	const set<string> &non_terminator,unordered_map<string,set<string>> &f_first,const vector<P_Rule> &ruleList,const string start_symbol);

private: void calculate_f_first(unordered_map<string,set<string>> &f_first,const vector<P_Rule> &ruleList,const set<string> &terminator,const set<string> &non_terminator);

private: void calculate_f_follow(unordered_map<string,set<string>> &f_follow, unordered_map<string,set<string>> &f_first,
	const vector<P_Rule> &ruleList,const set<string> &non_terminator,const set<string> &terminator,string start_symbol);

private: void calculate_forecast_list(vector<unordered_map<string,string>> &forecast_list,
	const vector<vector<P_Item>> &items_list,const set<string> &terminator, unordered_map<P_Rule,int> &rule_map,
	 unordered_map<int,unordered_map<string,int>> &convert_map, unordered_map<string,set<string>> &f_follow);

private: void printStack(Node* &node_tree);

private: void printStackTree(Node* &node_tree);

private: void printGraph(vector<vector<P_Item>> items_list,
unordered_map<int,unordered_map<string,int>> convert_map);

private: Node* syntax_analyze(const vector<P_Rule> &ruleList,set<string> zero_terminator, vector<unordered_map<string,string>> &forecast_list,
	unordered_map<int,unordered_map<string,int>> &convert_map,vector<P_Lex_Word> &input);

private: bool detect_ambigulous( vector<unordered_map<string,string>> &forecast_list,
 const vector<P_Rule> &ruleList,const vector<vector<P_Item>> items_list);

private: void gen_middle_code(Env &env,Node* &node_tree);

public: int slr(string rule_file,string compile_file,Env env);

private: int startsWith(string s, string sub);

public: Slr();


public: virtual ~Slr();


};

