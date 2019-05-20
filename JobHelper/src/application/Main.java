package application;
	
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.net.URL;

import com.teamdev.jxbrowser.chromium.ay;

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
	 static {
	        try {
	            Field e = ay.class.getDeclaredField("e");
	            e.setAccessible(true);
	            Field f = ay.class.getDeclaredField("f");
	            f.setAccessible(true);
	            Field modifersField = Field.class.getDeclaredField("modifiers");
	            modifersField.setAccessible(true);
	            modifersField.setInt(e, e.getModifiers() & ~Modifier.FINAL);
	            modifersField.setInt(f, f.getModifiers() & ~Modifier.FINAL);
	            e.set(null, new BigInteger("1"));
	            f.set(null, new BigInteger("1"));
	            modifersField.setAccessible(false);
	        } catch (Exception e1) {
	            e1.printStackTrace();
	        }
	    }
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
