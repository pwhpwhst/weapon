#include<iostream>
#include<deque>
#include<vector>
#include<set>
#include<bitset>
#include<unordered_map>

using namespace std;



int main(){

	// (a|b)*abb 正则表达式的NFA
	int status[11]={0,1,2,3,4,5,6,7,8,9,10};
	char act[2]={'a','b'};
	int edge_beg[13]={0,0,1,1,2,3,4,5,6,6,7,8,9};
	char edge_act[13]={0,0,0,0,'a',0,'b',0,0,0,'a','b','b'};
	int edge_end[13]={1,7,2,4,3,6,5,6,1,7,8,9,10};

	

	
	//	目标DFA
	unordered_map<int, set<int>> N_to_D_map;
	vector<int> dfa_status;
	vector<int> dfa_beg;
	vector<int> dfa_act;
	vector<int> dfa_end;

	deque<int> que0;
	deque<int> que;



	set<int> temp_set;
	set<int>::iterator it;
	set<int>::iterator it1;
	set<int> result_A;

//构造第一个DFA状态 
	que.push_front(0);
	while(!que.empty()){
		int u = que.back();
		result_A.insert(u);
		que.pop_back();

		temp_set.clear();
		for(int i1=0;i1<13;i1++){
			if((edge_beg[i1]==u)&&(edge_act[i1]==0)){
				temp_set.insert(edge_end[i1]);
			}
		}

		for(it=temp_set.begin ();it!=temp_set.end ();it++){
			if(!result_A.count(*it) ){
				que.push_front(*it);
			}
		}
	}

	dfa_status.push_back(0);
	N_to_D_map[0]=result_A;
	que0.push_front(0);




while(!que0.empty()){
	int u = que0.back();
	que0.pop_back();
	


	for(int i2=0;i2<2;i2++){
		result_A.clear();
		for(it=N_to_D_map[u].begin();it!=N_to_D_map[u].end();it++){

			for(int i3=0;i3<13;i3++){
				if(*it==edge_beg[i3]&&act[i2]==edge_act[i3]){
					result_A.insert(edge_end[i3]);

				}
			}
		}




		que.clear();
		for(it=result_A.begin();it!=result_A.end();it++){
			que.push_front(*it);
		}
		result_A.clear();



		while(!que.empty()){
			int u2 = que.back();
			result_A.insert(u2);
			que.pop_back();

			temp_set.clear();

			for(int i1=0;i1<13;i1++){
				if((edge_beg[i1]==u2)&&(edge_act[i1]==0)){
					temp_set.insert(edge_end[i1]);
				}
			}

			for(it=temp_set.begin ();it!=temp_set.end ();it++){
				if(!result_A.count(*it)){
					que.push_front(*it);
				}
			}

		}


//判断新制造的子集是否已经存在了
		bool is_exist=false;
		int exist_status;
		for(int i1=0;i1<dfa_status.size();i1++){
			if(N_to_D_map[dfa_status[i1]].size()==result_A.size()){
				bool is_match2=false;
				for(it=result_A.begin ();it!=result_A.end ();it++){

					bool is_match=false;
					for(it1=N_to_D_map[dfa_status[i1]].begin();it1!=N_to_D_map[dfa_status[i1]].end();it1++){
						if(*it==*it1){
							is_match=true;
							break;
						}
					}
					if(!is_match){
						break;
					}

					set<int>::iterator it2=it;
					if((++it2)==result_A.end()){
						is_match2=true;
					}
				}
				if(!is_match2){
					continue;
				}else{
					exist_status=dfa_status[i1];
					is_exist=true;
					break;
				}
			}else{
				continue;
			}
		}


		if(!is_exist){
			dfa_status.push_back(dfa_status[dfa_status.size()-1]+1);
			N_to_D_map[dfa_status[dfa_status.size()-1]]=result_A;
			que0.push_front(dfa_status[dfa_status.size()-1]);
		}

		dfa_beg.push_back(u);
		dfa_act.push_back(act[i2]);
		if(!is_exist){
			dfa_end.push_back(dfa_status[dfa_status.size()-1]);
		}else{
			dfa_end.push_back(exist_status);
		}

		

	}


}





// 调试代码 beg
cout<<"N_to_D_map:"<<endl;
for(int i1=0;i1<dfa_status.size();i1++){
	cout<<dfa_status[i1]<<":"<<endl;
	for(it=N_to_D_map[dfa_status[i1]].begin ();it!=N_to_D_map[dfa_status[i1]].end ();it++){
		cout<<*it<<",";
	}
	cout<<endl;
}

cout<<"DFA_EDGES:"<<endl;
for(int i1=0;i1<dfa_beg.size();i1++){
	cout<<dfa_beg[i1]<<","<<(char)dfa_act[i1]<<","<<dfa_end[i1]<<endl;
}

// 调试代码 end








}