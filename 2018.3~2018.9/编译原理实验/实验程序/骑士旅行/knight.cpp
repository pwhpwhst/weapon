#include<iostream>
#include<vector>
#include<memory>

struct Element
{
	int x;
	int y;
	int move;
	int step_index;
	unsigned char visited;
};
using namespace std;

int main(){


	string ss1="haha1";

	
	int knight_board[8][8];
	
	
	
	
	
	for(int i1=0;i1<8;i1++){
		for(int i2=0;i2<8;i2++){
			knight_board[i1][i2]=-1;
		}
	}

/**

CPU Disasm
Address   Hex dump          Command                                                               Comments
004016A7  |.  C745 E4 00000 MOV DWORD PTR SS:[LOCAL.8],0	（o）
004016AE  |.  EB 31         JMP SHORT 004016E1				（o）
004016B0  |>  C745 E0 00000 /MOV DWORD PTR SS:[LOCAL.9],0	（o）
004016B7  |.  EB 1E         |JMP SHORT 004016D7				（o）
004016B9  |>  8B45 E4       |/MOV EAX,DWORD PTR SS:[LOCAL.8]	（o）
004016BC  |.  8D14C5 000000 ||LEA EDX,[EAX*8]	（o）
004016C3  |.  8B45 E0       ||MOV EAX,DWORD PTR SS:[LOCAL.9]	（o）
004016C6  |.  01D0          ||ADD EAX,EDX	（o）
004016C8  |.  C78485 50FEFF ||MOV DWORD PTR SS:[EAX*4+EBP-1B0],-1 //EBP-1B0是基地址？
004016D3  |.  8345 E0 01    ||ADD DWORD PTR SS:[LOCAL.9],1	（o）
004016D7  |>  837D E0 07    ||CMP DWORD PTR SS:[LOCAL.9],7	（o）
004016DB  |.^ 7E DC         |\JLE SHORT 004016B9	（o）
004016DD  |.  8345 E4 01    |ADD DWORD PTR SS:[LOCAL.8],1	（o）
004016E1  |>  837D E4 07    |CMP DWORD PTR SS:[LOCAL.8],7	（o）
004016E5  |.^ 7E C9         \JLE SHORT 004016B0	（o）

*/


	



	vector<shared_ptr<Element>> stack;
/**

004016C4  |.  8D45 94                            LEA EAX,[LOCAL.28]
004016C7  |.  C785 18FEFFFF 02000000             MOV DWORD PTR SS:[LOCAL.123],2
004016D1  |.  89C1                               MOV ECX,EAX
004016D3  |.  E8 F8890600                        CALL 0046A0D0

*/








	shared_ptr<Element> ele=shared_ptr<Element>(new Element());

/**
CPU Disasm
Address   Hex dump          Command                                  Comments
004016FB  |.  C70424 140000 MOV DWORD PTR SS:[LOCAL.131],14
00401702  |.  C785 18FEFFFF MOV DWORD PTR SS:[LOCAL.123],3
0040170C  |.  E8 1F470700   CALL 00475E30
00401711  |.  C700 00000000 MOV DWORD PTR DS:[EAX],0
00401717  |.  C740 04 00000 MOV DWORD PTR DS:[EAX+4],0
0040171E  |.  C740 08 00000 MOV DWORD PTR DS:[EAX+8],0
00401725  |.  C740 0C 00000 MOV DWORD PTR DS:[EAX+0C],0
0040172C  |.  C640 10 00    MOV BYTE PTR DS:[EAX+10],0
00401730  |.  8D95 78FFFFFF LEA EDX,[LOCAL.35]
00401736  |.  890424        MOV DWORD PTR SS:[LOCAL.131],EAX         ; /Arg1
00401739  |.  89D1          MOV ECX,EDX                              ; |
0040173B  |.  E8 8CCC0400   CALL 0044E3CC                            ; \knight.0044E3CC
00401740  |.  83EC 04       SUB ESP,4

*/



int uuu1[5]={1,2,3,4,5};
	ele->x=0;
/**
CPU Disasm
Address          Hex dump                          Command                                             Comments
00401746         |.  8D45 8C                       LEA EAX,[LOCAL.30]
00401749         |.  89C1                          MOV ECX,EAX
0040174B         |.  E8 10340200                   CALL 00424B60
00401750         |.  C700 00000000                 MOV DWORD PTR DS:[EAX],0


CPU Disasm
Address          Hex dump                          Command                                             Comments
00401799         |.  8D45 8C                       LEA EAX,[LOCAL.30]
0040179C         |.  89C1                          MOV ECX,EAX
0040179E         |.  E8 BD330200                   CALL 00424B60
004017A3         |.  C740 08 FFFFFFFF              MOV DWORD PTR DS:[EAX+8],-1



CPU Disasm
Address          Hex dump                          Command                                             Comments
00424B60         /$  55                            PUSH EBP
00424B61         |.  89E5                          MOV EBP,ESP
00424B63         |.  83EC 04                       SUB ESP,4
00424B66         |.  894D FC                       MOV DWORD PTR SS:[LOCAL.1],ECX
00424B69         |.  8B45 FC                       MOV EAX,DWORD PTR SS:[LOCAL.1]
00424B6C         |.  8B00                          MOV EAX,DWORD PTR DS:[EAX]
00424B6E         |.  C9                            LEAVE
00424B6F         \.  C3                            RETN

ESP 堆栈指针寄存器
EBP 基址指针寄存器

LEAVE 相当于 
MOV SP,BP
POP BP

SS放临时变量，DS放函数里面的局部变量
*/
int uuu2[5]={1,2,3,4,5};
	ele->y=0;
	ele->move=-1;
	ele->step_index=0;
	ele->visited=0;
	stack.push_back(ele);
	knight_board[0][0]=0;


	while(stack.size()<=63){
		shared_ptr<Element> ele=stack.back();
			int next_move=-1;
			int avail_step=99;
			for(int i1=0;i1<8;i1++){// i1 SS:[LOCAL.12]
				
				int u=((ele->visited<<i1)%256)>>7;
				if(u==0){//u SS:[LOCAL.21]
					int next_x=0;//SS:[LOCAL.13]
					int next_y=0;//SS:[LOCAL.14]
					if(i1==0){
						next_x=ele->x+1;
						next_y=ele->y-2;
					}else if(i1==1){
						next_x=ele->x+2;
						next_y=ele->y-1;					
					}else if(i1==2){
						next_x=ele->x+2;
						next_y=ele->y+1;					
					}else if(i1==3){
						next_x=ele->x+1;
						next_y=ele->y+2;					
					}else if(i1==4){
						next_x=ele->x-1;
						next_y=ele->y+2;					
					}else if(i1==5){
						next_x=ele->x-2;
						next_y=ele->y+1;					
					}else if(i1==6){
						next_x=ele->x-2;
						next_y=ele->y-1;					
					}else if(i1==7){
						next_x=ele->x-1;
						next_y=ele->y-2;					
					}



					if(next_x<0||next_x>7||next_y<0||next_y>7||knight_board[next_x][next_y]!=-1){
						unsigned char u1=1;
						ele->visited|=(u1<<7-i1);
						continue;
					}else{
						int _avail_step=0;
						for(int i2=0;i2<8;i2++){
							int next_next_x=0;
							int next_next_y=0;
							if(i2==0){
								next_next_x=next_x+1;
								next_next_y=next_y-2;
							}else if(i2==1){
								next_next_x=next_x+2;
								next_next_y=next_y-1;					
							}else if(i2==2){
								next_next_x=next_x+2;
								next_next_y=next_y+1;					
							}else if(i2==3){
								next_next_x=next_x+1;
								next_next_y=next_y+2;					
							}else if(i2==4){
								next_next_x=next_x-1;
								next_next_y=next_y+2;					
							}else if(i2==5){
								next_next_x=next_x-2;
								next_next_y=next_y+1;					
							}else if(i2==6){
								next_next_x=next_x-2;
								next_next_y=next_y-1;					
							}else if(i2==7){
								next_next_x=next_x-1;
								next_next_y=next_y-2;					
							}
							
							if(next_next_x>=0&&next_next_x<8&&next_next_y>=0&&next_next_y<8&&knight_board[next_next_x][next_next_y]==-1){
								_avail_step++;
							}
						}
						
						if(_avail_step<avail_step){
							next_move=i1;
							avail_step=_avail_step;
						}

					}


				}
			}

			if(next_move!=-1){

				shared_ptr<Element> _ele=shared_ptr<Element>(new Element());
				if(next_move==0){
					_ele->x=ele->x+1;
					_ele->y=ele->y-2;
				}else if(next_move==1){
					_ele->x=ele->x+2;
					_ele->y=ele->y-1;					
				}else if(next_move==2){
					_ele->x=ele->x+2;
					_ele->y=ele->y+1;					
				}else if(next_move==3){
					_ele->x=ele->x+1;
					_ele->y=ele->y+2;					
				}else if(next_move==4){
					_ele->x=ele->x-1;
					_ele->y=ele->y+2;					
				}else if(next_move==5){
					_ele->x=ele->x-2;
					_ele->y=ele->y+1;					
				}else if(next_move==6){
					_ele->x=ele->x-2;
					_ele->y=ele->y-1;					
				}else if(next_move==7){
					_ele->x=ele->x-1;
					_ele->y=ele->y-2;					
				}

				knight_board[_ele->x][_ele->y]=ele->step_index+1;
				_ele->move=next_move;
				_ele->step_index=ele->step_index+1;
				_ele->visited=0;
				stack.push_back(_ele);

			}else{
				int invaliable_move=ele->move; //invaliable_move SS:[LOCAL.23] ele的地址 [LOCAL.43]
				knight_board[ele->x][ele->y]=-1; //MOV DWORD PTR SS:[EAX*4+EBP-1B0],-1
				stack.pop_back();
				unsigned char u1=1;
				stack.back()->visited|=(u1<<7-invaliable_move);
			}
			

		
	}


//i2 SS:[LOCAL.19] i1 SS:[LOCAL.20]
	for(int i2=0;i2<8;i2++){
		for(int i1=0;i1<8;i1++){
			cout<<knight_board[i1][i2]<<","; //0044A2D0  
		}
			cout<<endl; //0044A010
	}

	string ss2="haha2";

}

