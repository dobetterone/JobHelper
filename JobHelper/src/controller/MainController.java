package controller;

/***
 * 这是一个控制器，实现了Initializable接口
 * @author 航航
 *
 */
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.sun.xml.internal.bind.v2.runtime.Name;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserContext;
import com.teamdev.jxbrowser.chromium.BrowserContextParams;
import com.teamdev.jxbrowser.chromium.javafx.BrowserView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.web.HTMLEditor;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.StudentModel;
import service.MultImport;
import service.SubPrimaryStage;
import service.impl.ImportImpl;
import service.impl.SubPrimaryStageImpl;
import util.BrowserLoad;
import util.CreateMainModel;
import util.DrawInterface;
import util.ModelInfoManagent;
import util.StageManagement;
import util.makeDefautWorkeSpace;
import util.impl.BrowserLoadImpl;
import util.impl.DrawUtil;
import util.impl.makeDefaultWorkSpaceImpl;
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
	ScrollPane scrollPane;
	String all="";//仅仅测试用
	@FXML
    BorderPane bottompane_leftpane;
	@FXML
	BorderPane bottompane_rightpane;
	@FXML
	HBox bottompane_centerpane;
	//批量导入的菜单选项
	@FXML
	MenuItem bulkImport;
	//设置的菜单选项
	@FXML 
	MenuItem settings;
	@FXML
	Label testArea;
	@FXML
	HTMLEditor htmlEditor;
	@FXML
	StackPane Contentsrollpane;
	@FXML
	VBox listVox;
	//工作区间的文件夹的路径
	String WorkSpacePath;
	BrowserContext  bc;
	Browser browser2;
	BrowserView browserView;
	Map<Label, File> map_leftPane=new HashMap<Label,File>();//存放显示的label和文件 : label显示文件姓名，file为读取的文件
	//默认的工作空间路径
	private static final String workSpaPath="D://JobHelperWorkSpace";
	//默认的jxBrowser的默认路径
	private static final String jxBrFilePath="D://JobHelperWorkSpace//jxBrowser-data";
	StudentModel studentModel;//学生信息
	@FXML
	Label StudentInfoLable; //显示学生信息的标签
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//mainController一加载便将mainController加入StageManagement
		StageManagement.CONTROLLER.put("MainController", this);
		StageManagement.STAGE.put("primaryStage", primaryStage);
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
		bottompane_leftpane.setBackground(new Background(new BackgroundFill(Color.BISQUE, null,null)));
		bottompane_leftpane.setPrefWidth(300);
		//listVox.setBackground(new Background(new BackgroundFill(Color.AZURE, null, null)));
		//scrollPane.setBackground(new Background(new BackgroundFill(Color.AZURE, null, null)));
		Label label = new Label("你好");
		TextField textField = new TextField();
		List<Node>list = new ArrayList<>();
		//list.add(textField);
		list.add(label);
		//TODO
		//bottompane_centerpane.getChildren().add(model("现状分析"));
		//bottompane_centerpane.getChildren().add(model("学习目标"));
		//bottompane_centerpane.getChildren().add(model("未来目标"));
		//bottompane_centerpane.getChildren().add(model("短期计划"));
		//bottompane_centerpane.getChildren().add(model("长期计划"));
		//bottompane_centerpane.getChildren().add(model("专业分析"));
		//bottompane_centerpane.getChildren().add(model("专业前景"));
		//bottompane_centerpane.setSpacing(60);
		//bottompane_centerpane.setPadding(new Insets(20, 0, 0, 20));
		
		//默认的工作区间
		/***
		 * 如果用户没有选择工作区间，则我们默认的工作区间为D:/JobHelperWorkSpace
		 * JobHelperWorkSpace下面有一个jxBrowser-data的文件夹是存放jxBrowser的文件 
		 */
		//构建一个默认工作空间
		makeDefautWorkeSpace makeDefautWorkeSpace = new makeDefaultWorkSpaceImpl();
		try {
			makeDefautWorkeSpace.makeDaufWorkSpa(workSpaPath,jxBrFilePath);
		} catch (Exception e) {
			System.out.println("创建默认工作区间失败！！！");
			e.printStackTrace();
		}
		//加载浏览器
		bc = new BrowserContext(new BrowserContextParams("jxBrFilePath"));
		browser2 = new Browser(bc);
		browserView = new BrowserView(browser2);
		Contentsrollpane.getChildren().add(browserView);
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
		primaryStage.close();
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
	/***
	 * 当鼠标按下“批量导入时”
	 * @param event
	 */
	@FXML
	public void importOnMouseClicked(ActionEvent event){
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("批量导入");
		DirectoryChooser directoryChooser=new DirectoryChooser();
		File file= directoryChooser.showDialog(primaryStage);
		MultImport multImport = new ImportImpl();
		try {
			multImport.importFile(file,map_leftPane, listVox,scrollPane,browser2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@FXML
	public void settingOnMouseClicked(ActionEvent event){
		SubPrimaryStage subPrimaryStage = (SubPrimaryStage) new SubPrimaryStageImpl();
		try {
			subPrimaryStage.openPrimaryStage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/***
	 * 仅仅是一个测试用的方法
	 * @param event
	 * @throws IOException 
	 */
	@FXML
	public void test(MouseEvent event) throws IOException{
		//TODO根据选择的路径，去加载相应的html文件
		browser2.loadURL("file:///"+workSpaPath+"/Wtest.html");
	}
	@FXML
	public void SelectWorkSpace() {
		DirectoryChooser directoryChooser=new DirectoryChooser();
		File file = directoryChooser.showDialog(primaryStage);
		WorkSpacePath = file.getPath();
		System.out.println(WorkSpacePath);
	}
	
	public void getStuModlInfo(StudentModel studentModel) {
		this.studentModel = studentModel;
		String colleaName = studentModel.getColleaName();
		//System.out.println("colleage="+colleaName);
		String gradeName = studentModel.getGradeName();
		String className = studentModel.getClassName();
		//将学生信息学生信息标签
		StudentInfoLable.setText(colleaName+gradeName+className);
		//产生模块之前需要清空（防止第二次操作时，将第一次的模块加入）
		bottompane_centerpane.getChildren().clear();
		//主界面产生模块
		//首先获取模块数
		int modelNum = studentModel.getModelNum();
		//循环获取模块数
		for(int i=1;i<=modelNum;i++) {
			//获取模块名字
			String modelName = ModelInfoManagent.modepNameMap.get(i).getText();
			//获取每个模块的总分
			String modelAllScore = ModelInfoManagent.modelAllScoreMap.get(i).getText();
			//产生一个模块并且装入
			bottompane_centerpane.getChildren().add(CreateMainModel.model(modelName+"["+modelAllScore+"]"));
		}
		
		bottompane_centerpane.setSpacing(60);
		bottompane_centerpane.setPadding(new Insets(20, 0, 0, 20));
	}
}
