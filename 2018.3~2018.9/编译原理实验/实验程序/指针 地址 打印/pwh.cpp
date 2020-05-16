#include<iostream>
using namespace std;
int main(){

int a1=1;
char u1='a';
int *a2=&a1;
char u4='a';
a1=*a2;
char u2='a';
	cout<<"a1="<<a1<<endl;
char u3='a';
}





/**

int *a2=&a1;


CPU Disasm
Address   Hex dump          Command                                  Comments
00401570  |.  8D45 D8       LEA EAX,[LOCAL.11]						//计算 [LOCAL.11]的地址，并将结果移入寄存器 EAX
00401573  |.  8945 E0       MOV DWORD PTR SS:[LOCAL.9],EAX


*/

/**

a1=*a2;

CPU Disasm
Address   Hex dump          Command                                  Comments
0040157A  |.  8B45 E0       MOV EAX,DWORD PTR SS:[LOCAL.9]
0040157D  |.  8B00          MOV EAX,DWORD PTR DS:[EAX]
0040157F  |.  8945 D8       MOV DWORD PTR SS:[LOCAL.11],EAX

*/


/**
打印
CPU Disasm
Address   Hex dump          Command                                  Comments
0040157A  |.  8B45 D8       MOV EAX,DWORD PTR SS:[LOCAL.11]
0040157D  |.  8945 A0       MOV DWORD PTR SS:[LOCAL.25],EAX
00401580  |.  C74424 04 00F MOV DWORD PTR SS:[LOCAL.30],OFFSET 0047F ; ASCII "a1="
00401588  |.  C70424 60DF47 MOV DWORD PTR SS:[LOCAL.31],OFFSET 0047D
0040158F  |.  C745 A8 01000 MOV DWORD PTR SS:[LOCAL.23],1
00401596  |.  E8 D5020700   CALL 00471870
0040159B  |.  8B75 A0       MOV ESI,DWORD PTR SS:[LOCAL.25]
0040159E  |.  893424        MOV DWORD PTR SS:[LOCAL.31],ESI          ; /Arg1 => 1
004015A1  |.  89C1          MOV ECX,EAX                              ; |
004015A3  |.  E8 687C0400   CALL 00449210                            ; \pwh.00449210
004015A8  |.  83EC 04       SUB ESP,4
004015AB  |.  C70424 30F946 MOV DWORD PTR SS:[ESP],0046F930          ; /Arg1 => pwh.46F930, Entry point
004015B2  |.  89C1          MOV ECX,EAX                              ; |
004015B4  |.  E8 97790400   CALL 00448F50                            ; \pwh.00448F50
004015B9  |.  83EC 04       SUB ESP,4

*/