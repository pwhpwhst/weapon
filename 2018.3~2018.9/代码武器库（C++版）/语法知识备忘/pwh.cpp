#include<iostream>
#include <memory>
using namespace std;


//��ͨ�������

void fun1(){
//extern int i;�������Ƕ���i
//int i;������������i

int v1=0;
int v2=0;

cin>>v1>>v2;

cout<<v1<<","<<v2<<endl;

//��ȡһ��
string line;
getline(cin,line);
}

//ָ�롢���á���ָ��
void func2(){

int *u=nullptr;
int ival=1024;
int &ref_val=ival;
int *p= &ival;

void *pv= &ival;//void* ���Է��κ����͵�ָ��

//ָ��ָ�������
int *&r=p;
r=&ival;

const int pi=314;
}

//ָ���const
void func3(){
	double pi2=3.14;
const double pi=3.14;
const double *cptr = &pi;// double const *cptr = &pi; Ч��һ��
// *cptr=42 ���󣬲��ܸ�*cptr��ֵ

double * const curErr=&pi2;//curErr ��һֱָ��pi2;
double  const *curErr2=&pi2;

}


//ָ���const
void func4(){
int i=0;
int *const p1=&i;	//���ܸı�p1��ֵ������һ������const
const int ci=42	;	//���ܸı�ci��ֵ������һ������const
const int * p2=&ci; //����ı�p2��ֵ������һ���ײ�const
const int *const p3=p2; //���ҵ�const�Ƕ���const��������ǵײ�const
const int &r=ci; //�����������õ�const���ǵײ�const
}

//��������
void func5(){
typedef double wages;
using wages2=double;
typedef wages base,*p;//p �� double* ��ͬ���
}

//auto��decltype ��ʹ��
void func6(){

int i=0;
auto ii=i;
//auto һ�����Ե�����const��ͬʱ�ײ�const��ᱣ������
//�����ñ�������ʼֵʱ�����������ʼ������ʵ�����ö����ֵ

const int ci=0,&cj=0;
decltype(ci) x=0;//x��������const int;
decltype(cj) y=x;//y��������const int&
}

//�ַ�����ʼ�� ��ֱ�ӳ�ʼ���Ϳ�����ʼ����
void func7(){
string s5="fasfsa";
string s6("fdsfs");
}

//����ʹ�����÷���
int& func8(){
	int *u=new int(8);
	return *u;
}

//shared_ptr ��ʹ��
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