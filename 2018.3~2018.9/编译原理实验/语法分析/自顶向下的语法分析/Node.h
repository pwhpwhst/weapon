#pragma once
#include<string>
using namespace std;
struct Node{
string symbol;
bool is_first_child;
Node *parent;
Node *l_node;
Node *r_node;
};


