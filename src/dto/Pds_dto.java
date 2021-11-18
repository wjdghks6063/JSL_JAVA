package dto;

public class Pds_dto {
	String no, title, content, attach, name, reg_id, reg_date;
	int hit;
	
	//저장
	public Pds_dto(String no, String title, String content, String attach, String reg_id, String reg_date) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.attach = attach;
		this.reg_id = reg_id;
		this.reg_date = reg_date;
	}

	//목록조회용
	public Pds_dto(String no, String title, String attach, String name, String reg_date, int hit) {
		this.no = no;
		this.title = title;
		this.attach = attach;
		this.name = name;
		this.reg_date = reg_date;
		this.hit = hit;
	}
	//상세 보기
	public Pds_dto(String no, String title, String content, String attach, String name, String reg_date, int hit) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.attach = attach;
		this.name = name;
		this.reg_date = reg_date;
		this.hit = hit;
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

	public String getAttach() {
		return attach;
	}

	public String getName() {
		return name;
	}

	public String getReg_id() {
		return reg_id;
	}

	public String getReg_date() {
		return reg_date;
	}

	public int getHit() {
		return hit;
	}
	
	
}
