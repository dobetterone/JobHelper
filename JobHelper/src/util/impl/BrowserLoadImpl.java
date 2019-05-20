package util.impl;


import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserContext;
import com.teamdev.jxbrowser.chromium.BrowserContextParams;
import com.teamdev.jxbrowser.chromium.javafx.BrowserView;

import javafx.scene.layout.StackPane;
import util.BrowserLoad;

public class BrowserLoadImpl implements BrowserLoad {

	@Override
	public void brLoad(BrowserContext browserContext, Browser browser, BrowserView browserView, String loadPath,StackPane Contentsrollpane)
			throws Exception {
		browserContext = new BrowserContext(new BrowserContextParams("D:\\my-data1"));
		browser = new Browser(browserContext);
		browserView = new BrowserView(browser);
		Contentsrollpane.getChildren().add(browserView);
	}
	
}
