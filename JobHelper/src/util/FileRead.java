package util;

import java.io.File;
import java.util.List;
/**
 * 读取某个路径下的文件
 * @author 航航
 *
 */
public interface FileRead {
	 public   List<File> readfile(File file) throws Exception;
}
