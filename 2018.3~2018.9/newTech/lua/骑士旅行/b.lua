-- 初始化棋盘
local chessboard={}
for i1=1,8 do
 chessboard[i1]={0,0,0,0,0,0,0,0}
end
chessboard[1][1]=1

-- 初始化 stack
local stack={}
local size=1
local step={}
step["x"]=1
step["y"]=1
step["option"]={0,0,0,0,0,0,0,0}
step["next"]=0
 stack[1]=step


function nextPos(step,move)
	local x=step["x"]
	local y=step["y"]
	if move==1 then
	 x=x+1
	 y=y-2
	elseif move==2 then
	 x=x+2
	 y=y-1
	elseif move==3 then
	 x=x+2
	 y=y+1
	elseif move==4 then
	 x=x+1
	 y=y+2
	elseif move==5 then
	 x=x-1
	 y=y+2
	elseif move==6 then
	 x=x-2
	 y=y+1
	elseif move==7 then
	 x=x-2
	 y=y-1
	elseif move==8 then
	 x=x-1
	 y=y-2
	end

	return x,y
end


function getScore(x,y,chessboard)
local step={}
 step["x"]=x
 step["y"]=y

 local score=0
local x1
local y1

for i1=1,8 do
	x1,y1=nextPos(step,i1)
	if x1<1 or x1>8 or y1<1 or y1>8 then
	elseif chessboard[y1][x1]==0 then
	 score=score+1
	end
end

return score
end


function getBestMove(step,chessboard)

 local bestMove=0
 local bestScore=99
 local bestx=0
 local besty=0

 for i1=1,8 do
	x,y=nextPos(step,i1)
	if x<1 or x>8 or y<1 or y>8 then
	elseif chessboard[y][x]~=0 then
	else
	score = getScore(x,y,chessboard)
	if score<bestScore then
	bestMove=i1
	bestScore=score
	bestx=x
	besty=y
	end
	end
 end

 return bestMove,bestx,besty

end


while size<64 do
local move,x1,y1=getBestMove(stack[size],chessboard)

  if move>0 then
 stack[size]["next"]=move
 stack[size+1]={}
 stack[size+1]["x"]=x1
 stack[size+1]["y"]=y1
 chessboard[y1][x1]=size+1

 if move==1 then
  remove =5
 elseif move==2 then
  remove =6
 elseif move==3 then
  remove =7
  elseif move==4 then
  remove =8
 elseif move==5 then
  remove =1
   elseif move==6 then
  remove =2
    elseif move==7 then
  remove =3
     elseif move==8 then
  remove =4
  end

stack[size+1]["option"]={0,0,0,0,0,0,0,0}
stack[size+1]["option"][remove]=1
stack[size+1]["next"]=0
size=size+1
else
chessboard[stack[size]["y"]][stack[size]["x"]]=0
stack[size]=nil
size=size-1
stack[size]["option"][stack[size]["next"]]=1
stack[size]["next"]=0
end

end

io.write("\n")
for i1=1,8 do
	for i2=1,8 do
	 io.write(chessboard[i1][i2])
	 io.write(",")
	end
	io.write("\n")
end














