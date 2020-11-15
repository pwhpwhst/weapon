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

sign (\+|\-)
unsigned-suffix	(u|U)
long-suffix	(l|L)
floating-suffix	(f|F|l|L)
integer-suffix	(((u|U)(l|L)?)|((l|L)(u|U)?))
hexadecimal-digit [0-9a-fA-F]
octal-digit [0-7]
nonzero-digit [1-9]
digit [0-9]
hex-quad [0-9a-fA-F][0-9a-fA-F][0-9a-fA-F][0-9a-fA-F]
universal-character-name \\u{hex-quad}|\\U{hex-quad}{hex-quad}
nondigit ({universal-character-name}|[_a-zA-Z])
digit-sequence {digit}+
fractional-constant ({digit-sequence}?\.{digit-sequence}|{digit-sequence}\.)
exponent-part (e|E){sign}?{digit-sequence}
octal-escape-sequence (\\{octal-digit}|\\{octal-digit}{octal-digit}|\\{octal-digit}{octal-digit}{octal-digit})
simple-escape-sequence (\\'|\\\"|\\\?|\\\\|\\a|\\b|\\f|\\n|\\r|\\t|\\v)
hexadecimal-escape-sequence \\x[0-9a-fA-F]+
escape-sequence ({octal-escape-sequence}|{simple-escape-sequence}|{hexadecimal-escape-sequence})
pp-number \.?{digit}({digit}|{nondigit}|(e|E){sign}|\.)+
preprocessing-op-or-punc (\{|\}|\[|\]|\#|\#\#|\(|\)|\<\:|\:\>|\<\%|\%\>|\%\:|\%\:\%\:|\;|\:|\.\.\.|new|delete|\?|\:\:|\.|\.\*|\+|\-|\*|\/|\%|\ˆ|\&|\||\˜|\!|\=|\<|\>|\+\=|\-\=|\*\=|\/\=|\%\=|\ˆ\=|\&\=|\|\=|\<\<|\>\>|\>\>\=|\<\<\=|\=\=|\!\=|\<\=|\>\=|\&\&|\|\||\+\+|\-\-|\,|\-\>\*|\-\>|and|and_eq|bitand|bitor|compl|not|not_eq|or|or_eq|xor|xor_eq)
octal-literal 0{octal-digit}+
hexadecimal-literal (0x|0X)[0-9a-fA-F]+
decimal-literal [1-9][0-9]*
integer-literal ({decimal-literal}|{octal-literal}|{hexadecimal-literal})({integer-suffix})?
floating-literal ({fractional-constant}{exponent-part}?{floating-suffix}?|{digit-sequence}{exponent-part}{floating-suffix}?)
s-char [^\"\\\n]|{escape-sequence}|{universal-character-name}
c-char [^\'\\\n]|{escape-sequence}|{universal-character-name}
h-char [^\"\n]
q-char [^\>\n]
s-char-sequence {s-char}+
c-char-sequence {c-char}+ 
h-char-sequence {h-char}+
q-char-sequence {q-char}+
string-literal L?\"{s-char-sequence}?\"
character-literal L?\'{c-char-sequence}?\'
boolean-literal true|false
header-name \<{h-char-sequence}\>|\"{q-char-sequence}\"
literal {string-literal}|{character-literal}|{boolean-literal}|{floating-literal}|{integer-literal}
keyword alignas|alignof|and|and_eq|asm|atomic_cancel|atomic_commit|atomic_noexcept|auto|bitand|bitor|bool|break|case|catch|char|char8_t|char16_t|char32_t|class|compl|concept|const|consteval|constexpr|constinit|const_cast|continue|co_await|co_return|co_yield|decltype|default|delete|do|double|dynamic_cast|else|enum|explicit|export|extern|false|float|for|friend|goto|if|inline|int|long|mutable|namespace|new|noexcept|not|not_eq|nullptr|operator|or|or_eq|private|protected|public|reflexpr|register|reinterpret_cast|requires|return|short|signed|sizeof|static|static_assert|static_cast|struct|switch|synchronized|template|this|thread_local|throw|true|try|typedef|typeid|typename|union|unsigned|using|virtual|void|volatile|wchar_t|while|xor|xor_eq
operator new|delete|new\[\]|delete\[\]|\+|\-|\*|\/|\%|\ˆ|\&|\||\˜|\!|\=|\<|\>|\+\=|\-\=|\*\=|\/\=|\%\=|\ˆ\=|\&\=|\|\=|\<\<|\>\>|\>\>\=|\<\<\=|\=\=|\!\=|\<\=|\>\=|\&\&|\|\||\+\+|\-\-|\,|\-\>\*|\-\>|\(\)|\[\]
token {identifier}|{keyword}|{literal}|{operator}
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


{string-literal} {
 write_word(p_lex_word,yytext,"'string-literal'");
  p_lex_word++;
}


{character-literal} {
 write_word(p_lex_word,yytext,"'character-literal'");
  p_lex_word++;
}



{floating-literal} {
 write_word(p_lex_word,yytext,"'floating-literal'");
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
