package util;

import javafx.scene.layout.VBox;
public class VboxUtil {
	/***
	 * 判断vox中有没有内容
	 * @param vBox
	 * @return
	 */
	public static boolean isNUll(VBox vBox){
		return vBox.getChildren().isEmpty();
	}
	/***
	 * 清空一个vbox
	 * @param vBox
	 */
	public static void clearVbox(VBox vBox){
		vBox.getChildren().clear();
	}
}
