#include<stdio.h>
#include "subknight.h"


void main(){
	unsigned char one=1;
	int chessboard[8][8]={{0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0}};
	struct Knight stack[64]={}; 	
	struct Knight *p=stack;
	//初始化
	chessboard[0][0]=1;
	p->x=0;
	p->y=0;
	p->move=0;
	p->index=1;
	p->u=0;

	while(p!=(stack+63)){
		int nextMove=nextStep(p,chessboard);
		if(nextMove!=-1){
			move(p,nextMove,chessboard);
			p++;
		}else{
			int move=p->move;
			//清空当前指针的内容
			chessboard[p->x][p->y]=0;
			p->x=0;
			p->y=0;
			p->move=0;
			p->index=0;
			p->u=0;
			p--;
			p->u|=(one<<move);
		}
	}


	for(int i1=0;i1<8;i1++){
		for(int i2=0;i2<8;i2++){
			printf("%d,",chessboard[i1][i2]);
		}
		printf("\n");
	}
}


