package service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import com.teamdev.jxbrowser.chromium.Browser;

import javafx.geometry.Orientation;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import service.MultImport;
import util.FileRead;
import util.WordToHtml;
import util.impl.FileReadImpl;
import util.impl.WordToHtmlImpl;
/***
 * 导入文件，并且为标签加上事件
 * @author 航航
 *
 */
public class ImportImpl implements MultImport {
	
	@Override
	public void importFile(File file, Map<Label, File> map_leftPane, VBox listVox, ScrollPane scrollPane,Browser browser) throws Exception {
		if(file!=null)
		{
			try {
				FileRead fileRead = new FileReadImpl();
				List<File> list=fileRead.readfile(file);
				if(list!=null)
				{
				
				for(File f:list)
				{
					
					Label label=new Label(f.getName());
					/***
					 * 后期这部分内容会专门写一个lable组件，先暂时在此部分写
					 */
					//设置label属性
					label.setPrefHeight(50);
					//鼠标移上去变色
					label.setOnMouseEntered(e->{
						label.setBackground(new Background(new BackgroundFill(Color.ANTIQUEWHITE, null, null)));
					});
					//鼠标移出还原
					label.setOnMouseExited(e->{
						label.setBackground(new Background(new BackgroundFill(Color.AZURE, null, null)));
					});
					//鼠标点击事件
					//TODO
					label.setOnMouseClicked(e->{
						String FullFileName = map_leftPane.get(label).getName();
						String GenericFileName = FullFileName.substring(0,FullFileName.indexOf("."));
						//System.out.println("name="+fileName);
						//转化为html
						WordToHtml wordToHtml = new WordToHtmlImpl();
						try {
							//String filePath = "D://JobHelperWorkSpace/";
							String filePath  = map_leftPane.get(label).getCanonicalPath();
							String curentFilePath = filePath.substring(0,filePath.lastIndexOf("\\")+1);
							//System.out.println("filePath="+curentFilePath);
							String fileName = FullFileName;
							String htmlName = GenericFileName+".html";
							//获取后缀名
							String suffix = FullFileName.substring(FullFileName.lastIndexOf(".")+1);
							if("docx".equals(suffix)){//如果word文档是docx
								wordToHtml.word2007ToHtml(curentFilePath, fileName, htmlName);
								System.out.println("转换完成啦");
							}else if("doc".equals(suffix)) {//如果word文档是doc
								wordToHtml.word2003ToHtml(curentFilePath, fileName, htmlName);
								System.out.println("转换完成啦");
							}
							//System.out.println("后缀名="+suffix);
							
							//System.out.println("老啦老弟="+curentFilePath);
							String repCurentFilePath = curentFilePath.replace("\\", "//");
							//System.out.println("老啦老弟="+repCurentFilePath);
							//System.out.println("file:///"+"C://Users//航航//Desktop//test"+""+GenericFileName+".html");
							
							browser.loadURL("file:///"+repCurentFilePath+""+GenericFileName+".html");
							System.out.println("加载完成啦");
						} catch (Exception e1) {
							
							e1.printStackTrace();
						}
					});
					map_leftPane.put(label, f);
					
				
				}
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<Label>list1 = new ArrayList<>();
			listVox = new VBox();
			for (Label key : map_leftPane.keySet())
			{
				//listVox.getChildren().add(key);
				
				key.setTextFill(Color.BLUE);
				System.out.println();
				list1.add(key);
				System.out.println("test="+map_leftPane.get(key));
			}
			for (File key1 : map_leftPane.values())
			{
				//listVox.getChildren().add(key);
				
				System.out.println(key1);
			}
			listVox.getChildren().addAll(list1);
			listVox.setBackground(new Background(new BackgroundFill(Color.AZURE, null, null)));
			scrollPane.setContent(listVox);
			
		}
		else
		{
			JOptionPane.showMessageDialog(null, "您还没有打开任何文件！", "错误", JOptionPane.ERROR_MESSAGE);
		}
	}

	
}
