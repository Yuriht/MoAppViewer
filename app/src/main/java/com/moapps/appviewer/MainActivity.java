package com.moapps.appviewer;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.moapps.appviewer.API.APIClient;
import com.moapps.appviewer.API.APIInterface;
import com.moapps.appviewer.API.pojo.User;


public class MainActivity extends AppCompatActivity {

    private MainActivity main_activity;
    private LoginFragment loginFragment;
    private AppListFragment app_list_Fragment;
    private WebViewFragment webviewFragment;
    private Fragment current_fragment;

    public APIInterface apiInterface;
    public User current_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_activity = this;

        apiInterface = APIClient.getClient().create(APIInterface.class);

        //SHOW FIRST FRAGMENT
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }
            ShowLoginFragment();
        }
    }

    public void ShowLoginFragment() {
        loginFragment = new LoginFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, loginFragment, "LOGIN").commit();
        current_fragment = loginFragment;
    }

    public void ShowAppListFragment() {
        app_list_Fragment = new AppListFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, app_list_Fragment, "APPLIST").commit();
        current_fragment = app_list_Fragment;
    }

    public void ShowWebViewFragment(String applicationUrl) {
        webviewFragment = new WebViewFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", applicationUrl);
        webviewFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, webviewFragment, "WEBVIEW").commit();
        current_fragment = webviewFragment;
    }

    @Override
    public void onBackPressed()
    {
        if (current_fragment.getTag() == "WEBVIEW") {
            getSupportFragmentManager().beginTransaction().remove(current_fragment).commit();
        } else {
            finish();
        }
    }
}
