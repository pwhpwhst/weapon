#include <iostream>
#include <io.h>
#include <string>
#include <string.h>
#include <vector>
#include <fstream>
using namespace std;

const string project_index="index.txt";
const string project_path="D:\\Users\\Administrator\\Desktop\\project2018.3_2018.9\\2018.3~2018.9\\编译原理实验\\微型编译器";

int main()
{
	long handle;                                                     //用于查找的句柄
	struct _finddata_t findData;                                     //文件信息的结构体


  ofstream fs;
  fs.open(project_index.c_str());
  if (!fs){
	return 0;
  }

 
	vector<string> que;
	que.push_back(project_path);

	bool is_first=true;
	while(que.size()>0){
		string _path=que.back();
		que.pop_back();
		handle=_findfirst((_path+"\\*.*").c_str(),&findData); 

		do{
			if(handle==-1){
				continue;
			}

			if (findData.attrib & _A_SUBDIR){
				if (strcmp(findData.name, ".") == 0 || strcmp(findData.name, "..") == 0){
					continue;
				}
				string s=findData.name;
				que.push_back(_path+"\\"+s);

			}else{
				if(is_first){
					is_first=false;
				}else{
					fs<<endl;
				}
				fs<<_path<<"\\"<<findData.name;
			}
		}while(!_findnext(handle,&findData));
			_findclose(handle);
		}

		fs.close();
		return 0;	
}
