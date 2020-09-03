#include<iostream>
#include<unordered_map>
using namespace std;

class Pwh
{
	public : int value;
	public : Pwh(){
	cout<<"创建Pwh"<<this<<endl;
	}
	public : Pwh& operator=(const Pwh& rh){
		this->value=rh.value;
		cout<<"赋值Pwh2"<<this<<endl;
		cout<<"值="<<this->value<<endl;
		return *this;
	}
	public : Pwh(const Pwh& rh){
		this->value=rh.value;
		cout<<"拷贝Pwh2"<<this<<endl;
		cout<<"值="<<this->value<<endl;
	}

	public : virtual ~Pwh(){
	cout<<"销毁Pwh"<<this<<endl;
	}

};


bool operator==(const Pwh& lh,const Pwh& rh){
		return rh.value==rh.value;
	}

struct hash_name_token{
	size_t operator()(const Pwh & p) const{
		return p.value;
	}
};


int main(){
	unordered_map<Pwh,Pwh,hash_name_token> map;
	Pwh pwh1;
	pwh1.value=1;
	Pwh pwh2;
	pwh2.value=11;
	map[pwh1]=pwh2;
	cout<<"fsdfsa"<<endl;
	cout<<map[pwh1].value<<endl;
}