#include "subknight.h"

int nextStep(struct Knight *p,int chessboard[8][8]){
	unsigned char u=0;
	unsigned char one=1;
	for(int i1=0;i1<8;i1++){
		int dx=0;
		int dy=0;
		if(i1==0){
			dx=p->x-2;
			dy=p->y+1;
		}else if(i1==1){
			dx=p->x-1;
			dy=p->y+2;
		}else if(i1==2){
			dx=p->x+1;
			dy=p->y+2;
		}else if(i1==3){
			dx=p->x+2;
			dy=p->y+1;
		}else if(i1==4){
			dx=p->x+2;
			dy=p->y-1;
		}else if(i1==5){
			dx=p->x+1;
			dy=p->y-2;
		}else if(i1==6){
			dx=p->x-1;
			dy=p->y-2;
		}else if(i1==7){
			dx=p->x-2;
			dy=p->y-1;
		}

		if(dx<0||dx>7||dy<0||dy>7){
			u|=(one<<i1);
			continue;
		}
		if(chessboard[dx][dy]!=0){
			u|=(one<<i1);
			continue;
		}
		if((((p->u)<<(7-i1))>>7)==one){
			u|=(one<<i1);
			continue;
		}
	}

int bestMove=-1;
int bestScore=9;
	for(int i1=0;i1<8;i1++){
		if(((u<<(7-i1))>>7)==one){
			continue;
		}else{
			
			int dx=0;
			int dy=0;
			if(i1==0){
				dx=p->x-2;
				dy=p->y+1;
			}else if(i1==1){
				dx=p->x-1;
				dy=p->y+2;
			}else if(i1==2){
				dx=p->x+1;
				dy=p->y+2;
			}else if(i1==3){
				dx=p->x+2;
				dy=p->y+1;
			}else if(i1==4){
				dx=p->x+2;
				dy=p->y-1;
			}else if(i1==5){
				dx=p->x+1;
				dy=p->y-2;
			}else if(i1==6){
				dx=p->x-1;
				dy=p->y-2;
			}else if(i1==7){
				dx=p->x-2;
				dy=p->y-1;
			}

			int score=0;
			if(dx<0||dx>7||dy<0||dy>7){
				score=9;
			}else if(chessboard[dx][dy]!=0){
				score=9;
			}else{
				for(int i2=0;i2<8;i2++){
						int ddx=0;
						int ddy=0;

					if(i2==0){
						ddx=dx-2;
						ddy=dy+1;
					}else if(i2==1){
						ddx=dx-1;
						ddy=dy+2;
					}else if(i2==2){
						ddx=dx+1;
						ddy=dy+2;
					}else if(i2==3){
						ddx=dx+2;
						ddy=dy+1;
					}else if(i2==4){
						ddx=dx+2;
						ddy=dy-1;
					}else if(i2==5){
						ddx=dx+1;
						ddy=dy-2;
					}else if(i2==6){
						ddx=dx-1;
						ddy=dy-2;
					}else if(i2==7){
						ddx=dx-2;
						ddy=dy-1;
					}

					if(ddx<0||ddx>7||ddy<0||ddy>7){
						continue;
					}

					if(chessboard[ddx][ddy]==0){
						score++;
					}
				}
			}

			if(score<bestScore){
				bestMove=i1;
				bestScore=score;
			}
		}
	}
	return bestMove;
}

void move(struct Knight *p,int nextMove,int chessboard[8][8]){

		int dx=0;
		int dy=0;
		if(nextMove==0){
			dx=p->x-2;
			dy=p->y+1;
		}else if(nextMove==1){
			dx=p->x-1;
			dy=p->y+2;
		}else if(nextMove==2){
			dx=p->x+1;
			dy=p->y+2;
		}else if(nextMove==3){
			dx=p->x+2;
			dy=p->y+1;
		}else if(nextMove==4){
			dx=p->x+2;
			dy=p->y-1;
		}else if(nextMove==5){
			dx=p->x+1;
			dy=p->y-2;
		}else if(nextMove==6){
			dx=p->x-1;
			dy=p->y-2;
		}else if(nextMove==7){
			dx=p->x-2;
			dy=p->y-1;
		}

		(p+1)->x=dx;
		(p+1)->y=dy;
		(p+1)->index=p->index+1;
		(p+1)->move=nextMove;
		(p+1)->u=0;
		
		chessboard[dx][dy]=p->index+1;

}