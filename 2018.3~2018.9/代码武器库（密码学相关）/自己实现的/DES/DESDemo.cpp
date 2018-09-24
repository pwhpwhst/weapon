#ifndef DES_DEMO_H
#define DES_DEMO_H
#include<iostream>
#include<bitset>
#include "h/common.hpp"
#include "h/DESDemo.hpp"
using namespace std;



bitset<64> DESUtil::secret_key=std::bitset<64>("0011100000110111001101100011010100110100001100110011001000110001");


int DESUtil::password_substitute1[]={1,2,3,4,5,6,7,
							9,10,11,12,13,14,15,
							17,18,19,20,21,22,23,
							25,26,27,28,29,30,31,
							33,34,35,36,37,38,39,
							41,42,43,44,45,46,47,
							49,50,51,52,53,54,55,
							57,58,59,60,61,62,63};

int DESUtil::shiftBits[]={1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1};

int DESUtil::password_substitute2[]={14, 17, 11, 24,  1,  5,  
               3, 28, 15,  6, 21, 10,  
              23, 19, 12,  4, 26,  8,  
              16,  7, 27, 20, 13,  2,  
              41, 52, 31, 37, 47, 55,  
              30, 40, 51, 45, 33, 48,  
              44, 49, 39, 56, 34, 53,  
              46, 42, 50, 36, 29, 32};  

int DESUtil::IP[]={58, 50, 42, 34, 26, 18, 10, 2,  
            60, 52, 44, 36, 28, 20, 12, 4,  
            62, 54, 46, 38, 30, 22, 14, 6,  
            64, 56, 48, 40, 32, 24, 16, 8,  
            57, 49, 41, 33, 25, 17, 9,  1,  
            59, 51, 43, 35, 27, 19, 11, 3,  
            61, 53, 45, 37, 29, 21, 13, 5,  
            63, 55, 47, 39, 31, 23, 15, 7};

int DESUtil::E[]={32,  1,  2,  3,  4,  5,  
            4,  5,  6,  7,  8,  9,  
            8,  9, 10, 11, 12, 13,  
           12, 13, 14, 15, 16, 17,  
           16, 17, 18, 19, 20, 21,  
           20, 21, 22, 23, 24, 25,  
           24, 25, 26, 27, 28, 29,  
           28, 29, 30, 31, 32,  1};

int DESUtil::S_BOX[8][4][16]={  
    {    
        {14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7},    
        {0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8},    
        {4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0},   
        {15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13}   
    },  
    {    
        {15,1,8,14,6,11,3,4,9,7,2,13,12,0,5,10},    
        {3,13,4,7,15,2,8,14,12,0,1,10,6,9,11,5},   
        {0,14,7,11,10,4,13,1,5,8,12,6,9,3,2,15},    
        {13,8,10,1,3,15,4,2,11,6,7,12,0,5,14,9}    
    },   
    {    
        {10,0,9,14,6,3,15,5,1,13,12,7,11,4,2,8},    
        {13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1},    
        {13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7},    
        {1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12}    
    },   
    {    
        {7,13,14,3,0,6,9,10,1,2,8,5,11,12,4,15},    
        {13,8,11,5,6,15,0,3,4,7,2,12,1,10,14,9},    
        {10,6,9,0,12,11,7,13,15,1,3,14,5,2,8,4},    
        {3,15,0,6,10,1,13,8,9,4,5,11,12,7,2,14}    
    },  
    {    
        {2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9},    
        {14,11,2,12,4,7,13,1,5,0,15,10,3,9,8,6},    
        {4,2,1,11,10,13,7,8,15,9,12,5,6,3,0,14},    
        {11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3}    
    },  
    {    
        {12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11},    
        {10,15,4,2,7,12,9,5,6,1,13,14,0,11,3,8},    
        {9,14,15,5,2,8,12,3,7,0,4,10,1,13,11,6},    
        {4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13}    
    },   
    {    
        {4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1},    
        {13,0,11,7,4,9,1,10,14,3,5,12,2,15,8,6},    
        {1,4,11,13,12,3,7,14,10,15,6,8,0,5,9,2},    
        {6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12}    
    },   
    {    
        {13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7},    
        {1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2},    
        {7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8},    
        {2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11}    
    }   
};

int DESUtil::P[]={16,  7, 20, 21,  
           29, 12, 28, 17,  
            1, 15, 23, 26,  
            5, 18, 31, 10,  
            2,  8, 24, 14,  
           32, 27,  3,  9,  
           19, 13, 30,  6,  
           22, 11,  4, 25 };


int DESUtil::IP_1[]={40, 8, 48, 16, 56, 24, 64, 32,  
              39, 7, 47, 15, 55, 23, 63, 31,  
              38, 6, 46, 14, 54, 22, 62, 30,  
              37, 5, 45, 13, 53, 21, 61, 29,  
              36, 4, 44, 12, 52, 20, 60, 28,  
              35, 3, 43, 11, 51, 19, 59, 27,  
              34, 2, 42, 10, 50, 18, 58, 26,  
              33, 1, 41,  9, 49, 17, 57, 25};



