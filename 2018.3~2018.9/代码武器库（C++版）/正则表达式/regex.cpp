#include <iostream>
#include <regex>
using namespace std;

/*
https://www.cnblogs.com/tianzeng/p/10492932.html
*/

int main()
{
    
    //--------------std::regex_match: ������ʽ��Ҫƥ�������ַ�������, Ҳ����˵������ʽҪ��
    //�ַ�����ȫƥ��, ���, ���ǵ���ƥ��, ����ƥ��ʧ��.  �������Ի�ȡ��ƥ�����
    //���� "()" ���ڲ�����, ������ı���ǰ��� "(" ���ֵ�˳��, ������, ��1��ʼ���б�ŵ� 

	/*
    string str("adfd-89-@-.");
    regex partten("([a-z]{4})-(\\d{2}-@-\\.)");
	*/
 
 	
	string str("Declare $1;");
    regex partten("\\$1");
	

    //1.ֻƥ���Ƿ����������ʽ 
    cout<<regex_match(str,partten)<<endl;
    
    //2-1.ƥ���Ƿ����������ʽ��������һ�β������� sm[0]��ƥ���������ʽ���;match_results<string::const_iterator> sm; 
    smatch sm;
    if(regex_match(str.cbegin(),str.cend(),sm,partten))
    {
        for(match_results<string::const_iterator>::const_iterator it=sm.begin();it!=sm.end();++it)
            cout<<it->length()<<": "<<it->str()<<endl;
    }
    
    //2-2.����match_results�洢��� 
    match_results<string::iterator> res;
    if(regex_match(str.begin(),str.end(),res,partten))
    {
        for(auto it=res.begin();it!=res.end();++it)
            cout<<it->length()<<": "<<it->str()<<endl;
    }
    
    //3.cmatch match_resluts<const char*>
    cmatch cm;
    if(regex_match(str.c_str(),cm,partten))
    {
        for(match_results<const char *>::const_iterator it=cm.begin();it!=cm.end();++it)
        {
            cout<<it->length()<<": "<<it->str()<<endl;
        }    
    }
    
    //4.
    cout<<regex_match(str.c_str(),partten)<<endl;
    
    //5.
    match_results<string::const_iterator> ress;
    if(regex_match(str,ress,partten))
    {
        for(int i=0;i<ress.size();++i)
        {
            //sub_match: ��ƥ��, match_results���涼��ŵ���sub_match
            //sub_match �������Ϊ std::pair ����չ, ���̳��� std::pair,
            //���� first ʵ���ϴ�ŵ��ǻ�ȡ���ַ���ͷָ���ַ, second Ϊβָ���ַ
            ssub_match sub=ress[i];
            cout<<sub.length()<<": "<<sub.str()<<endl;
        }
    }
    
    
    //------------std::regex_search: ����������ʽ����, ������Ҫ�������ַ�������ȫƥ��. 
    //������ֻ���е�������, ��������ֹͣ��������, �������ظ��������.
    //���� "()" ���ڲ�����, ������ı���ǰ��� "(" ���ֵ�˳��, ������, ��1��ʼ���б�ŵ�
    string str1("Data-Time:2019-03-07-23:20~2019-03-08-23:59");
    regex r("(\\d{4}-)(\\d{2}-)(\\d{2})");
    
    //1.��0��һ��������������ʽƥ����, ���������ǲ�����Ľ��, ���������ظ��������
    match_results<string::iterator> res1;
    if(regex_search(str1.begin(),str1.end(),res1,r))
    {
        match_results<string::iterator>::const_iterator it;
        for(it=res1.begin();it!=res1.end();++it)
            cout<<it->length()<<": "<<it->str() <<endl;
    }
    
    //2.��ʾ�Ƿ�������������������ʽ�Ľ��
    cout<<regex_search(str1.begin(),str1.end(),r)<<endl;
    
    //3.��1��ͬ�����Ǵ˴��õ���smatch��
    smatch sm1;
    if(regex_search(str1.cbegin(),str1.cend(),sm1,r)) 
    {
        for(auto it=sm1.begin();it!=sm1.end();++it)
            cout<<it->length()<<": "<<it->str()<<endl;
    }
    
    //4.��1��ͬ�����Ǵ˴��õ���cmatch
    cmatch cm1;
    if(regex_search(str1.c_str(),cm1,r))
    {
        for(int i=0;i<cm1.size();++i)
        {
            csub_match cs=cm1[i];
            cout<<cs.length()<<": "<<cs.str()<<endl;
            
        }
    }
    
    //5.
    cout<<regex_search(str1,r)<<endl;
    
    //6.
    cout<<regex_search(str1.c_str(),r)<<endl;
    
    
    //--------------egex_replace: �����������������ʽ(�����ǲ�����)��Ȼ���滻������ʽƥ�䵽�Ľ��
    string str2("Date:2019-03-7~2019-03-20");
    regex r2("\\d{4}-\\d{2}-\\d{2}");
    //������Ϻ��滻���ַ�
    string result(256,'\0');
    string sub("2019-03-8");
    
    //1.regex_replace ģ�庯������ֵʵ�������µ��ַ������������β����ָ��λ��, �� 0 ��Ϊ�˷�ֹ�������ݳ��������
    *regex_replace(result.begin(),str2.begin(),str2.end(),r2,sub)='\0';
    cout<<result.c_str()<<endl;

    //2.
    result.clear();
    result.resize(256,'\0');
    result=regex_replace(str2,r2,sub);
    cout<<result.c_str()<<endl;
    return 0;
}