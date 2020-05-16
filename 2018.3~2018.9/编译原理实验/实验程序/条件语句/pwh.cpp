#include<iostream>
#include<string>
using namespace std;
int main(){
string a1="haha1";
int k=0;
int u=0;
int uuu1[]={1,2,3,4,5};



/**


if(k==0){
	u=1;
}else{
	u=2;
}

CPU Disasm
Address   Hex dump          Command                                  Comments
004015D5  |.  837D E4 00    CMP DWORD PTR SS:[LOCAL.8],0
004015D9  |.  75 09         JNE SHORT 004015E4
004015DB  |.  C745 E0 01000 MOV DWORD PTR SS:[LOCAL.9],1
004015E2  |.  EB 07         JMP SHORT 004015EB
004015E4  |>  C745 E0 02000 MOV DWORD PTR SS:[LOCAL.9],2
*/


/**
if(k==0){
	u=1;
}

CPU Disasm
Address   Hex dump          Command                                  Comments
004015D5  |.  837D E4 00    CMP DWORD PTR SS:[LOCAL.8],0
004015D9  |.  75 07         JNE SHORT 004015E2						//比较结果不等于0则跳到 004015E2（int uuu2[]={1,2,3,4,5};）
004015DB  |.  C745 E0 01000 MOV DWORD PTR SS:[LOCAL.9],1
*/

/**
if(k>0){
	u=1;
}


CPU Disasm
Address   Hex dump          Command                                  Comments
004015D5  |.  837D E4 00    CMP DWORD PTR SS:[LOCAL.8],0
004015D9  |.  7E 07         JLE SHORT 004015E2
004015DB  |.  C745 E0 01000 MOV DWORD PTR SS:[LOCAL.9],1
*/



int uuu2[]={1,2,3,4,5};

}

/**
条转类型归纳
select * from command where command like 'J%' -- 13129


select * from command where command like 'JE %' -- 1862
select * from command where command like 'JZ %' -- 3024
select * from command where command like 'JNE%' -- 977
select * from command where command like 'JNZ%'; -- 1111
select * from command where command like 'JS %'; -- 122
select * from command where command like 'JNS%'; -- 12

select * from command where command like 'JBE%'; -- 218  
select * from command where command like 'JMP%' -- 4252
select * from command where command like 'JLE%'; -- 409
select * from command where command like 'JA %'; -- 313
select * from command where command like 'JGE%'; -- 65
select * from command where command like 'JAE%'; -- 506

select * from command where command like 'JG %'; -- 130

select * from command where command like 'JL %'; -- 20
select * from command where command like 'JB %'; -- 102
select * from command where command like 'JPO%'; -- 2
select * from command where command like 'JPE%'; -- 4





select * from command where command like 'J%' 
and command not like 'JE %'
and command not like 'JZ %'
and command not like 'JMP%'
and command not like 'JNE%'
and command not like 'JBE%'
and command not like 'JNZ%'
and command not like 'JLE%'
and command not like 'JA %'
and command not like 'JGE%'
and command not like 'JAE%'
and command not like 'JS %'
and command not like 'JG %'
and command not like 'JNS%'
and command not like 'JL %'
and command not like 'JB %'

*/