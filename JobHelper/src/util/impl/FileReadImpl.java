package util.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import util.FileRead;

public class FileReadImpl implements FileRead {

	@Override
	public List<File> readfile(File file) throws Exception {
		List<File> list = new ArrayList<>();
		String[] filelist = file.list();
		for (int i = 0; i < filelist.length; i++) {
			File readfile = new File(file.getPath() + "\\" + filelist[i]);// 组装成每个文件的地址
			if (readfile.getName().endsWith(".doc") || readfile.getName().endsWith(".docx")) {
				// System.out.println("path=" + readfile.getPath());
				// System.out.println("absolutepath="+ readfile.getAbsolutePath());
				// System.out.println("name="+ readfile.getName());
				// System.out.println(readfile.getName().endsWith("doc"));
				list.add(readfile);
			}
		}

		return list;// list里面装的是文件
	}

}
