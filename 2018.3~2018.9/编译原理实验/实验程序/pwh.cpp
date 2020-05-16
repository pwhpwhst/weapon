#include<iostream>
#include<vector>
#include<memory>
using namespace std;
int main(){


struct Element
{
	int x;
	int y;
	int move;
	int step_index;
	unsigned char visited;
};

string a1="hahah1";
vector<shared_ptr<string>> stack;
shared_ptr<string> ele=shared_ptr<string>(new string("ss"));
int a[5]={1,2,3,4,5};
stack.push_back(ele);


int b[5]={1,2,3,4,5};

}
