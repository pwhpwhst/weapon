#include <iostream>
#include <io.h>
#include <string>
#include <fstream>






using namespace std;

const string project_index="pwh.bat";
int main(int argc,char *argv[]){

	
	ofstream fs;
	fs.open(project_index.c_str());
	if (!fs){
	return 0;
	}

	string s=argv[1];
	string cmd =s;
	fs<<cmd<<endl;

	s=argv[2];
	cmd ="cd "+s;
	fs<<cmd<<endl;

	cmd="findstr /n /s /i ";
	cmd+="\"";
	s=argv[3];
	cmd+=s;
	cmd+="\" ";
	s=argv[4];
	cmd+=s;
	cmd+=" >F:\\codeWeaponStore\\project2018.3_2018.9\\2018.3~2018.9\\newTech\\editplus脚本\\调试信息.txt";
	fs<<cmd<<endl;

	cmd="F:\\codeWeaponStore\\project2018.3_2018.9\\2018.3~2018.9\\newTech\\editplus脚本\\调试信息.txt";
	fs<<cmd<<endl;
	fs.close();
	system("F:\\codeWeaponStore\\project2018.3_2018.9\\2018.3~2018.9\\newTech\\editplus脚本\\pwh.bat");
	return 0;
}