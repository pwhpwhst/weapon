#ifndef LEX_YY_H_PWH
#define LEX_YY_H_PWH
struct C_Lex_Word{
	char* type;
	char* content;
};

extern void c_word_parser(char* file_path,struct C_Lex_Word **beg,struct C_Lex_Word **end);


#endif