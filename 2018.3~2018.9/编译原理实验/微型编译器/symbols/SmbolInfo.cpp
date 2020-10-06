#include "SmbolInfo.h"
#include "Tag.h"
#include<iostream>
using namespace std;

SmbolInfo SmbolInfo::DEFAULT_SMBOLINFO=SmbolInfo(Tag::DEFAULT);

SmbolInfo::SmbolInfo(){

}

SmbolInfo::SmbolInfo(int tag){
    this->tag=tag;
}

SmbolInfo::~SmbolInfo(){


}





