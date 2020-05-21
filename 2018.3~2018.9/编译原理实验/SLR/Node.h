#pragma once
#include<string>
using namespace std;
struct Node{
string symbol;
string content;
bool is_first_child;
Node *parent;
Node *l_node;
Node *r_node;
};


