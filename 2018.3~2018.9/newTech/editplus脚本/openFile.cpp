#include <iostream>
#include <io.h>
#include <string>
#include <vector>
#include <fstream>


int main(int argc,char *argv[])
{
	using namespace std;
	ifstream ifs;
	// 3 打开文件 判断是否打开成功
	ifs.open(argv[1], ios::in);
	if (!ifs.is_open()) {
	  cout << "文件打开失败！" << endl;
	  return 0;
	}


	vector<string> file_list;
	string buf;
	while (getline(ifs, buf)) {
		file_list.push_back(buf);
	}


	for(int i1=0;i1<file_list.size();i1++){
		const string &e=file_list[i1];
		if(e.find(".cpp")!=-1||e.find(".h")!=-1||e.find(".txt")!=-1){
			if(e.find(argv[2])!=-1){
				system(e.c_str());
			}
		}

	}
	
}
