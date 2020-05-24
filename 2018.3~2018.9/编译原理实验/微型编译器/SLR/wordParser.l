%{
   #include "stdio.h"
   #include "stdlib.h"
   #include<string.h>
   #include "SLR\lex.yy.h"
   
    struct C_Lex_Word lex_word[500];
    struct C_Lex_Word *p_lex_word=lex_word;

void write_word(struct C_Lex_Word *p_lex_word,char *yytext,char *symbol_name){
  p_lex_word->content = (char *)malloc(sizeof(char)*(strlen(yytext)+1));
  strcpy(p_lex_word->content, yytext);
  p_lex_word->type = (char *)malloc(sizeof(char)*(strlen(symbol_name)+1));
  strcpy(p_lex_word->type, symbol_name);
}

%}
letter [_A-Za-z]
digit [0-9]
identifier {letter}({letter}|{digit})*
number [-]?[1-9][0-9]*
LEFT_BRACKET \(
RIGHT_BRACKET \)
MULTI \*
%%
{number} { 
write_word(p_lex_word,yytext,"'number'");
  p_lex_word++;
}

{identifier} {
if(strcmp(yytext,"int")==0){
	write_word(p_lex_word,yytext,"'simple-type-specifier'");
}else{
	write_word(p_lex_word,yytext,"'identifier'");
}

  p_lex_word++;
}



{LEFT_BRACKET} {

 write_word(p_lex_word,yytext,"'('");
  p_lex_word++;
}

{RIGHT_BRACKET} {

  write_word(p_lex_word,yytext,"')'");
  p_lex_word++;
}

"+" {

 write_word(p_lex_word,yytext,"'+'");
  p_lex_word++;
}

{MULTI} {

  write_word(p_lex_word,yytext,"'*'");
  p_lex_word++;
}

" " {
//  write_word(p_lex_word,yytext,"' '");
//  p_lex_word++;
}


";" {
  write_word(p_lex_word,yytext,"';'");
  p_lex_word++;
}

"=" {
  write_word(p_lex_word,yytext,"'='");
  p_lex_word++;
}

"[" {
  write_word(p_lex_word,yytext,"'['");
  p_lex_word++;
}

"]" {
  write_word(p_lex_word,yytext,"']'");
  p_lex_word++;
}

%%

void c_word_parser(char* file_path,struct C_Lex_Word **beg,struct C_Lex_Word **end){
   int i1=0;
   for(i1=0;i1<500;i1++){
	if(lex_word[i1].type!=NULL){
	   free(lex_word[i1].type);
	   lex_word[i1].type=NULL;
	}

        if(lex_word[i1].content!=NULL){
	   free(lex_word[i1].content);
	   lex_word[i1].content=NULL;
	}
   }
   p_lex_word=lex_word;

   fopen_s(&yyin, file_path, "r");
   yylex();
   fclose(yyin);
   *beg=lex_word;
   *end=p_lex_word;
}



int yywrap()//此函数必须由用户提供

{
    return 1;
}
