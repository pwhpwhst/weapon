#include<vector>
#include<iostream>
#include "Lex_Word.h"
 #include "../symbols/SmbolInfo.h"
  #include "../symbols/Tag.h"
using namespace std;

extern "C"
{
#include "lex.yy.h"
};

Lex_Word::Lex_Word(){
}


Lex_Word::Lex_Word(const string &type,const string &content){
this->type=type;
this->content=content;
}

Lex_Word::Lex_Word(const Lex_Word &lex_word){
this->type=lex_word.type;
this->content=lex_word.content;
}

Lex_Word::~Lex_Word(){
}


// word_parser(compile_file,total_lex_word_list);

void word_parser(const string& path,vector<P_Lex_Word> &lex_word_list,Env& env){
   struct C_Lex_Word **beg=(struct C_Lex_Word **)malloc(sizeof(struct C_Lex_Word *));
   struct C_Lex_Word **end=(struct C_Lex_Word **)malloc(sizeof(struct C_Lex_Word *));
   char *c_path = const_cast<char *>(path.c_str()) ;
   c_word_parser(c_path,beg,end);

	struct C_Lex_Word *p=*beg;
	while(p!=*end){
		lex_word_list.push_back(P_Lex_Word(new Lex_Word()));
		lex_word_list.back()->type=p->type;
		lex_word_list.back()->content=p->content;
		if(lex_word_list.back()->type=="'identifier'"){
            SmbolInfo& info=env.get(p->content);
            if(info.tag==Tag::ID){
               lex_word_list.back()->type= info.identifier_name;
            }
		}

		p++;
	}
	free(beg);
	free(end);
}


//void c_word_parser(char *c_path, struct C_Lex_Word **beg, struct C_Lex_Word **end){}