/**

int knight_board[8][8];

CPU Disasm
Address   Hex dump          Command                                  Comments
00401666  |.  C785 48FEFFFF MOV DWORD PTR SS:[LOCAL.111],1           ; |
00401670  |.  89C1          MOV ECX,EAX                              ; |
00401672  |.  E8 F9A90400   CALL 0044C070                            ; \knight.0044C070
00401677  |.  83EC 08       SUB ESP,8
0040167A  |.  8D45 A5       LEA EAX,[LOCAL.24+1]
0040167D  |.  89C1          MOV ECX,EAX
0040167F  |.  E8 3CF00300   CALL 004406C0
00401684  |.  8D45 A6       LEA EAX,[LOCAL.24+2]
00401687  |.  89C1          MOV ECX,EAX
00401689  |.  E8 02F00300   CALL 00440690                            ; [knight.00440690
0040168E  |.  8D45 9C       LEA EAX,[LOCAL.26]
00401691  |.  8D55 A6       LEA EDX,[LOCAL.24+2]
00401694  |.  895424 04     MOV DWORD PTR SS:[LOCAL.118],EDX         ; /Arg2



*/


/**

MOV	319
LEA	89
CALL	86
CMP	44
ADD	39
SUB	35
JMP	30
JNE	26
MOVZX	7
JLE	6
JG	4
JS	4
SHL	4
JE	2
OR	2
SAR	2
JGE	1
SETBE	1
SHR	1
TEST	1



*/





