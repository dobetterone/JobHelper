package util;
//这是为了模块数据管理

import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.TextField;
/**
 * 这是一个管理器（模块和这个模块的总分以键值对的形式存储）
 * @author 航航
 *
 */
public class ModelInfoManagent {
	/**
	 * 这是装moddelName的map
	 */
	public static Map<Integer, TextField>modepNameMap = new HashMap<>();
	/***
	 * 这是装modelAllScore的map
	 */
	public static Map<Integer, TextField>modelAllScoreMap = new HashMap<>();
	/**
	 * 清空moddelName和modelAllScore里面的所有内容
	 */
	public static void clearModelInfoManagent() {
		modepNameMap.clear();
		modelAllScoreMap.clear();
	}
}
