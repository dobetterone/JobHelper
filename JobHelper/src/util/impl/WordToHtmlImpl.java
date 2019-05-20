package util.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.core.FileURIResolver;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.w3c.dom.Document;

import util.WordToHtml;

public class WordToHtmlImpl implements WordToHtml {

	@Override
	public void word2007ToHtml(String filePath,String fileName,String htmlName) throws Exception {
		//String filePath = "D://JobHelperWorkSpace/";
		//String fileName = "test.docx";
		//String htmlName = "Wtest.html";
		final String file = filePath+fileName;
		System.out.println(file);
		File f = new File(file);
		if(!f.exists()) {
			System.out.println("Sorry File not Exists!!");		
		}else {
			if(f.getName().endsWith(".docx")||f.getName().endsWith(".DOCX")) {
				//加载word文档生成XWPDFDocument对象
				InputStream intInputStream = new FileInputStream(f);
				XWPFDocument document = new XWPFDocument(intInputStream);
				//（2）解析XHTML配置（这里设置IURIResolver来设置图片存放的目录）
				File imageFolderFile  = new File(filePath);
				XHTMLOptions options = XHTMLOptions.create().URIResolver(new FileURIResolver(imageFolderFile));
				options.setExtractor(new FileImageExtractor(imageFolderFile));
				options.setIgnoreStylesIfUnused(false);
				options.setFragment(true);
				//System.out.println("filePath"+filePath);
				//(3)将XWPFDocument转化为XHTML
				OutputStream outputStream = new FileOutputStream(new File(filePath+htmlName));
				XHTMLConverter.getInstance().convert(document, outputStream, options);
			}else {
				System.out.println("Enter only MS Office 2007+ files");
			}
		}
	}

	@Override
	public void word2003ToHtml(String filePath,String fileName,String htmlName) throws Exception {
		//String filePath = "F:/a/";
		//final String imagePath = "f:/a/image/";
		//String fileName = "test.doc";
		//String htmlName = "test.html";
		//存放图片的路径
		final String imagePath = filePath+"image/";
		final String file = filePath+fileName;
		InputStream inputStream = new FileInputStream(new File(file));
		HWPFDocument wordDocument = new HWPFDocument(inputStream);
		WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
		//设置图片存放的位置
		wordToHtmlConverter.setPicturesManager(new PicturesManager() {
			
			@Override
			public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
				File imgPath = new File(imagePath);
				if(!imgPath.exists()) {//如果路径下文件不存在，则创建一个文件
					imgPath.mkdirs();
				}
				File file = new File(imagePath+suggestedName);
				try {
					OutputStream outputStream = new FileOutputStream(file);
					outputStream.write(content);
					outputStream.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return imagePath+suggestedName;
			}
		});
		//解析word文档
		wordToHtmlConverter.processDocument(wordDocument);
		Document htmlDocument = wordToHtmlConverter.getDocument();
		File htmlFile = new File(filePath+htmlName);
		OutputStream outputStream = new FileOutputStream(htmlFile);
		DOMSource domSource = new DOMSource(htmlDocument);
		StreamResult streamResult = new StreamResult(outputStream);
		
		
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer serializer = factory.newTransformer();
		serializer.setOutputProperty(OutputKeys.ENCODING,"utf-8");
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		serializer.setOutputProperty(OutputKeys.METHOD,"html");
		serializer.transform(domSource, streamResult);
		outputStream.close();
	}

	

}
