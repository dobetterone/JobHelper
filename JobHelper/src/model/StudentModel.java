package model;
/**
 *
 * @author 航航
 *
 */
public class StudentModel {
	private String colleaName;//学院名称
	private String gradeName;//年级名称
	private String className;//班级名称
	private int ModelNum;//模块个数
	public StudentModel() {
		// TODO Auto-generated constructor stub
	}
	public StudentModel(String colleaName, String gradeName, String className, int modelNum) {
		super();
		this.colleaName = colleaName;
		this.gradeName = gradeName;
		this.className = className;
		ModelNum = modelNum;
	}
	public String getColleaName() {
		return colleaName;
	}
	public void setColleaName(String colleaName) {
		this.colleaName = colleaName;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getModelNum() {
		return ModelNum;
	}
	public void setModelNum(int modelNum) {
		ModelNum = modelNum;
	}
	
}
