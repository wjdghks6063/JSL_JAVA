package dto;

public class Member_dto {
	String id,name,password,belong,linetell_1,linetell_2,linetell_3,
		tell_1,tell_2,tell_3,email_1,email_2,reg_date,exit_yn,exit_date;

	public Member_dto(String id, String name, String password, String belong, String linetell_1, String linetell_2,
			String linetell_3, String tell_1, String tell_2, String tell_3, String email_1, String email_2,
			String reg_date, String exit_yn, String exit_date) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.belong = belong;
		this.linetell_1 = linetell_1;
		this.linetell_2 = linetell_2;
		this.linetell_3 = linetell_3;
		this.tell_1 = tell_1;
		this.tell_2 = tell_2;
		this.tell_3 = tell_3;
		this.email_1 = email_1;
		this.email_2 = email_2;
		this.reg_date = reg_date;
		this.exit_yn = exit_yn;
		this.exit_date = exit_date;
	}
	

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getBelong() {
		return belong;
	}

	public String getLinetell_1() {
		return linetell_1;
	}

	public String getLinetell_2() {
		return linetell_2;
	}

	public String getLinetell_3() {
		return linetell_3;
	}

	public String getTell_1() {
		return tell_1;
	}

	public String getTell_2() {
		return tell_2;
	}

	public String getTell_3() {
		return tell_3;
	}

	public String getEmail_1() {
		return email_1;
	}

	public String getEmail_2() {
		return email_2;
	}

	public String getReg_date() {
		return reg_date;
	}

	public String getExit_yn() {
		return exit_yn;
	}

	public String getExit_date() {
		return exit_date;
	}
	
}
