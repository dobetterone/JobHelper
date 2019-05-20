package service.impl;

import java.net.URL;

import controller.MainController;
import controller.MsonController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.SubPrimaryStage;
import util.StageManagement;

public class SubPrimaryStageImpl implements SubPrimaryStage{
	@Override
	public void openPrimaryStage() throws Exception {
		Stage stage = new Stage();
		//放入舞台管理器中
		StageManagement.STAGE.put("stage", stage);
		URL location = getClass().getResource("/resource/fxml/Mson.fxml");
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(location);
		fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
		Parent root = fxmlLoader.load();
		//获取controller
		MsonController msonController = fxmlLoader.getController();
		msonController.setPrimary(stage);
		Scene scene = new Scene(root,350,400);
		stage.setScene(scene);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.show();
		//将stage和controller控制器传入到舞台管理器中
		StageManagement.STAGE.put("MsonStage",stage);
		StageManagement.CONTROLLER.put("MsonController", msonController);
	}
}
