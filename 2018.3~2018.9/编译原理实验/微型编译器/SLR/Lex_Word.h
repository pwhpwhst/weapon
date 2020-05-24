#pragma once
#include <memory>
using namespace std;

class Lex_Word{
public:	string type;
public:	string content;

//Ĭ�Ϲ�����
public: Lex_Word();

//�Զ��幹����
public: Lex_Word(const string &type,const string &content);

//����
public: Lex_Word(const Lex_Word &lex_word);

public: virtual ~Lex_Word();

};

typedef std::shared_ptr<Lex_Word> P_Lex_Word;

void word_parser(const string& path,vector<P_Lex_Word> &lex_word_list);