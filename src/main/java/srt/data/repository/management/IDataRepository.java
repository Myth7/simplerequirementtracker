package srt.data.repository.management;

import java.util.List;

import srt.data.domain.management.Data;
//import srt.data.domain.management.User;

public interface IDataRepository {
	
	public Data getDatasByDataId(Long dataId,String Creator);

	public List<Data> getDatasByData1(String data1,String Creator);
	
	public List<Data> getDatasByCreator(String Creator);

	public int addData(Data data);
	
	public int updateData(Data data);

	public int removeDataByDataId(Long dataId);
	
}
