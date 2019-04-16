package controller;

import java.awt.Font;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.Scrollable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import service.ExchangeData;
import service.impl.ExchangeDataImpl;
import util.MsonModel;
import util.StringUtil;
import util.VboxUtil;


public class MsonController implements Initializable {
	@FXML
	BorderPane rootPane;
	@FXML
	BorderPane topPane;
	@FXML
	BorderPane bottomPane;
	@FXML
	BorderPane CcenterPane;
	@FXML
	TextField textFiledModelNum;
	@FXML
	TextField textFiledGradeName;
	@FXML
	TextField textFiledClassName;
	@FXML 
	TextField textFiledModelName;
	@FXML
	TextField textFiledModelAllScore;
	@FXML
	BorderPane CtopPane;
	@FXML 
	//退出的标签
	Label labelExit;
	@FXML
	//退出的图片
	ImageView imageExit;
	@FXML 
	ScrollPane ModelScrollPane;
	@FXML
	VBox MsonVbox;
	//鼠标相对于窗口内的位置
	double mouseX,mouseY;
	//窗口位置的原值和窗口的大小
	double resetX,resetY,resetWidth,reseHeight;
	int modelNum; //用户输入的模块数
	int modelOldNum;//上一次用户输入的模块数（为了和上一次比较如果一样则不创建，如果不一样就重新创建）
	String moddelName;//用户输入模块的名字
	String gradeName; //年级名字
	String className;//班级名称
	List<Integer> scoreList;//存储用户输入的每个模块的分数
	@FXML
	//这是控制封装（最大化，最小化，退出）label的hbox
	HBox hboxBar;
	//main里面的舞台
	Stage stage;
	//输入域
	@FXML
	TextArea textArea;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//设置总BorderPane面板的背景
		rootPane.setBackground(new Background(new BackgroundFill(Color.WHITE,null, null)));
		//设置BorderPane面板上部分的背景
		topPane.setBackground(new Background(new BackgroundFill(Color.GAINSBORO, null, null)));
		//设置3张图片（最小化，最大化，退出）
		imageExit.setImage(new Image("resource/img/topandbottom/CloseDark.png"));
		//设置hbox的内边距
		hboxBar.setPadding(new Insets(15,5,5,10));//上右下左
		bottomPane.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, null, null)));
		CcenterPane.setBackground(new Background(new BackgroundFill(Color.LIGHTSKYBLUE, null, null)));
		CtopPane.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
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
	public void setPrimary(Stage stage) {
		this.stage = stage;
	}
	/***
	 * 退出程序，也就是关闭窗口
	 * @param event
	 */
	@FXML
	public void exitWindow(MouseEvent event) {
		stage.close();//关闭当前窗口
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
		this.stage.setX(event.getScreenX()-mouseX);
		this.stage.setY(event.getScreenY()-mouseY);
	}
	@FXML
	public void transportData(ActionEvent event){//TODO  
		//获取来之子窗体的数据
		ExchangeData exchangeData = new ExchangeDataImpl();
		try {
			exchangeData.exchange(modelNum, moddelName, scoreList, gradeName, className);    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	public void getModelInfo(ActionEvent event){//TODO
		String modelNumStr = textFiledModelNum.getText();
		StringUtil stringUtil = new StringUtil();
		if(stringUtil.isNullOrEmpty(modelNumStr))
			textFiledModelNum.setPromptText("请输入模块数！");
		else
			modelNum = Integer.parseInt(textFiledModelNum.getText());   //获取输入框中的模块数
		//当多次点击创建的时候看msonVbox中是否有模块,反正重复创建
		VboxUtil vboxUtil = new VboxUtil();
		if(!vboxUtil.isNUll(MsonVbox)&&modelOldNum==modelNum)
				return;
		//moddelName = textFiledModelName.getText(); //获取模块的名称
		//scoreList = null;//TODO        //每个模块的总分装入list
		//gradeName = textFiledGradeName.getText();//获取年级的名字
		//className = textFiledClassName.getText();//获取班级的名字
		//System.out.println(modelNum);
		//每次创建应当清空之前创建的
		vboxUtil.clearVbox(MsonVbox);
		MsonModel msonModel = new MsonModel(modelNum);
		//System.out.println(msonModel.createMasonModel().isEmpty());
		MsonVbox.getChildren().addAll(msonModel.createMasonModel());
		modelOldNum = modelNum;
		MsonVbox.setSpacing(20);
		//ModelScrollPane.setContent(value);
		//msonModel.createMasonModel();
	}
	
}
