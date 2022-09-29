package jdbc.employee.vo;

public class Employee {
	private String employeeNO;
	private String employeeID;
	private String employeePW;
	private String teamCode;
	private String teamName;
	private String deptCode;
	private String deptName;
	private String jobCode;
	private String employeeName;
	private String employeeSSN;
	private String hireDate;
	private String entDate;
	private String entYN;
	private int loginNum;
	
	public Employee() {}
	
	public Employee(String employeeNO,String employeeID,String teamCode,String deptCode, String jobCode,String employeeName) {
		super();
		this.employeeNO = employeeNO;
		this.employeeID = employeeID;
		this.teamCode = teamCode;
		this.deptCode =deptCode;
		this.jobCode = jobCode;
		this.employeeName = employeeName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	public String getEmployeeNO() {
		return employeeNO;
	}

	public void setEmployeeNO(String employeeNO) {
		this.employeeNO = employeeNO;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmployeePW() {
		return employeePW;
	}

	public void setEmployeePW(String employeePW) {
		this.employeePW = employeePW;
	}

	public String getTeamCode() {
		return teamCode;
	}

	public void setTeamCode(String teamCode) {
		this.teamCode = teamCode;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeSSN() {
		return employeeSSN;
	}

	public void setEmployeeSSN(String employeeSSN) {
		this.employeeSSN = employeeSSN;
	}

	public String getHireDate() {
		return hireDate;
	}

	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}

	public String getEntDate() {
		return entDate;
	}

	public void setEntDate(String entDate) {
		this.entDate = entDate;
	}

	public String getEntYN() {
		return entYN;
	}

	public void setEntYN(String entYN) {
		this.entYN = entYN;
	}
	public int getLoginNum() {
		return loginNum;
	}

	public void setLoginNum(int loginNum) {
		this.loginNum = loginNum;
	}

	@Override
	public String toString() {
		return "Employee [employeeNO=" + employeeNO + ", employeeID=" + employeeID + ", teamCode=" + teamCode
				+ ", deptCode=" + deptCode + ", employeeName=" + employeeName + "]";
	}
	
	
}
