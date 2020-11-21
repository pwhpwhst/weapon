#include"symbols\Env.h"
#include"symbols\CompileInfo.h"
#include"slr.h"
#include"symbols\Type.h"
#include"symbols\Tag.h"
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
Env env;
vector<P_Lex_Word>  total_lex_word_list;
total_lex_word_list.clear();
word_parser(compile_file,total_lex_word_list,env);

for(const auto &e:total_lex_word_list){
	cout<<"type="<<e->type<<endl;
}


}


void testForSynax(){
string rule_file="D:\\Users\\Administrator\\Desktop\\project2018.3_2018.9\\2018.3~2018.9\\编译原理实验\\微型编译器\\pwh的试验\\ruleForLexical.txt";
string compile_file="D:\\Users\\Administrator\\Desktop\\project2018.3_2018.9\\2018.3~2018.9\\编译原理实验\\微型编译器\\pwh的试验\\testForLexical.txt";
//string rule_file="F:\\codeWeaponStore\\project2018.3_2018.9\\2018.3~2018.9\\编译原理实验\\微型编译器\\rule.txt";
//string compile_file="F:\\codeWeaponStore\\project2018.3_2018.9\\2018.3~2018.9\\编译原理实验\\微型编译器\\test.txt";
Env env;
//

//    enum-name : 'identifier-enum-name'
//typedef-name : 'identifier-typedef-name'
    shared_ptr<SmbolInfo> newInfo=shared_ptr<SmbolInfo>(new SmbolInfo());
    newInfo->tag=Tag::ID;
    newInfo->identifier_name="'identifier-enum-name'";
    env.put("identifier_enum_name",*newInfo);
    cout<<env.get("identifier_enum_name").identifier_name<<endl;

     newInfo=shared_ptr<SmbolInfo>(new SmbolInfo());
    newInfo->tag=Tag::ID;
    newInfo->identifier_name="'identifier-typedef-name'";
    env.put("identifier_typedef_name",*newInfo);
    cout<<env.get("identifier_typedef_name").identifier_name<<endl;

cout<<"尝试将Cpp转成符号表！"<<endl;
Slr slr;
CompileInfo compileInfo;
slr.slr( rule_file, compile_file,env,compileInfo);
cout<<"检测开始！"<<endl;

if(compileInfo.errInfo!=""){
    cout<<compileInfo.errInfo<<endl;
}else{

}



cout<<"检测完成！"<<endl;

}



void testForLexer(){

string compile_file="D:\\Users\\Administrator\\Desktop\\project2018.3_2018.9\\2018.3~2018.9\\编译原理实验\\微型编译器\\pwh的试验\\testForLexical.txt";
Env env;
vector<P_Lex_Word>  total_lex_word_list;
total_lex_word_list.clear();
word_parser(compile_file,total_lex_word_list,env);

for(const auto &e:total_lex_word_list){
	cout<<"type="<<e->type<<endl;
}


}




int main(){
testForSynax();
}
