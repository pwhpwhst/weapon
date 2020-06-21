package com.pwhTest.httpsTest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;



import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


import com.pwhTest.VOAUrl.dao.IVOAUrlDao;
import com.pwhTest.VOAUrl.model.VOAUrlEntity;

import com.pwhTest.VOAWord.dao.IVOAWordDao;
import com.pwhTest.VOAWord.model.VOAWordEntity;

import com.pwhTest.VOADictionary.dao.IVOADictionaryDao;
import com.pwhTest.VOADictionary.model.VOADictionaryEntity;


public class Test{
	public static void main(String[] args){ 

		Resource rs = new ClassPathResource("appContext.xml");
		BeanFactory act = new XmlBeanFactory(rs);

		IVOAUrlDao voaUrlDao= (IVOAUrlDao) act.getBean("voaUrlDao");
		IVOAWordDao voaWordDao= (IVOAWordDao) act.getBean("voaWordDao");
		IVOADictionaryDao voaDictionaryDao= (IVOADictionaryDao) act.getBean("voaDictionaryDao");

		//1、 获取2019年所有带有中文翻译文章的url

		if(voaUrlDao.getVOAUrlCount()==0){
			String mainUrl="https://www.chinavoa.com/";
			Deque<String> urlDeq=new ArrayDeque();
			String initUrl=mainUrl+"list-8741-1.html";
			List<VOAUrlEntity> resultList=new ArrayList<VOAUrlEntity>();

			urlDeq.addFirst(initUrl);

			while(urlDeq.size()>0){
				String url=urlDeq.peekLast();
				System.out.println("读取："+url);
				urlDeq.pollLast();

				String jsonStr = "{}";
				String httpPage = HttpClientUtil.doPost(url, jsonStr, "utf-8");

				Document doc = Jsoup.parse(httpPage);
				Elements liArr=doc.select("#list li");
				for(int i1=0;i1<liArr.size();i1++){
					Elements images=liArr.get(i1).select("img");
					boolean isTranslate=false;
					for(int i2=0;i2<images.size();i2++){
						if("/statics/images/yi.gif".equals(images.get(i2).attr("src"))){
							isTranslate=true;
							break;
						}
					}
					if(!isTranslate){
						continue;
					}

					Elements as=liArr.get(i1).select("a");
					for(int i2=0;i2<as.size();i2++){
						if(as.get(i2).attr("href").indexOf("show")>0){
							VOAUrlEntity voaUrl=new VOAUrlEntity();
							voaUrl.setTitle(as.get(i2).text());	
							voaUrl.setUrl(as.get(i2).attr("href"));	
							resultList.add(voaUrl);
						}

					}			
				}

				Elements pagelist=doc.select(".pagelist a");
				for(int i1=0;i1<pagelist.size();i1++){
					if("下一页".equals(pagelist.get(i1).text())){
						if(!"".equals(pagelist.get(i1).attr("href"))){
							urlDeq.addFirst(mainUrl+pagelist.get(i1).attr("href"));
						}
					}
				}

			}
			voaUrlDao.batchSave(resultList);
		}
		
		

		//2、下载文件
			File file =new File("article");
			if(!file.exists()){
				file.mkdir();
			}

			List<VOAUrlEntity>  resultLit2=voaUrlDao.getVOAUrlList();
			for(int i1=0;i1<resultLit2.size();i1++){

				try{
					file =new File("article/"+resultLit2.get(i1).getTitle().replaceAll("\\\\|\\/|\\:|\\*|\\?|\"|\\<|\\>|\\|","")+".txt");

					if(!file.exists()){
						file.createNewFile();

						System.out.println("下载："+i1+"/"+resultLit2.size());
						String jsonStr = "{}";
						String httpPage = HttpClientUtil.doPost(resultLit2.get(i1).getUrl(), jsonStr, "utf-8");

						Document doc = Jsoup.parse(httpPage);
						Elements ps=doc.select("#tab_fanyi_con1 p");


						FileWriter fileWritter = new FileWriter(file.getAbsoluteFile());
						for(int i2=0;i2<ps.size();i2++){
							if(!"".equals(ps.get(i2).text().trim())){
								fileWritter.write(ps.get(i2).text()+"\n");
								fileWritter.flush();					
							}
						}
						fileWritter.close();
					}

				}catch(IOException e){
				   e.printStackTrace();
				}
			}	
			


			


			//3、统计单词频数并保存
/**
			voaWordDao.delete();

			File articleFold = new File("article");
			File[] articleList = articleFold.listFiles();
			Map<String,Integer> countMap=new HashMap<String,Integer>();
			for(int i1=0;i1<articleList.length;i1++){
				String content=readFileContent("article/"+articleList[i1].getName());
				int stat=0;
				StringBuffer sb=new StringBuffer();
				for(int i2=0;i2<content.length();i2++){
					if(content.charAt(i2)>='a'&&content.charAt(i2)<='z'||content.charAt(i2)>='A'&&content.charAt(i2)<='Z'){
						char c=content.charAt(i2);
						if(content.charAt(i2)>='A'&&content.charAt(i2)<='Z'){
							c=(char)(c+32);
						}
						if(stat==0){
							stat=1;
							sb.append(c);
						}else if(stat==1){
							sb.append(c);
						}
					}else{
						if(stat==0){

						}else if(stat==1){
							if(countMap.get(sb.toString())==null){
								countMap.put(sb.toString(),0);
							}
							countMap.put(sb.toString(),countMap.get(sb.toString())+1);
							sb.setLength(0);
							stat=0;
						}
					}
				}
			}
		
		List<VOAWordEntity> result3List=new ArrayList<VOAWordEntity>();

		 for(String key:countMap.keySet()){
			VOAWordEntity voaWord=new VOAWordEntity();
			voaWord.setWord(key);
			voaWord.setNum(countMap.get(key));
			result3List.add(voaWord);
		 }

		 Comparator<VOAWordEntity> cmpr=new Comparator<VOAWordEntity>(){
			@Override
			public int compare(VOAWordEntity o1, VOAWordEntity o2) {
				Integer num1=o1.getNum();
				Integer num2=o2.getNum();
				
				int result= num2.compareTo(num1);
				if(result!=0){
					return result;
				}

				String word1=o1.getWord();
				String word2=o2.getWord();

				result= word2.length()-word1.length();

				return result;

			}
		};
		Collections.sort(result3List, cmpr);
		voaWordDao.batchSave(result3List);
*/


		List<VOAWordEntity> wordList=voaWordDao.getVOAWordList();
		List<VOADictionaryEntity> result4List=new ArrayList<VOADictionaryEntity>();
		for(int i1=0;i1<wordList.size();i1++){
			System.out.println("下载单词含义："+i1+"/"+wordList.size());
			String main2Url="http://dict.youdao.com/w/";
			String tail2Url="/#keyfrom=dict2.top";
			String wordUrl=main2Url+wordList.get(i1).getWord()+tail2Url;
			String httpPage2 = HttpClientUtil.doPost(wordUrl, "{}", "utf-8");
			Document doc = Jsoup.parse(httpPage2);
			Elements liArr=doc.select("#phrsListTab ul li");
			for(int i2=0;i2<liArr.size();i2++){
				String content=liArr.get(i2).text();
				int stat=0;
				StringBuffer sb=new StringBuffer();
				for(int i3=0;i3<content.length();i3++){
					if(content.charAt(i3)>='a'&&content.charAt(i3)<='z'||content.charAt(i3)>='A'&&content.charAt(i3)<='Z'){
						if(stat==0){
							stat=1;
							sb.append(content.charAt(i3));
						}else if(stat==1){
							sb.append(content.charAt(i3));
						}
					}else if(content.charAt(i3)=='.'){
						if(stat==0){

						}else if(stat==1){
							stat=2;
							break;
						}
					}else{
						stat=2;
						sb.setLength(0);
						break;
					}
				}
				String property=sb.toString();
				VOADictionaryEntity voaDictionaryEntity=new VOADictionaryEntity();
				voaDictionaryEntity.setWordId(wordList.get(i1).getId());
				voaDictionaryEntity.setProperty(property);
				voaDictionaryEntity.setChinese(content);
				result4List.add(voaDictionaryEntity);
			}

			if(result4List.size()>100){
				voaDictionaryDao.batchSave(result4List);
				result4List.clear();
			}
			

			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(result4List.size()>0){
			voaDictionaryDao.batchSave(result4List);
			result4List.clear();
		}






    }




	public static String readFileContent(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		StringBuffer sbf = new StringBuffer();
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempStr;
			while ((tempStr = reader.readLine()) != null) {
				sbf.append(tempStr+"\n");
			}
			reader.close();
			return sbf.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return sbf.toString();
	}




} 


