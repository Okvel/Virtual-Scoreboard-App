package by.liauko.minskscoreboard.fragment;

import android.app.Fragment;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import by.liauko.minskscoreboard.R;

public class WebViewFragment extends Fragment {
    private SharedPreferences mPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.webview_fragment, null);
        initWebView(view);

        return view;
    }

    private void initWebView(View view) {
        WebView webView = (WebView) view.findViewById(R.id.web_view);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        String url = mPreferences.getString("url", "http://www.minsktrans.by/lookout_yard/");
        webView.loadUrl(url);
    }

    public void setPreferences(SharedPreferences mPreferences) {
        this.mPreferences = mPreferences;
    }
}
