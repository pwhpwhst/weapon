#include"symbols\Env.h"
#include"symbols\CompileInfo.h"
#include"slr.h"
#include"symbols\Type.h"
#include"SLR\Lex_Word.h"

#include <vector>






void test1(){
string rule_file="D:\\Users\\Administrator\\Desktop\\project2018.3_2018.9\\2018.3~2018.9\\编译原理实验\\微型编译器\\pwh的试验\\rule.txt";
string compile_file="D:\\Users\\Administrator\\Desktop\\project2018.3_2018.9\\2018.3~2018.9\\编译原理实验\\微型编译器\\pwh的试验\\test.txt";
//string rule_file="F:\\codeWeaponStore\\project2018.3_2018.9\\2018.3~2018.9\\编译原理实验\\微型编译器\\rule.txt";
//string compile_file="F:\\codeWeaponStore\\project2018.3_2018.9\\2018.3~2018.9\\编译原理实验\\微型编译器\\test.txt";
Env env;
cout<<"尝试将Cpp转成符号表！"<<endl;
Slr slr;
CompileInfo compileInfo;
slr.slr( rule_file, compile_file,env,compileInfo);
cout<<"检测开始！"<<endl;

if(compileInfo.errInfo!=""){
    cout<<compileInfo.errInfo<<endl;
}else{
Token* t=env.get("abcd").type;
Type* t1=(Type*) t;
cout<<t1->content<<endl;

t=env.get("abc").type;
t1=(Type*) t;
cout<<t1->content<<endl;

 cout<<compileInfo.interCode<<endl;
}



cout<<"检测完成！"<<endl;

}



void test2(){

string compile_file="D:\\Users\\Administrator\\Desktop\\project2018.3_2018.9\\2018.3~2018.9\\编译原理实验\\微型编译器\\pwh的试验\\test.txt";

vector<P_Lex_Word>  total_lex_word_list;
total_lex_word_list.clear();
word_parser(compile_file,total_lex_word_list);

for(const auto &e:total_lex_word_list){
	cout<<"type="<<e->type<<endl;
}


}



void test3(){

string compile_file="D:\\Users\\Administrator\\Desktop\\project2018.3_2018.9\\2018.3~2018.9\\编译原理实验\\微型编译器\\pwh的试验\\testForLexical.txt";

vector<P_Lex_Word>  total_lex_word_list;
total_lex_word_list.clear();
word_parser(compile_file,total_lex_word_list);

for(const auto &e:total_lex_word_list){
	cout<<"type="<<e->type<<endl;
}


}




int main(){
test1();
//test2();
}
