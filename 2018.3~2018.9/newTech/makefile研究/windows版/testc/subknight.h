struct Knight
{
	int x;
	int y;
	int index;
	int move;
	unsigned char u;
};

int nextStep(struct Knight *p,int chessboard[8][8]);
void move(struct Knight *p,int nextMove,int chessboard[8][8]);