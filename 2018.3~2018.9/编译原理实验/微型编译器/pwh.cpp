#include"symbols\Env.h"
#include"slr.h"


void test1();
void test2();

int main(){
test2();
}


void test1(){
string rule_file="D:\\Users\\Administrator\\Desktop\\project2018.3_2018.9\\2018.3~2018.9\\����ԭ��ʵ��\\΢�ͱ�����\\rule.txt";
string compile_file="D:\\Users\\Administrator\\Desktop\\project2018.3_2018.9\\2018.3~2018.9\\����ԭ��ʵ��\\΢�ͱ�����\\test.txt";
//string rule_file="F:\\codeWeaponStore\\project2018.3_2018.9\\2018.3~2018.9\\����ԭ��ʵ��\\΢�ͱ�����\\rule.txt";
//string compile_file="F:\\codeWeaponStore\\project2018.3_2018.9\\2018.3~2018.9\\����ԭ��ʵ��\\΢�ͱ�����\\test.txt";
Env env;
Slr slr;
slr.slr( rule_file, compile_file,env);
}




void test2(){

string rule_file="D:\\Users\\Administrator\\Desktop\\project2018.3_2018.9\\2018.3~2018.9\\����ԭ��ʵ��\\΢�ͱ�����\\������\\rule.txt";
string compile_file="D:\\Users\\Administrator\\Desktop\\project2018.3_2018.9\\2018.3~2018.9\\����ԭ��ʵ��\\΢�ͱ�����\\������\\test.txt";
//string rule_file="F:\\codeWeaponStore\\project2018.3_2018.9\\2018.3~2018.9\\����ԭ��ʵ��\\΢�ͱ�����\\rule.txt";
//string compile_file="F:\\codeWeaponStore\\project2018.3_2018.9\\2018.3~2018.9\\����ԭ��ʵ��\\΢�ͱ�����\\test.txt";
Env env;
Slr slr;
slr.slr( rule_file, compile_file,env);
}