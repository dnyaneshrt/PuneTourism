package com.infeanet.punetourism.ui.malls;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.infeanet.punetourism.R;

public class MallsFragment extends Fragment {

    WebView webView;
    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_malls, container, false);


        webView=view.findViewById(R.id.webview_malls);
        progressBar=view.findViewById(R.id.progress_bar_malls);

//1. loadUrl is method to load webpages intoyour application.
        webView.loadUrl("https://www.tripadvisor.in/Attractions-g297654-Activities-c26-t143-Pune_Pune_District_Maharashtra.html");

//
        webView.setWebViewClient(new WebViewClient()
                                 {
                                     @Override
                                     public void onPageStarted(WebView view, String url, Bitmap
                                             favicon) {
                                         super.onPageStarted(view, url, favicon);
                                         progressBar.setVisibility(View.VISIBLE);
                                     }

                                     @Override
                                     public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest
                                             request) {
//                                         Toast.makeText(requireContext(), request, Toast.LENGTH_SHORT).show();
                                         return super.shouldOverrideUrlLoading(view, request);
                                     }

                                     @Override
                                     public void onPageFinished(WebView view, String url) {
                                         super.onPageFinished(view, url);
                                         progressBar.setVisibility(View.GONE);
                                     }
                                 }
        );

        //to enable zoom controls
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);

        return view;
    }
}
