#pragma once
#include<string>
using namespace std;
class Node{
public: string symbol;
public: string content;
public: bool is_first_child;
public: Node *parent;
public: Node *l_node;
public: Node *r_node;
};



