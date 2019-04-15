package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
/***
 * 这是一个控制器，实现了Initializable接口
 * @author 航航
 *
 */
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class MainController implements Initializable {
	@FXML
	BorderPane rootPane;
	@FXML
	BorderPane topPane;
	@FXML
	BorderPane bottomPane;
	@FXML
	BorderPane leftPane;
	@FXML
	BorderPane logobackgroud;
	@FXML
	//最小化的标签
	Label labelMin;
	@FXML
	//最大化的标签
	Label labelMax;
	@FXML 
	//退出的标签
	Label labelExit;
	@FXML
	//最小化的图片
	ImageView imageMin;
	@FXML
	//最大化的图片
	ImageView imageMax;
	@FXML
	//退出的图片
	ImageView imageExit;
	//鼠标相对于窗口内的位置
	double mouseX,mouseY;
	//窗口位置的原值和窗口的大小
	double resetX,resetY,resetWidth,reseHeight;
	@FXML
	//这是控制封装（最大化，最小化，退出）label的hbox
	HBox hboxBar;
	//main里面的舞台
	Stage primaryStage;
	//输入域
	@FXML
	TextArea textArea;
	@FXML
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//设置总BorderPane面板的背景
		rootPane.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE,null, null)));
		//设置BorderPane面板上部分的背景
		topPane.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, null, null)));
		logobackgroud.setPrefHeight(40);
		logobackgroud.setBackground(new Background(new BackgroundFill(Color.AZURE, null, null)));
		//设置3张图片（最小化，最大化，退出）
		imageMin.setImage(new Image("resource/img/topandbottom/MinmizeDark.png"));
		imageMax.setImage(new Image("resource/img/topandbottom/MaximizeDark.png"));
		imageExit.setImage(new Image("resource/img/topandbottom/CloseDark.png"));
		//设置hbox的内边距
		hboxBar.setPadding(new Insets(0,10,0,20));
		bottomPane.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, null, null)));
		leftPane.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, null, null)));
	}
	
	/***
	 * 鼠标移入后最小化的图片替换为白色
	 * @param event
	 */
	@FXML
	public void imgMinToWhite(MouseEvent event) {
		//labelMin.setBackground(new Background(new BackgroundFill(Color.AQUA, null, null)));
		imageMin.setImage(new Image("resource/img/topandbottom/Minmize.png"));
		labelMin.setBackground(new Background(new BackgroundFill(Color.FLORALWHITE, null,null)));
	}
	/***
	 * 鼠标移入后最小化的图片替换为黑色
	 * @param event
	 */
	@FXML
	public void imgMinToBlack(MouseEvent event) {
		imageMin.setImage(new Image("resource/img/topandbottom/MinmizeDark.png"));
		labelMin.setBackground(new Background(new BackgroundFill(Color.AZURE, null,null)));
	}
	/***
	 * 鼠标移入时最大化图片替换为白色
	 * @param event
	 */
	@FXML
	public void imgMaxToWhite(MouseEvent event) {
		imageMax.setImage(new Image("resource/img/topandbottom/maximize.png"));
		labelMax.setBackground(new Background(new BackgroundFill(Color.FLORALWHITE, null,null)));
	}
	/***
	 * 鼠标移动时最大化的图片替换为黑色
	 * @param event
	 */
	@FXML
	public void imgMaxToBlack(MouseEvent event) {
		imageMax.setImage(new Image("resource/img/topandbottom/MaximizeDark.png"));
		labelMax.setBackground(new Background(new BackgroundFill(Color.AZURE, null, null)));
	}
	/***
	 * 鼠标移动时退出的图片替换为黑色
	 * @param event
	 */
	@FXML
	public void imgExitToWhite(MouseEvent event) {
		imageExit.setImage(new Image("resource/img/topandbottom/Close.png"));
		labelExit.setBackground(new Background(new BackgroundFill(Color.rgb(255, 10, 10), null, null)));
	}
	/***
	 * 鼠标移动时退出的图片替换为白色
	 * @param event
	 */
	@FXML
	public void imgExitToBlack(MouseEvent event) {
		imageExit.setImage(new Image("resource/img/topandbottom/CloseDark.png"));
		labelExit.setBackground(new Background(new BackgroundFill(Color.AZURE, null, null)));
	}
	/***
	 * 获取来自Main的stage
	 */
	/***
	 * 将Main里面的舞台传过来
	 * @param primaryStage
	 */
	public void setPrimary(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}
	/***
	 * 鼠标点击最小化后最小化窗口
	 * @param event
	 */
	@FXML
	public void minmizeWindow(MouseEvent event) {
		primaryStage.setIconified(true);
	}
	/***
	 * 点击最大化图标之后窗体会全面屏
	 * 再次点击后会还原到原来的位置和大小
	 * @param event
	 */
	@FXML
	public void maximizeWindow(MouseEvent event) {
		//判断当前窗口是否为最大化
		if(!this.primaryStage.isMaximized()){//正常状态
			//记录当前位置的坐标
			this.resetX = this.primaryStage.getX();
			this.resetY = this.primaryStage.getY();
			this.resetWidth = this.primaryStage.getWidth();
			this.reseHeight = this.primaryStage.getHeight();
			this.primaryStage.setMaximized(true);
			//更换最大化窗口的图标
			labelMax.setOnMouseExited(e->{
				imageMax.setImage(new Image("resource/img/topandbottom/resetDark.png"));
			});
			labelMax.setOnMouseEntered(e->{
				imageMax.setImage(new Image("resource/img/topandbottom/reset.png"));
			});
		}else {//最大化状态
			this.primaryStage.setX(resetX);
			this.primaryStage.setY(resetY);
			this.primaryStage.setWidth(resetWidth);
			this.primaryStage.setHeight(reseHeight);
			//设置还原状态
			this.primaryStage.setMaximized(false);
			labelMax.setOnMouseExited(e->{
				imageMax.setImage(new Image("resource/img/topandbottom/MaximizeDark.png"));
			});
			labelMax.setOnMouseEntered(e->{
				imageMax.setImage(new Image("resource/img/topandbottom/maximize.png"));
			});
		}
	}
	/***
	 * 退出程序，也就是关闭窗口
	 * @param event
	 */
	@FXML
	public void exitWindow(MouseEvent event) {
		System.exit(0);
	}
	/***
	 * 计算鼠标按下时距离我们写的窗口内的距离
	 * @param event
	 */
	@FXML
	public void culateSpace(MouseEvent event) {
		//记录按下的时候的坐标
		this.mouseX = event.getSceneX();
		this.mouseY = event.getSceneY();
	}
	/***
	 * 记录鼠标拖拽时距离windows窗口的距离
	 * @param event
	 */
	@FXML
	public void caculateLocation(MouseEvent event) {
		//设置新的x,y
		this.primaryStage.setX(event.getScreenX()-mouseX);
		this.primaryStage.setY(event.getScreenY()-mouseY);
	}
}
