package dto;

public class Qna_dto {
	String no, q_title, q_content, q_reg_id, q_reg_date, q_name,
	 		a_content, a_reg_id, a_reg_date, a_name, secret;
	int hit;
	
	//상세 보기
	public Qna_dto(String no, String q_title, String q_content, String q_name, String q_reg_id, String q_reg_date,  String a_content,
			String a_name, String a_reg_date, String secret, int hit) {
		super();
		this.no = no;
		this.q_title = q_title;
		this.q_content = q_content;
		this.q_name = q_name;
		this.q_reg_id = q_reg_id;
		this.q_reg_date = q_reg_date;
		this.a_content = a_content;
		this.a_name = a_name;
		this.a_reg_date = a_reg_date;
		this.secret = secret;
		this.hit = hit;
	}
	
	//질문 등록,삭제,수정
	public Qna_dto(String no, String a_contnet, String a_reg_id, String a_reg_date) {
		this.no = no;
		this.a_content = a_contnet;
		this.a_reg_id = a_reg_id;
		this.a_reg_date = a_reg_date;
	}
	
		
	//등록,삭제,수정
	public Qna_dto(String no, String q_title, String q_contnet, String q_reg_id, String secret, String q_reg_date) {
		this.no = no;
		this.q_title = q_title;
		this.q_content = q_contnet;
		this.q_reg_id = q_reg_id;
		this.secret = secret;
		this.q_reg_date = q_reg_date;
	}
	

	//목록 조회용
	public Qna_dto(String no, String q_title, String a_content, String q_name, 
					String q_reg_date, String secret, String q_reg_id, int hit) {
		this.no = no;
		this.q_title = q_title;
		this.a_content = a_content;
		this.q_name = q_name;
		this.q_reg_date = q_reg_date;
		this.secret = secret;
		this.q_reg_id = q_reg_id;
		this.hit = hit;
	}
	
	//전체 목록
	public Qna_dto(String no, String q_title, String q_content, String q_reg_id, String q_reg_date, String q_name,
			String a_title, String a_content, String a_reg_id, String a_reg_date, String a_name, String secret,
			int hit) {
		this.no = no;
		this.q_title = q_title;
		this.q_content = q_content;
		this.q_reg_id = q_reg_id;
		this.q_reg_date = q_reg_date;
		this.q_name = q_name;
		this.a_content = a_content;
		this.a_reg_id = a_reg_id;
		this.a_reg_date = a_reg_date;
		this.a_name = a_name;
		this.secret = secret;
		this.hit = hit;
	}
	
	public String getNo() {
		return no;
	}

	public String getQ_title() {
		return q_title;
	}

	public String getQ_content() {
		return q_content;
	}

	public String getQ_reg_id() {
		return q_reg_id;
	}

	public String getQ_reg_date() {
		return q_reg_date;
	}

	public String getQ_name() {
		return q_name;
	}

	public String getA_content() {
		return a_content;
	}

	public String getA_reg_id() {
		return a_reg_id;
	}

	public String getA_reg_date() {
		return a_reg_date;
	}

	public String getA_name() {
		return a_name;
	}

	public String getSecret() {
		return secret;
	}

	public int getHit() {
		return hit;
	}
	
	
	
	
}
