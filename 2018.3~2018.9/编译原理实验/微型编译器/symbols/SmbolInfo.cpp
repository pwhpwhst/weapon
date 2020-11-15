#include "SmbolInfo.h"
#include "Tag.h"
#include<iostream>
using namespace std;

SmbolInfo SmbolInfo::DEFAULT_SMBOLINFO=SmbolInfo(Tag::DEFAULT);

int SmbolInfo::REGIST_SEQ=0;

SmbolInfo::SmbolInfo(){
    identifier_name="";
}

SmbolInfo::SmbolInfo(int tag){
     identifier_name="";
    this->tag=tag;
}

 SmbolInfo& SmbolInfo:: operator = (const SmbolInfo& smbolInfo){
   this->tag=smbolInfo.tag;
   this->identifier_name=smbolInfo.identifier_name;
   if(smbolInfo.type!=nullptr){
     this->type = smbolInfo.type->clone();
   }else{
    this->type =nullptr;
   }

    this->registNum=smbolInfo.registNum;
    return *this;
}



SmbolInfo::~SmbolInfo(){


}


