package service;

import java.io.File;
import java.util.Map;

import com.teamdev.jxbrowser.chromium.Browser;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public interface MultImport {
	public void importFile(File file,Map<Label, File>panMap,VBox vBox,ScrollPane scrollPane,Browser browser) throws Exception;
}
