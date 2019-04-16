package util;

public interface DrawInterface {
	static boolean isBottomRight = false;      //判断光标是否在左下角
	final static double primarystageWidth = 1480 ;
	final static double primarystageHeight = 900 ;
	static double similarRatio = (double)primarystageWidth/primarystageHeight;
	public void addDrawFunc() throws Exception; 
}
