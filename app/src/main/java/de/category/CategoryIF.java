package de.category;

public interface CategoryIF {
    public String TYPE = "CATEGORY_IF";
	public Long getId();

	public void setId(Long id);

	public String getText_en();

	public void setText_en(String text_en);

	public String getText_de();

	public void setText_de(String text_de);

	public String getText_tr();

	public void setText_tr(String text_tr);

}
