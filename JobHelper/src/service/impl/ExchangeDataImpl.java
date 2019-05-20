package service.impl;

import java.util.List;

import controller.MainController;
import model.StudentModel;
import service.ExchangeData;
import util.StageManagement;

public class ExchangeDataImpl implements ExchangeData {

	@Override
	public void exchange(int ModelNum, String ModelName, List<Integer> ModelAllScore, String gradeName, String className)
			throws Exception {
		
	}

	@Override
	public void exchStuModlInf(StudentModel studentModel) throws Exception {
		//首先获取MainController
		MainController mainController = (MainController) StageManagement.CONTROLLER.get("MainController");
		mainController.getStuModlInfo(studentModel);
	}

}
