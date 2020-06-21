package com.pwhTest.VOAWord.dao;
import com.pwhTest.VOAWord.model.VOAWordEntity;
import java.util.List;


public interface IVOAWordDao{

	public void save(VOAWordEntity voaWord);

	public void delete();

	public void batchSave(List<VOAWordEntity> voaWordList);

	public List<VOAWordEntity> getVOAWordList();

}







