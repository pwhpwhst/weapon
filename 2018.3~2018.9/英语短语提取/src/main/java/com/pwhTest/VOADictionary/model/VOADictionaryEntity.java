package com.pwhTest.VOADictionary.model;


public class VOADictionaryEntity{

	private Integer id;
	private Integer wordId;
	private String property;
	private String chinese;

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		 this.id=id;
	}

	public Integer getWordId(){
		return wordId;
	}

	public void setWordId(Integer wordId){
		 this.wordId=wordId;
	}


	public String getProperty(){
		return property;
	}

	public void setProperty(String property){
		 this.property=property;
	}

	public String getChinese(){
		return chinese;
	}

	public void setChinese(String chinese){
		 this.chinese=chinese;
	}

}