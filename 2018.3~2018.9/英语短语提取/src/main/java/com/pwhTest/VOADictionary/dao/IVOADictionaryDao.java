package com.pwhTest.VOADictionary.dao;
import com.pwhTest.VOADictionary.model.VOADictionaryEntity;
import java.util.List;


public interface IVOADictionaryDao{

	public void save(VOADictionaryEntity voaDictionary);

	public void delete();

	public void batchSave(List<VOADictionaryEntity> voaDictionaryList);

	public List<VOADictionaryEntity> getVOADictionaryList();

}







