package util.impl;

import java.io.File;

import util.makeDefautWorkeSpace;

public class makeDefaultWorkSpaceImpl implements makeDefautWorkeSpace {

	@Override
	public void makeDaufWorkSpa(String workSpaPath, String jxBrFilePath) throws Exception {
		/*
		File workSpacefile = new File("D://JobHelperWorkSpace");
		if(!workSpacefile.exists()) {
			workSpacefile.mkdir();
		}
		File jxBrowserFile = new File("D://JobHelperWorkSpace//jxBrowser-data"); 
		if(!jxBrowserFile.exists()) {
			jxBrowserFile.mkdir();
		}
		*/
		File workSpacefile = new File("workSpaPath");
		if(!workSpacefile.exists()) {
			workSpacefile.mkdir();
		}
		File jxBrowserFile = new File("jxBrFilePath"); 
		if(!jxBrowserFile.exists()) {
			jxBrowserFile.mkdir();
		}
	}

}
