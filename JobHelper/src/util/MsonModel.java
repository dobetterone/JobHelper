package util;


import java.util.ArrayList;
import java.util.List;

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
		List<VBox>msonList = new ArrayList<>();
		for(int i=1;i<=num;i++){
			VBox MsonVBox = new VBox();
			HBox hBoxTop = new HBox();
			HBox hBoxBottom = new HBox();
			Label labelModelName = new Label(); 
			Label labelModelAllScore = new Label();
			TextField textFieldModelName = new TextField();
			textFieldModelName.setPrefWidth(240);
			TextField textFieldModelAllScore = new TextField();
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
