#include<iostream>
#include <memory>
using namespace std;


//普通输入输出

void fun1(){
//extern int i;声明而非定义i
//int i;声明并定义了i

int v1=0;
int v2=0;

cin>>v1>>v2;

cout<<v1<<","<<v2<<endl;

//读取一行
string line;
getline(cin,line);
}

//指针、引用、空指针
void func2(){

int *u=nullptr;
int ival=1024;
int &ref_val=ival;
int *p= &ival;

void *pv= &ival;//void* 可以放任何类型的指针

//指向指针的引用
int *&r=p;
r=&ival;

const int pi=314;
}

//指针和const
void func3(){
	double pi2=3.14;
const double pi=3.14;
const double *cptr = &pi;// double const *cptr = &pi; 效果一样
// *cptr=42 错误，不能给*cptr赋值

double * const curErr=&pi2;//curErr 将一直指向pi2;
double  const *curErr2=&pi2;

}


//指针和const
void func4(){
int i=0;
int *const p1=&i;	//不能改变p1的值，这是一个顶层const
const int ci=42	;	//不能改变ci的值，这是一个顶层const
const int * p2=&ci; //允许改变p2的值，这是一个底层const
const int *const p3=p2; //靠右的const是顶层const，靠左的是底层const
const int &r=ci; //用于声明引用的const都是底层const
}

//处理类型
void func5(){
typedef double wages;
using wages2=double;
typedef wages base,*p;//p 是 double* 的同义词
}

//auto和decltype 的使用
void func6(){

int i=0;
auto ii=i;
//auto 一般会忽略掉顶层const，同时底层const则会保留下来
//当引用被用作初始值时，真正参与初始化的其实是引用对象的值

const int ci=0,&cj=0;
decltype(ci) x=0;//x的类型是const int;
decltype(cj) y=x;//y的类型是const int&
}

//字符串初始化 （直接初始化和拷贝初始化）
void func7(){
string s5="fasfsa";
string s6("fdsfs");
}

//慎重使用引用返回
int& func8(){
	int *u=new int(8);
	return *u;
}

//shared_ptr 的使用
void func9(){
	auto p = make_shared<int>(42);
	auto r=p;
	r=nullptr;
	p=nullptr;
}


int main(){

//fun1();
cout<<func8();
}