/*
所有的调用
141 0040B590 函数开头会调用的方法？
113 0040A7D0 函数开头会调用的方法？
1289 00440750 REP RETN
1645 0044C130 字符串构造函数
1290 00440780 REP RETN
2554 0046A0D0 vector<shared_ptr<Element>>
3014 00475E10 new Element()
1752 0044E3AC shared_ptr<Element>(new Element());
909 00424B60 解地址操作*
1753 0044E3CC ele的构析函数
2553 0046A070 stack.push_back
2552 0046A040 stack.pop_back()
2550 00469F9C stack.back()
988  00425ED4 stack.size()
1751 0044E38C shared_ptr<Element> ele=stack.back(); 中的=
1289 00440750	REP RETN
1290 00440780	REP RETN
1670 0044C7A0	string 的构析函数
1548 0044A2D0 cout<<knight_board[i1][i2]
2913 004737D0 cout<<","
1538 0044A010 cout<<endl;
2555 0046A0E8 stack 的构析
*/

/**
2553 0046A070 stack.push_back

0046A070  /$  55                                 PUSH EBP                                                             ; knight.0046A070(guessed Arg1)
0046A071  |.  89E5                               MOV EBP,ESP
0046A073  |.  83EC 18                            SUB ESP,18
0046A076  |.  894D F4                            MOV DWORD PTR SS:[LOCAL.3],ECX
0046A079  |.  8B45 F4                            MOV EAX,DWORD PTR SS:[LOCAL.3]
0046A07C  |.  8B50 04                            MOV EDX,DWORD PTR DS:[EAX+4]
0046A07F  |.  8B45 F4                            MOV EAX,DWORD PTR SS:[LOCAL.3]
0046A082  |.  8B40 08                            MOV EAX,DWORD PTR DS:[EAX+8]
0046A085  |.  39C2                               CMP EDX,EAX
0046A087  |.  74 2D                              JE SHORT 0046A0B6
0046A089  |.  8B45 F4                            MOV EAX,DWORD PTR SS:[LOCAL.3]
0046A08C  |.  8B50 04                            MOV EDX,DWORD PTR DS:[EAX+4]
0046A08F  |.  8B45 F4                            MOV EAX,DWORD PTR SS:[LOCAL.3]
0046A092  |.  8B4D 08                            MOV ECX,DWORD PTR SS:[ARG.1]
0046A095  |.  894C24 08                          MOV DWORD PTR SS:[LOCAL.4],ECX
0046A099  |.  895424 04                          MOV DWORD PTR SS:[LOCAL.5],EDX
0046A09D  |.  890424                             MOV DWORD PTR SS:[LOCAL.6],EAX
0046A0A0  |.  E8 5F9AFFFF                        CALL 00463B04
0046A0A5  |.  8B45 F4                            MOV EAX,DWORD PTR SS:[LOCAL.3]
0046A0A8  |.  8B40 04                            MOV EAX,DWORD PTR DS:[EAX+4]
0046A0AB  |.  8D50 08                            LEA EDX,[EAX+8]
0046A0AE  |.  8B45 F4                            MOV EAX,DWORD PTR SS:[LOCAL.3]
0046A0B1  |.  8950 04                            MOV DWORD PTR DS:[EAX+4],EDX
0046A0B4  |.  EB 13                              JMP SHORT 0046A0C9
0046A0B6  |>  8B45 F4                            MOV EAX,DWORD PTR SS:[LOCAL.3]
0046A0B9  |.  8B55 08                            MOV EDX,DWORD PTR SS:[ARG.1]
0046A0BC  |.  891424                             MOV DWORD PTR SS:[LOCAL.6],EDX                                       ; /Arg1 => [ARG.1]
0046A0BF  |.  89C1                               MOV ECX,EAX                                                          ; |
0046A0C1  |.  E8 5AFCFFFF                        CALL 00469D20                                                        ; \knight.00469D20
0046A0C6  |.  83EC 04                            SUB ESP,4
0046A0C9  |>  C9                                 LEAVE
0046A0CA  \.  C2 0400                            RETN 4

*/