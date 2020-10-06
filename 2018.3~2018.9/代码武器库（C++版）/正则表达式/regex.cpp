#include <iostream>
#include <regex>
using namespace std;

/*
https://www.cnblogs.com/tianzeng/p/10492932.html
*/

int main()
{
    
    //--------------std::regex_match: 正则表达式需要匹配整个字符串序列, 也就是说正则表达式要与
    //字符串完全匹配, 因此, 它是单次匹配, 否则匹配失败.  它还可以获取子匹配的组
    //这里 "()" 用于捕获组, 捕获组的编号是按照 "(" 出现的顺序, 从左到右, 从1开始进行编号的 

	/*
    string str("adfd-89-@-.");
    regex partten("([a-z]{4})-(\\d{2}-@-\\.)");
	*/
 
 	
	string str("Declare $1;");
    regex partten("\\$1");
	

    //1.只匹配是否符合正则表达式 
    cout<<regex_match(str,partten)<<endl;
    
    //2-1.匹配是否符合正则表达式并将其他一次捕获到数组 sm[0]是匹配的正则表达式结果;match_results<string::const_iterator> sm; 
    smatch sm;
    if(regex_match(str.cbegin(),str.cend(),sm,partten))
    {
        for(match_results<string::const_iterator>::const_iterator it=sm.begin();it!=sm.end();++it)
            cout<<it->length()<<": "<<it->str()<<endl;
    }
    
    //2-2.用类match_results存储结果 
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
            //sub_match: 子匹配, match_results里面都存放的是sub_match
            //sub_match 可以理解为 std::pair 的扩展, 它继承了 std::pair,
            //其中 first 实际上存放的是获取的字符串头指针地址, second 为尾指针地址
            ssub_match sub=ress[i];
            cout<<sub.length()<<": "<<sub.str()<<endl;
        }
    }
    
    
    //------------std::regex_search: 搜素正则表达式参数, 但它不要求整个字符序列完全匹配. 
    //而且它只进行单次搜索, 搜索到即停止继续搜索, 不进行重复多次搜索.
    //这里 "()" 用于捕获组, 捕获组的编号是按照 "(" 出现的顺序, 从左到右, 从1开始进行编号的
    string str1("Data-Time:2019-03-07-23:20~2019-03-08-23:59");
    regex r("(\\d{4}-)(\\d{2}-)(\\d{2})");
    
    //1.第0组一般是整个正则表达式匹配结果, 其他依次是捕获组的结果, 它不进行重复多次搜索
    match_results<string::iterator> res1;
    if(regex_search(str1.begin(),str1.end(),res1,r))
    {
        match_results<string::iterator>::const_iterator it;
        for(it=res1.begin();it!=res1.end();++it)
            cout<<it->length()<<": "<<it->str() <<endl;
    }
    
    //2.显示是否有搜索到符合正则表达式的结果
    cout<<regex_search(str1.begin(),str1.end(),r)<<endl;
    
    //3.与1相同。但是此次用的是smatch，
    smatch sm1;
    if(regex_search(str1.cbegin(),str1.cend(),sm1,r)) 
    {
        for(auto it=sm1.begin();it!=sm1.end();++it)
            cout<<it->length()<<": "<<it->str()<<endl;
    }
    
    //4.与1相同。但是此次用的是cmatch
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
    
    
    //--------------egex_replace: 多次搜索整个正则表达式(不考虑捕获组)，然后替换正则表达式匹配到的结果
    string str2("Date:2019-03-7~2019-03-20");
    regex r2("\\d{4}-\\d{2}-\\d{2}");
    //结果集合和替换的字符
    string result(256,'\0');
    string sub("2019-03-8");
    
    //1.regex_replace 模板函数返回值实际上是新的字符串存入变量后尾部的指针位置, 置 0 是为了防止变量数据出错或乱码
    *regex_replace(result.begin(),str2.begin(),str2.end(),r2,sub)='\0';
    cout<<result.c_str()<<endl;

    //2.
    result.clear();
    result.resize(256,'\0');
    result=regex_replace(str2,r2,sub);
    cout<<result.c_str()<<endl;
    return 0;
}