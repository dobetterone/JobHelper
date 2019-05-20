package util;

public interface WordToHtml {
	/**
	 * 这是2007版本以上的word转html
	 * @param filePath
	 * @param fileName
	 * @param htmlName
	 * @throws Exception
	 */
	public void word2007ToHtml(String filePath,String fileName,String htmlName) throws Exception;
	/**
	 * 这是2003版本的word转html
	 * @param filePath
	 * @param fileName
	 * @param htmlName
	 * @throws Exception
	 */
	public void word2003ToHtml(String filePath,String fileName,String htmlName) throws Exception;
}
