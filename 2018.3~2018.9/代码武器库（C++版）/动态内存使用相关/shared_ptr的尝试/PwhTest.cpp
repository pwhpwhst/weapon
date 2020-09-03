#include<iostream>
#include<memory>
using namespace std;

class Pwh
{
	public : Pwh(){
	cout<<"创建Pwh"<<this<<endl;
	}
	public : virtual ~Pwh(){
	cout<<"销毁Pwh"<<this<<endl;
	}
};

class Pwh1  : public Pwh
{
	public : Pwh1(){
	cout<<"创建Pwh1"<<this<<endl;
	}
	public : virtual ~Pwh1(){
	cout<<"销毁Pwh1"<<this<<endl;
	}
};

int main(){
	Pwh1 *pwh1=new Pwh1();
	shared_ptr<Pwh1> p1(pwh1);
	if(true){
		shared_ptr<Pwh> p2(p1);
		cout<<"haha1"<<endl;
	}
	cout<<"haha"<<endl;
}