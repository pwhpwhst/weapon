#include"symbols\Env.h"
#include"slr.h"
#include"symbols\Type.h"


void test3();
int main(){
test3();
}




void test3(){
string rule_file="D:\\Users\\Administrator\\Desktop\\project2018.3_2018.9\\2018.3~2018.9\\编译原理实验\\微型编译器\\pwh的试验\\rule.txt";
string compile_file="D:\\Users\\Administrator\\Desktop\\project2018.3_2018.9\\2018.3~2018.9\\编译原理实验\\微型编译器\\pwh的试验\\test.txt";
//string rule_file="F:\\codeWeaponStore\\project2018.3_2018.9\\2018.3~2018.9\\编译原理实验\\微型编译器\\rule.txt";
//string compile_file="F:\\codeWeaponStore\\project2018.3_2018.9\\2018.3~2018.9\\编译原理实验\\微型编译器\\test.txt";
Env env;
cout<<"尝试将Cpp转成符号表！"<<endl;
Slr slr;
slr.slr( rule_file, compile_file,env);
cout<<"dasd"<<endl;
env.traversal();
Token* t=env.get("adsadas").type;
Type* t1=(Type*) t;
cout<<t1->content<<endl;
cout<<"dasd2"<<endl;
}
