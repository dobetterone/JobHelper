package util;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserContext;
import com.teamdev.jxbrowser.chromium.javafx.BrowserView;

import javafx.scene.layout.StackPane;

public interface BrowserLoad {
	public void brLoad(BrowserContext browserContext,Browser browser, BrowserView browserView, String loadPath,StackPane Contentsrollpane) throws Exception;
}
