package util;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class CreateMainModel {
	public static VBox model(String name) {
		VBox vBox = new VBox();
		Label label = new Label(name);//TODO
		label.setPrefWidth(100);
		label.setBackground(new Background(new BackgroundFill(Color.CADETBLUE,null, null)));
		TextField textField = new TextField();
		textField.setMinWidth(0);
		textField.setPrefWidth(100);
		List<Node>list = new ArrayList<>();
		list.add(label);
		list.add(textField);
		vBox.getChildren().addAll(list);
		return vBox;	
	}
}
