package dto;

public class Faq_dto {
	String no, title, content, reg_date, reg_id, name;

	//목록 조회
	public Faq_dto(String no, String title, String content, String name, String reg_date) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.name = name;
		this.reg_date = reg_date;
	}


	public String getNo() {
		return no;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getReg_date() {
		return reg_date;
	}

	public String getReg_id() {
		return reg_id;
	}
	public String getName() {
		return name;
	}
	
	
}
