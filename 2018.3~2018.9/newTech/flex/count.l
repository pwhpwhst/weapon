%{
   #include "stdio.h"
   #include "stdlib.h"
   int num_num=0,num_id=0;
   //声明部分
%}
INTEGER [-+]?[1-9][0-9]*
%%
{INTEGER} { num_num++;
  printf("(num=%d)",atoi(yytext));/*打印数字值*/
    /*数字数加一*/
    //转换规则
}
%%
//额外函数
int main()
{
   fopen_s(&yyin, "C:\\Users\\Administrator\\Desktop\\flex\\test.txt", "r");
   yylex();
   fclose(yyin);
printf("\n");
   printf("num=%d,id=%d",num_num,num_id);
   return 0;
}

int yywrap()//此函数必须由用户提供

{
    return 1;
}