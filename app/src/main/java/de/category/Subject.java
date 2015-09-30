package de.category;

public class Subject implements CategoryIF{

    public String TYPE = "SUBJECT";
	Long id = null;
	String text_de = "";
	String text_en = "";
	String text_tr = "";
	Long category_id = null;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText_de() {
		return text_de;
	}
	public void setText_de(String text_de) {
		this.text_de = text_de;
	}
	public String getText_en() {
		return text_en;
	}
	public void setText_en(String text_en) {
		this.text_en = text_en;
	}
	public String getText_tr() {
		return text_tr;
	}
	public void setText_tr(String text_tr) {
		this.text_tr = text_tr;
	}
	public Long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

}
