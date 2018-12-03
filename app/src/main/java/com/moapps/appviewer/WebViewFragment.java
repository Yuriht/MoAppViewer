package com.moapps.appviewer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;


public class WebViewFragment extends Fragment {

	private MainActivity mainActivity;
	private View this_view;

	private WebView web_view;
    private String url;


	public WebViewFragment() {

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mainActivity = (MainActivity) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this_view = inflater.inflate(R.layout.fragment_webview, container, false);

        web_view = this_view.findViewById(R.id.web_view);

        url = (String) getArguments().getSerializable("data");
        web_view.getSettings().setJavaScriptEnabled(true);
        web_view.loadUrl(url);

		return this_view;
	}

}