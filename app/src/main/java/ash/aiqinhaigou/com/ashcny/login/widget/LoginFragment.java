package ash.aiqinhaigou.com.ashcny.login.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ash.aiqinhaigou.com.ashcny.R;
import ash.aiqinhaigou.com.ashcny.index.widget.MainActivity;
import ash.aiqinhaigou.com.ashcny.login.presenter.LoginPresenter;
import ash.aiqinhaigou.com.ashcny.login.presenter.LoginPresenterImpl;
import ash.aiqinhaigou.com.ashcny.login.view.LoginView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Aspros on 16/6/7.
 */
public class LoginFragment extends Fragment implements LoginView {

    @Bind(R.id.login_name)
    EditText loginName;
    @Bind(R.id.login_password)
    EditText loginPassword;
    @Bind(R.id.login_btn)
    Button loginBtn;
    private View view;


    private LoginPresenter mLoginPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLoginPresenter = new LoginPresenterImpl(this, getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.framgent_login, container, false);
        ButterKnife.bind(this, view);

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.login_btn)
    public void onClick() {
        login(loginName.getText().toString(), loginPassword.getText().toString());
    }

    @Override
    public void login(String name, String password) {
        mLoginPresenter.loginSys(name, password);
    }

    @Override
    public void loginSuccess() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void loginFail(int type) {
        switch (type) {
            case 1:
                Toast.makeText(getContext(), "Login Fail! name or password is error", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(getContext(), "Login Fail! you need to check your internet", Toast.LENGTH_SHORT).show();
                break;
            case 3:
                Toast.makeText(getContext(), "Login Fail! something is error", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }
}
