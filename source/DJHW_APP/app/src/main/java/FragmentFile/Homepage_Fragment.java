package FragmentFile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import andbook.example.protect_me_release.R;

public class Homepage_Fragment extends Fragment {

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle
                             savedInstanceState){
        View v= inflater.inflate(R.layout.fragment_hompageview,container,false);

        WebView mWebView = (WebView) v.findViewById(R.id.web); //레이아웃과 연결
        mWebView.setWebViewClient(new WebViewClient()); //클라이언트 셋팅
        WebSettings mWebSettings = mWebView.getSettings(); //웹 셋팅
        mWebSettings.setJavaScriptEnabled(false); //웹 사이트의 자바스크립트 사용 시 true 미 사용시 false 추후 xss 공격의 여지있음
        mWebView.loadUrl("http://b255fish.iptime.org:8080/DJHW/index.jsp"); //연결 웹사이트 주소
        return v;
    }
}
