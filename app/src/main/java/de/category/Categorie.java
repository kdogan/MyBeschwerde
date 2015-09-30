package de.category;

public class Categorie implements CategoryIF{
    public String TYPE = "CATEGORY";
	private Long id;
    private String text_en;
    private String text_de;
    private String text_tr;
    private String isCompanyCategory;
    private String isComplainCategory;
    private String parentId;
    private String categoryType;
    private String isTrash;
	
    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText_en() {
		return text_en;
	}
	public void setText_en(String text_en) {
		this.text_en = text_en;
	}
	public String getText_de() {
		return text_de;
	}
	public void setText_de(String text_de) {
		this.text_de = text_de;
	}
	public String getText_tr() {
		return text_tr;
	}
	public void setText_tr(String text_tr) {
		this.text_tr = text_tr;
	}
	public String getIsCompanyCategory() {
		return isCompanyCategory;
	}
	public void setIsCompanyCategory(String isCompanyCategory) {
		this.isCompanyCategory = isCompanyCategory;
	}
	public String getIsComplainCategory() {
		return isComplainCategory;
	}
	public void setIsComplainCategory(String isComplainCategory) {
		this.isComplainCategory = isComplainCategory;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}
	public String getIsTrash() {
		return isTrash;
	}
	public void setIsTrash(String isTrash) {
		this.isTrash = isTrash;
	}
     

}
