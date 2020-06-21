package com.pwhTest.VOAUrl.dao;
import com.pwhTest.VOAUrl.model.VOAUrlEntity;
import java.util.List;


public interface IVOAUrlDao{

	public void save(VOAUrlEntity voaUrl);

	public void batchSave(List<VOAUrlEntity> voaUrlList);

	public List<VOAUrlEntity> getVOAUrlList();

	public Integer getVOAUrlCount();

}







