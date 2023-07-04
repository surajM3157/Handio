package com.example.handioin.ModelClass;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class otpResponseModal {
    @SerializedName("array")
    @Expose
    private List<otpRequestModal> array = null;

    public List<otpRequestModal> getArray() {
        return array;
    }
    public void setArray(List<otpRequestModal> array) {
        this.array = array;
    }
}
