package com.moapps.appviewer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.moapps.appviewer.API.pojo.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment {

	private MainActivity mainActivity;
	private View this_view;

	private EditText edit_login;
	private EditText edit_password;
	private Button button_login;

	public LoginFragment() {

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		mainActivity = (MainActivity) activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this_view = inflater.inflate(R.layout.fragment_login, container, false);

		edit_login = this_view.findViewById(R.id.edit_login);
		edit_password = this_view.findViewById(R.id.edit_password);
		button_login = this_view.findViewById(R.id.button_login);

		button_login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				String login = edit_login.getText().toString().trim();
				String password = edit_password.getText().toString().trim();

				User user = new User(login, password);
				Call<User> call1 = mainActivity.apiInterface.createUser(user);
				call1.enqueue(new Callback<User>() {
					@Override
					public void onResponse(Call<User> call, Response<User> response) {
						User user1 = response.body();
						if (user1 == null) {
                            Toast.makeText(mainActivity, "Ошибка подключения (код "+ response.code() + " " + response.message() + ")", Toast.LENGTH_LONG).show();
                        } else {
						    if (user1.data == null || user1.err == true || user1.code != 200){
                                Toast.makeText(mainActivity, "Неверно введены логин/пароль (код "+ user1.code +")", Toast.LENGTH_LONG).show();
                            } else {
                                mainActivity.current_user = user1;
                                mainActivity.ShowAppListFragment();
                            }
                        }
					}

					@Override
					public void onFailure(Call<User> call, Throwable t) {
                        Toast.makeText(mainActivity, "Ошибка подключения (" + t.getMessage() + ")", Toast.LENGTH_LONG).show();
						call.cancel();
					}
				});
			}
		});

		return this_view;
	}

}