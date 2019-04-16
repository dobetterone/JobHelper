package util;

import java.util.HashMap;
import java.util.Map;

import javafx.stage.Stage;
/***
 * 这是一个Stage管理类，将所有的Stage,controller传进来，可进行获取进行
 * 实现多个窗口之间的传值（以键值对的方式存储）
 * @author 航航
 *
 */
public class StageManagement {
	public static Map<String, Stage>STAGE= new HashMap<>();
	public static Map<String,Object>CONTROLLER = new HashMap<>();
}
