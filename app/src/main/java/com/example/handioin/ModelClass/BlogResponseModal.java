package com.example.handioin.ModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BlogResponseModal {
    @SerializedName("blog")
    @Expose
    private List<BlogRequest> blog;

    public List<BlogRequest> getBlog() {
        return blog;
    }

    public void setBlog(List<BlogRequest> blog) {
        this.blog = blog;
    }
}
