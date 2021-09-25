#include "Item.h"
#include "Rule.h"
using namespace std;





Item::Item(){
}

Item::Item(const P_Rule &rule,int status){
this->rule=rule;
this->status=status;
}


Item::Item(const Item &item){
this->rule=item.rule;
this->status=item.status;
}


Item::~Item(){
this->rule=nullptr;

}




