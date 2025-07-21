package com.infeanet.punetourism.ui.home;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.infeanet.punetourism.R;

public class HomeFragment extends Fragment  implements BackPressedListener{

    WebView webView;
    ProgressBar progressBar;

   public static BackPressedListener backPressedListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_about_pune, container, false);
        webView=view.findViewById(R.id.webview_about_pune);
        progressBar=view.findViewById(R.id.progress_bar);

        //1. loadUrl is method to load webpages intoyour application.
        webView.loadUrl("https://en.wikipedia.org/wiki/Pune");

        //
        webView.setWebViewClient(new WebViewClient()
                                 {
                                     @Override
                                     public void onPageStarted(WebView view, String url, Bitmap favicon) {
                                         super.onPageStarted(view, url, favicon);
                                         progressBar.setVisibility(View.VISIBLE);
                                     }

                                     @Override
                                     public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
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
    @Override
    public void onPause() {
        backPressedListener=null;
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        backPressedListener=this;
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack())
        {
            webView.goBack();
        }else {
            AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
            builder.setTitle("Exit!");
            builder.setIcon(R.drawable.baseline_home_24);
            builder.setMessage("Do you want to exit App?");

            builder.setPositiveButton("Yes", (dialog, which)-> {
                        getActivity().finish();
                    }
            );
            builder.setNegativeButton("No", (dialog, which)-> {
                        dialog.dismiss();
                    }
            );

            builder.setNeutralButton("Cancel", (dialog, which)-> {
                        dialog.dismiss();
                    }
            );
            builder.show();
        }
    }
}
