#pragma once
#include <memory>
using namespace std;

class Lex_Word{
public:	string type;
public:	string content;

//默认构造器
public: Lex_Word();

//自定义构造器
public: Lex_Word(const string &type,const string &content);

//容器
public: Lex_Word(const Lex_Word &lex_word);

public: virtual ~Lex_Word();

};

typedef std::shared_ptr<Lex_Word> P_Lex_Word;

void word_parser(const string& path,vector<P_Lex_Word> &lex_word_list);