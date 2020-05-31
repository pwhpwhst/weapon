#pragma once
#include<string>
#include<vector>
using namespace std;
class Node{
public: string symbol;
public: string content;
public: int offset;
public: Node *parent;
public: vector<Node*> child_node_list;

public: string get_rule_str();
};



