package com.moapps.appviewer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.moapps.appviewer.Adapters.AppsAdapter;
import com.moapps.appviewer.API.pojo.AppList;
import com.moapps.appviewer.Classes.RecyclerClickListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AppListFragment extends Fragment {

	private MainActivity mainActivity;
	private View this_view;

    private RecyclerView recyclerView;
    private AppsAdapter appsAdapter;
    private List<AppList.AppData> app_data_list = new ArrayList<>();

	public AppListFragment() {

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mainActivity = (MainActivity) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this_view = inflater.inflate(R.layout.fragment_app_list, container, false);

        //Init List
        recyclerView = this_view.findViewById(R.id.recycler_view);
        appsAdapter = new AppsAdapter(this_view.getContext(), app_data_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mainActivity);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(appsAdapter);
        ListItemClickListener();

        //Init Retrofit
        AppList app_list_request = new AppList(0, 1000, 0, mainActivity.current_user.data);
        Call<AppList> call1 = mainActivity.apiInterface.createAppList(app_list_request);
        call1.enqueue(new Callback<AppList>() {
            @Override
            public void onResponse(Call<AppList> call, Response<AppList> response) {
                AppList app_list_serponce = response.body();
                if (app_list_serponce == null) {
                    Toast.makeText(mainActivity, "Ошибка подключения (код "+ response.code() + " " + response.message() + ")", Toast.LENGTH_LONG).show();
                } else {
                    app_data_list.clear();
                    app_data_list.addAll(app_list_serponce.data);
                    UpdateList();
                }
            }

            @Override
            public void onFailure(Call<AppList> call, Throwable t) {
                Toast.makeText(mainActivity, "Ошибка подключения (" + t.getMessage() + ")", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
		return this_view;
	}


    //Update List
    public void UpdateList() {
        recyclerView.scrollToPosition(0);
        appsAdapter.notifyDataSetChanged();
    }



    //Recycler items click
    private void ListItemClickListener() {
        recyclerView.addOnItemTouchListener(new RecyclerClickListener(mainActivity, recyclerView, new RecyclerClickListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                final int pos = position;
                mainActivity.ShowWebViewFragment(app_data_list.get(pos).getApplicationUrl());
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

}