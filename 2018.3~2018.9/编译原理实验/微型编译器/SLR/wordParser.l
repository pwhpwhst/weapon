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

integer-suffix	(((u|U)(l|L)?)|((l|L)(u|U)?))
octal-digit [0-7]
digit [0-9]
hex-quad [0-9a-fA-F][0-9a-fA-F][0-9a-fA-F][0-9a-fA-F]
universal-character-name \\u{hex-quad}|\\U{hex-quad}{hex-quad}
nondigit ({universal-character-name}|[_a-zA-Z])
octal-literal 0{octal-digit}+
hexadecimal-literal (0x|0X)[0-9a-fA-F]+
decimal-literal [1-9][0-9]*
integer-literal ({decimal-literal}|{octal-literal}|{hexadecimal-literal})({integer-suffix})?
identifier {nondigit}({nondigit}|{digit})*

%%




{identifier} {
if(strcmp(yytext,"char")==0){
	write_word(p_lex_word,yytext,"'simple-type-specifier'");
}else if(strcmp(yytext,"wchar_t")==0){
	write_word(p_lex_word,yytext,"'simple-type-specifier'");
}else if(strcmp(yytext,"bool")==0){
	write_word(p_lex_word,yytext,"'simple-type-specifier'");
}else if(strcmp(yytext,"short")==0){
	write_word(p_lex_word,yytext,"'simple-type-specifier'");
}else if(strcmp(yytext,"int")==0){
	write_word(p_lex_word,yytext,"'simple-type-specifier'");
}else if(strcmp(yytext,"long")==0){
	write_word(p_lex_word,yytext,"'simple-type-specifier'");
}else if(strcmp(yytext,"signed")==0){
	write_word(p_lex_word,yytext,"'simple-type-specifier'");
}else if(strcmp(yytext,"unsigned")==0){
	write_word(p_lex_word,yytext,"'simple-type-specifier'");
}else if(strcmp(yytext,"float")==0){
	write_word(p_lex_word,yytext,"'simple-type-specifier'");
}else if(strcmp(yytext,"double")==0){
	write_word(p_lex_word,yytext,"'simple-type-specifier'");
}else if(strcmp(yytext,"void")==0){
	write_word(p_lex_word,yytext,"'simple-type-specifier'");
}else if(strcmp(yytext,"true")==0||strcmp(yytext,"false")==0){
	write_word(p_lex_word,yytext,"'boolean-literal'");
}else{
	write_word(p_lex_word,yytext,"'identifier'");
}
  p_lex_word++;
}


{integer-literal} {
 write_word(p_lex_word,yytext,"'integer-literal'");
  p_lex_word++;
}


"=" {
  write_word(p_lex_word,yytext,"'='");
  p_lex_word++;
}


";" {
  write_word(p_lex_word,yytext,"';'");
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



int yywrap()

{
    return 1;
}
