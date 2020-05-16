#include "Extra.h"

int endsWith(string s,string sub){
        return s.rfind(sub)==(s.length()-sub.length())?1:0;
}