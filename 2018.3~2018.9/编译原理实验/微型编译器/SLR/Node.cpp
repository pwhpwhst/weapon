#include<vector>
#include <sstream>
#include "Node.h"
using namespace std;

string Node::get_rule_str(){
	ostringstream os;
	os<<this->symbol<<" :";
	for(const auto &e:child_node_list){
		os<<" "<<e->symbol;
	}
	return os.str();
}
/**
void releaseNode(Node *node){
	if(node->parent!=nullptr){
		if(node->is_first_child){
			if(node->r_node!=nullptr){
				node->parent->l_node=node->r_node;
				node->r_node->is_first_child=true;
				node->r_node->parent=node->parent;
			}

		}else{
			node->parent->r_node=node->r_node;
		}
		node->parent=nullptr;
		node->r_node=nullptr;
	}

vector<Node*> node_stack;
node_stack.push_back(node);

while(node_stack.size()>0){

Node *present_node=node_stack.back();

if(present_node->l_node!=nullptr){
	node_stack.push_back(present_node->l_node);
}else if(present_node->r_node!=nullptr){
	node_stack.push_back(present_node->r_node);
}else{
	node_stack.pop_back();
	delete present_node;
}

}

}
*/