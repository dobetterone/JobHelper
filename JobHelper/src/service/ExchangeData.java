package service;

import java.util.List;

import model.StudentModel;

public interface ExchangeData {
	public void exchange(int ModelNum,String ModelName,List<Integer> ModelAllScore,String gradeName,String className) throws Exception;
	/**
	 * 交换学生信息
	 * @param studentModel
	 * @throws Exception
	 */
	public void exchStuModlInf(StudentModel studentModel) throws Exception;
}
