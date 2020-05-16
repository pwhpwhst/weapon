#include <iostream>
using namespace std;
extern "C"
{
#include "chead.h"
};
//上面也可以直接就 extern“C”  int add(int x , int y);
int main(){
	int c=add(160,360);
	cout<<c;
}
