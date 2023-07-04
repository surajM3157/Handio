package com.example.handioin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.handioin.ModelClass.BlogRequest;
import com.example.handioin.ModelClass.BlogResponseModal;
import com.example.handioin.RetrofitClient.BaseUrlClass;
import com.example.handioin.RetrofitClient.RetrofitClientInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlogActivity extends AppCompatActivity {
    private ImageView ivBackArrow;
    private ImageView backArrow, ivimg;
    private TextView tvWebview, tvwebview1, tvwebview2, tvwebview3, tvwebview4;

//    private RecyclerView rvWebViwe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        ivBackArrow = (ImageView) findViewById(R.id.backArrow);
        ivBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), HomeActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        });
        blogData();

//        webView = (WebView) findViewById(R.id.webView);
//        ProgressDialog progressDialog = ProgressDialog.show(BlogActivity.this,"Loading","Please Wait",true);
//        progressDialog.setCancelable(false);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setLoadsImagesAutomatically(true);
//        webView.getSettings().setUseWideViewPort(true);
//        webView.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                progressDialog.show();
//                view.loadUrl(url);
//                return true;
//            }
//            @Override
//            public void onPageFinished(WebView view, String url) {
//               progressDialog.dismiss();
//            }
//        });
//        webView.loadUrl("https://handio.in/api/Blog");

    }

    private void blogData() {
        Call<BlogResponseModal> call;
        ProgressDialog dialog = new ProgressDialog(BlogActivity.this);
        dialog.setMessage(" Loading... ");
        dialog.show();
        RetrofitClientInterface object = BaseUrlClass.getRetrofitClient(BlogActivity.this).create(RetrofitClientInterface.class);
        call = object.getDataBlog();
        call.enqueue(new Callback<BlogResponseModal>() {

            @Override
            public void onResponse(Call<BlogResponseModal> call, Response<BlogResponseModal> response) {
                Log.d("aktu", "done");
                if (response.isSuccessful()) {
                    BlogResponseModal responseModal = response.body();
                    List<BlogRequest> blog = responseModal.getBlog();

                    for (int i = 0; i < blog.size(); i++) {

                        String blo1 = blog.get(i).getBlog1();
                        Spanned html = Html.fromHtml(blo1); // used by TextView
                        tvWebview = (TextView) findViewById(R.id.tv_webview);
                        tvWebview.setText(html);

                        String blo2 = blog.get(i).getBlog2();
                        Spanned html1 = Html.fromHtml(blo2);
                        tvwebview1 = findViewById(R.id.tv_webview1);
                        tvwebview1.setText(html1);

                        String blo3 = blog.get(i).getBlog3();
                        Spanned html3 = Html.fromHtml(blo3);
                        tvwebview2 = findViewById(R.id.tv_webview2);
                        tvwebview2.setText(html3);

                        String blo4 = blog.get(i).getBlogImage();
                        ivimg = (ImageView) findViewById(R.id.iv_img1);

//                        Glide.with(BlogActivity.this)
//                                .load("2210171349634d5e31c7df0.jpg")
//                                .placeholder(R.drawable.image1)
//                                .error(R.drawable.bloga)
//                                .override(200, 200)
//                                .centerCrop()
//                                .into(ivimg);

                        Glide.with(BlogActivity.this)
                                .load("2210171349634d5e31c7df0.jpg")
                                .apply(new RequestOptions().override(150, 100))
                                .placeholder(R.drawable.blog)
                                .into(ivimg);

                        String blo5 = blog.get(i).getAboutUsImage();
                        String blo6 = blog.get(i).getCreatedAt();
                        String blo7 = blog.get(i).getXaboutUs();
                        Spanned html7 = Html.fromHtml(blo7);
                        tvwebview3 = findViewById(R.id.tv_webview3);
                        tvwebview3.setText(html7);

                        String blo8 = blog.get(i).getXblogImage2();
                        String blo9 = blog.get(i).getUpdatedAt();
                    }
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<BlogResponseModal> call, Throwable t) {
            }
        });
    }
}