bitset<64> DESUtil::oper(bitset<64> input,int flag){

    //准备16轮子密钥 begin
	cout<<"secret_key:"<<secret_key<<endl;


	bitset<56> secret_key56;
    for(int i1=0; i1<56; ++i1){
		secret_key56[55-i1] = secret_key[64-password_substitute1[i1]]; 
	}
	cout<<"secret_key56:"<<secret_key56<<endl;


	bitset<48> sub_keys[16];
	for(int i1=0;i1<16;i1++){
		int shift_num=0;
		for(int i2=0;i2<=i1;i2++){
			shift_num+=shiftBits[i2];
		}

		bitset<28> left;
		bitset<28> rigth;
		for(int i2=0;i2<28;i2++){
			left[27-i2] = secret_key56[55-i2];  
			rigth[27-i2] = secret_key56[27-i2];  
		}

		left=(left>>(28-shift_num))|(left<<shift_num);
		rigth=(rigth>>(28-shift_num))|(rigth<<shift_num);

		bitset<56> _secret_key56;

		for(int i2=0;i2<28;i2++){
			 _secret_key56[55-i2]=left[27-i2];
			 _secret_key56[27-i2]= rigth[27-i2];
		}


		if(flag==0){
			for(int i2=0; i2<48; ++i2){
				sub_keys[i1][47-i2]=_secret_key56[56-password_substitute2[i2]];
			}
		}else{
			for(int i2=0; i2<48; ++i2){
				sub_keys[15-i1][47-i2]=_secret_key56[56-password_substitute2[i2]];
			}
		}

	}
	//准备16轮子密钥 end


	// 明文初始置换 begin
	bitset<64> bit64_afterIP;
	for(int i=0; i<64; i++)  
		bit64_afterIP[63-i] = input[64-IP[i]];
	// 明文初始置换 end


bitset<64> _bit64_afterIP=bit64_afterIP;

//16轮 begin

for(int i1=0;i1<16;i1++){
	bitset<32> left_text;
	bitset<32> right_text;

	//分成左右明文 begin
	for(int i2=0;i2<32;i2++){
		left_text[31-i2] = _bit64_afterIP[63-i2]; 
		right_text[31-i2] = _bit64_afterIP[31-i2]; 
	}
	//分成左右明文 end

	//右明文拓展置换成48 begin
	bitset<48> right_text48;
	 for(int i2=0; i2<48; ++i2)  
        right_text48[47-i2] = right_text[32-E[i2]]; 
	//右明文拓展置换成48 end

	//与子密钥异或 begin
	bitset<48> _right_text48;
	_right_text48=right_text48^sub_keys[i1];
	//与子密钥异或 end

	//S盒替换 begin
	bitset<32> _right_text32;
	for(int i2=0;i2<8;i2++){
		bitset<6> u;
		u[5]=_right_text48[47-6*i2];
		u[4]=_right_text48[46-6*i2];
		u[3]=_right_text48[45-6*i2];
		u[2]=_right_text48[44-6*i2];
		u[1]=_right_text48[43-6*i2];
		u[0]=_right_text48[42-6*i2];

		int num=S_BOX[i2][u[5]*2+u[0]][u[4]*8+u[3]*4+u[2]*2+u[1]];
		bitset<4> u2(num);
		_right_text32[31-4*i2]=u2[3];
		_right_text32[30-4*i2]=u2[2];
		_right_text32[29-4*i2]=u2[1];
		_right_text32[28-4*i2]=u2[0];
	}
	//S盒替换 end

	//P盒替换 begin
	bitset<32> right_text32;
    for(int i2=0; i2<32; ++i2)  
        right_text32[31-i2] = _right_text32[32-P[i2]];  
	//P盒替换 end

	bitset<32> new_right_text32;
	new_right_text32=right_text32^left_text;
	
	for(int i2=0; i2<32; ++i2) {
		_bit64_afterIP[31-i2] = new_right_text32[31-i2]; 
		_bit64_afterIP[63-i2] = right_text[31-i2];
	}


}
//16轮 end

//左右32位交换
bool bit;
	for(int i1=0; i1<32; ++i1) {
		bit=_bit64_afterIP[i1];
		_bit64_afterIP[i1]=_bit64_afterIP[i1+32];
		_bit64_afterIP[i1+32]=bit;
	}
//最终置换
	bitset<64> result;
    for(int i2=0; i2<64; ++i2)  
        result[63-i2] = _bit64_afterIP[64-IP_1[i2]];  

	return result;
}


bitset<64> DESUtil::encrpt(bitset<64> input){
	return oper(input,0);
}

bitset<64> DESUtil::decrpt(bitset<64> input){
	return oper(input,1);
}

int main(){
	DESUtil desUtil;
	bitset<64> ori_bit64("0011100000110111001101100011010100110100001100110011001000110001");
	cout<<"ori_bit64:"<<ori_bit64<<endl;
	bitset<64> encrpt_bit64=desUtil.encrpt(ori_bit64);
	cout<<"encrpt_bit64:"<<encrpt_bit64<<endl;
	bitset<64> decrpt_bit64=desUtil.decrpt(encrpt_bit64);
	cout<<"decrpt_bit64:"<<decrpt_bit64<<endl;
	return 0;
}

#endif
