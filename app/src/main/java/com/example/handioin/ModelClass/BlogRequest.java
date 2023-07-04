package com.example.handioin.ModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class BlogRequest {

    public BlogRequest(Integer id, String blog1, String blog2, String blog3, String blogImage, String xblogImage2, String xaboutUs, String aboutUsImage, String updatedAt, String createdAt) {
        super();
        this.id = id;
        this.blog1 = blog1;
        this.blog2 = blog2;
        this.blog3 = blog3;
        this.blogImage = blogImage;
        this.xblogImage2 = xblogImage2;
        this.xaboutUs = xaboutUs;
        this.aboutUsImage = aboutUsImage;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
    }

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("blog1")
    @Expose
    private String blog1;
    @SerializedName("blog2")
    @Expose
    private String blog2;
    @SerializedName("blog3")
    @Expose
    private String blog3;
    @SerializedName("blogImage")
    @Expose
    private String blogImage;
    @SerializedName("XblogImage2")
    @Expose
    private String xblogImage2;
    @SerializedName("XaboutUs")
    @Expose
    private String xaboutUs;
    @SerializedName("aboutUsImage")
    @Expose
    private String aboutUsImage;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBlog1() {
        return blog1;
    }

    public void setBlog1(String blog1) {
        this.blog1 = blog1;
    }

    public String getBlog2() {
        return blog2;
    }

    public void setBlog2(String blog2) {
        this.blog2 = blog2;
    }

    public String getBlog3() {
        return blog3;
    }

    public void setBlog3(String blog3) {
        this.blog3 = blog3;
    }

    public String getBlogImage() {
        return blogImage;
    }

    public void setBlogImage(String blogImage) {
        this.blogImage = blogImage;
    }

    public String getXblogImage2() {
        return xblogImage2;
    }

    public void setXblogImage2(String xblogImage2) {
        this.xblogImage2 = xblogImage2;
    }

    public String getXaboutUs() {
        return xaboutUs;
    }

    public void setXaboutUs(String xaboutUs) {
        this.xaboutUs = xaboutUs;
    }

    public String getAboutUsImage() {
        return aboutUsImage;
    }

    public void setAboutUsImage(String aboutUsImage) {
        this.aboutUsImage = aboutUsImage;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
