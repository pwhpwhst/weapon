#ifndef COMMON_H
#define COMMON_H
#include<bitset>
#include<string>
#include<time.h>
#include "h/common.hpp"
using namespace std;
bitset<64> charToBitset(const char s[8])  
{  
    bitset<64> bits;  
    for(int i=0; i<8; ++i)  
        for(int j=0; j<8; ++j)  
            bits[i*8+j] = ((s[i]>>j) & 1);  
    return bits;  
} 


string bitset64ToStr(bitset<64> bits){
	char chr[9];
	for(size_t i1=0;i1<9;i1++){
		chr[i1]=0;
	}
	for(size_t i1=0;i1!=64;i1++){
		chr[i1/8]|=bits[i1]<<(i1%8);
	}
	return string(chr);
 }



 bitset<64> generateSecretKey(){
 srand((unsigned)time(NULL));
	bitset<64> u;
	for(size_t i1=0;i1!=64;i1++){
		int a=rand();
		u[i1]=(a%2==0)?0:1;
	}
	return u;
 }

 #endif