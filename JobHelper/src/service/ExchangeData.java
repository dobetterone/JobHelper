package service;

import java.util.List;

public interface ExchangeData {
	public void exchange(int ModelNum,String ModelName,List<Integer> ModelAllScore,String gradeName,String className) throws Exception;
}
