#include"symbols\Env.h"
#include"slr.h"
#include"symbols\Type.h"


void test3();
int main(){
test3();
}




void test3(){
string rule_file="D:\\Users\\Administrator\\Desktop\\project2018.3_2018.9\\2018.3~2018.9\\����ԭ��ʵ��\\΢�ͱ�����\\pwh������\\rule.txt";
string compile_file="D:\\Users\\Administrator\\Desktop\\project2018.3_2018.9\\2018.3~2018.9\\����ԭ��ʵ��\\΢�ͱ�����\\pwh������\\test.txt";
//string rule_file="F:\\codeWeaponStore\\project2018.3_2018.9\\2018.3~2018.9\\����ԭ��ʵ��\\΢�ͱ�����\\rule.txt";
//string compile_file="F:\\codeWeaponStore\\project2018.3_2018.9\\2018.3~2018.9\\����ԭ��ʵ��\\΢�ͱ�����\\test.txt";
Env env;
cout<<"dasd1"<<endl;
Slr slr;
slr.slr( rule_file, compile_file,env);
cout<<"dasd"<<endl;
env.traversal();
Token* t=env.get("adsadas").type;
Type* t1=(Type*) t;
cout<<t1->content<<endl;
}
