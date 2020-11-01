#include "SmbolInfo.h"
#include "Tag.h"
#include<iostream>
using namespace std;

SmbolInfo SmbolInfo::DEFAULT_SMBOLINFO=SmbolInfo(Tag::DEFAULT);

int SmbolInfo::REGIST_SEQ=0;

SmbolInfo::SmbolInfo(){

}

SmbolInfo::SmbolInfo(int tag){
    this->tag=tag;
}

SmbolInfo::~SmbolInfo(){


}





