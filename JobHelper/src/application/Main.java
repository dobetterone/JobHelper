package application;
	
import java.net.URL;

import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;

/***
 * 这是一个程序的入口
 * @author 张艺航
 */
public class Main extends Application {
	Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		try {
			//读取fxml文件
			//Parent root = FXMLLoader.load(getClass().getResource("/resource/fxml/Main.fxml"));
			URL location = getClass().getResource("/resource/fxml/Main.fxml");
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(location);
			fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
			Parent root = fxmlLoader.load();
			//获取controller
			MainController mainController = fxmlLoader.getController();
			mainController.setPrimary(primaryStage);
			//设置场景场景
			Scene scene = new Scene(root,1480,900);
			scene.getStylesheets().add(getClass().getResource("/resource/css/MainStyle.css").toExternalForm());
			primaryStage.setScene(scene);
			//设置舞台的样式为无样式
			primaryStage.initStyle(StageStyle.TRANSPARENT);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
