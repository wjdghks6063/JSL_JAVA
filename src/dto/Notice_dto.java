package dto;

public class Notice_dto {
	String no, title, content, reg_id, reg_date, name;
	int hit;
	
	
	
	//등록,삭제,수정
	public Notice_dto(String no, String title, String content, String reg_id, String reg_date) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.reg_id = reg_id;
		this.reg_date = reg_date;
	}
	
	
	//목록 조회용
	public Notice_dto(String no, String title, String name, String reg_date, int hit) {
		this.no = no;
		this.title = title;
		this.name = name;
		this.reg_date = reg_date;
		this.hit = hit;
	}
	
	
	//상세 조회용
	public Notice_dto(String no, String title, String content, String name, String reg_date, int hit) {
		this.no = no;
		this.title = title;
		this.content = content;
		this.name = name;
		this.reg_date = reg_date;
		this.hit = hit;
	}
	
	//이전 페이지,다음 페이지
	public Notice_dto(String no, String title) {
		this.no = no;
		this.title = title;
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
