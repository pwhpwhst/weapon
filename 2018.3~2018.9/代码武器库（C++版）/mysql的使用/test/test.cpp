#include <stdio.h>
#include <windows.h>
#include <mysql.h>
#include <winsock2.h>

int main(int argc,char *argv[])
{
    MYSQL conn;
    int res;
    mysql_init(&conn);
    if(mysql_real_connect(&conn,"localhost","root","","test",0,NULL,CLIENT_FOUND_ROWS)) //"root":���ݿ����Ա "":root���� "test":���ݿ������
    {
        printf("connect success!\n");
        res=mysql_query(&conn,"insert into test values('user','123456')");
        if(res)
        {
            printf("error\n");
        }
        else
        {
            printf("OK\n");
        }
        mysql_close(&conn);
    }
    return 0;
}