package util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MsonModel {
	int num;
	public MsonModel(int num) {
		this.num = num;
	}
	public List<VBox> createMasonModel(){
		//首先清空模块管理器
		ModelInfoManagent.clearModelInfoManagent();
		//System.out.println("模块管理器清空啦！！！");
		List<VBox>msonList = new ArrayList<>();
		//Map<TextField, TextField>map = new HashMap<>();//存储模块信息
		for(int i=1;i<=num;i++){
			VBox MsonVBox = new VBox();
			HBox hBoxTop = new HBox();
			HBox hBoxBottom = new HBox();
			Label labelModelName = new Label(); 
			Label labelModelAllScore = new Label();
			TextField textFieldModelName = new TextField();
			textFieldModelName.setPrefWidth(240);
			TextField textFieldModelAllScore = new TextField();
			//将模块名和每个模块的总分加入模块管理器中
			//加入模块名
			ModelInfoManagent.modepNameMap.put(i, textFieldModelName);
			//加入每个模块总分
			ModelInfoManagent.modelAllScoreMap.put(i, textFieldModelAllScore);
			textFieldModelAllScore.setMinWidth(0);
			textFieldModelAllScore.setPrefWidth(80);
			labelModelName.setPrefWidth(60);
			labelModelName.setPrefHeight(30);
			labelModelName.setText("模块名称");
			labelModelAllScore.setPrefWidth(100);
			labelModelAllScore.setPrefHeight(30);
			labelModelAllScore.setText("模块总分");
			hBoxTop.getChildren().add(labelModelName);
			hBoxTop.getChildren().add(labelModelAllScore);
			hBoxTop.setSpacing(150);
			hBoxBottom.getChildren().add(textFieldModelName);
			hBoxBottom.getChildren().add(textFieldModelAllScore);
			hBoxBottom.setSpacing(10);
			MsonVBox.getChildren().add(hBoxTop);
			MsonVBox.getChildren().add(hBoxBottom);
			msonList.add(MsonVBox);
		}
		return msonList;
	}
}
