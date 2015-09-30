package de.category;

/**
 * Created by Kamuran Dogan on 21.12.2014.
 */
public class Complains {
    Long id;
    String text;
    Long subjectId;
    String header;
    String companyLogo;
    String status;
    String date;
    Long userId;
    Long previous_company_contact;
    Long process_satisfaction;
    Long result_satisfaction;
    Long complain_type;
    Long complain_category;
    Double tags;
    //TODO create enum class with two types (yes, no) for the publish
    String publish;
    Long company_id;
    Boolean is_trash;
    //TODO create an enum-class with two types (READ, UNREAD) for the variable is_read
    String is_read;


    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public String getHeader() {
        return header;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getPrevious_company_contact() {
        return previous_company_contact;
    }

    public Long getProcess_satisfaction() {
        return process_satisfaction;
    }

    public Long getResult_satisfaction() {
        return result_satisfaction;
    }

    public Long getComplain_type() {
        return complain_type;
    }

    public Long getComplain_category() {
        return complain_category;
    }

    public Double getTags() {
        return tags;
    }

    public String getPublish() {
        return publish;
    }

    public Long getCompany_id() {
        return company_id;
    }

    public Boolean getIs_trash() {
        return is_trash;
    }

    public String getIs_read() {
        return is_read;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setPrevious_company_contact(Long previous_company_contact) {
        this.previous_company_contact = previous_company_contact;
    }

    public void setProcess_satisfaction(Long process_satisfaction) {
        this.process_satisfaction = process_satisfaction;
    }

    public void setResult_satisfaction(Long result_satisfaction) {
        this.result_satisfaction = result_satisfaction;
    }

    public void setComplain_type(Long complain_type) {
        this.complain_type = complain_type;
    }

    public void setComplain_category(Long complain_category) {
        this.complain_category = complain_category;
    }

    public void setTags(Double tags) {
        this.tags = tags;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public void setIs_trash(Boolean is_trash) {
        this.is_trash = is_trash;
    }

    public void setCompany_id(Long company_id) {
        this.company_id = company_id;
    }

    public void setIs_read(String is_read) {
        this.is_read = is_read;
    }
}
