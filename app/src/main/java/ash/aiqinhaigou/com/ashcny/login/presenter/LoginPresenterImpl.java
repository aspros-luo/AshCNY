package ash.aiqinhaigou.com.ashcny.login.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import ash.aiqinhaigou.com.ashcny.login.model.LoginModel;
import ash.aiqinhaigou.com.ashcny.login.model.LoginModelImpl;
import ash.aiqinhaigou.com.ashcny.login.view.LoginView;

/**
 * Created by Aspros on 16/6/7.
 */
public class LoginPresenterImpl implements LoginPresenter, LoginModelImpl.OnLoginListener {
    private LoginView mLoginView;
    private LoginModel mLoginModel;
    private Context mContext;

    private SharedPreferences mSharedPreferences;

    public LoginPresenterImpl(LoginView loginView, Context context) {
        mLoginView = loginView;
        mContext = context;
        mLoginModel = new LoginModelImpl();
    }

    @Override
    public void loginSys(String name, String password) {
        mLoginModel.loginSys(name, password, this);
    }

    @Override
    public void successLogin() {
        mSharedPreferences = mContext.getSharedPreferences("loginInfo", Context.MODE_MULTI_PROCESS);

        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean("isLogin", true);
        editor.commit();

        mLoginView.loginSuccess();
    }

    @Override
    public void failLogin() {
        mLoginView.loginFail(isConnectState());
    }

    private int isConnectState() {
        ConnectivityManager manager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if (info == null || !manager.getBackgroundDataSetting()) {
            return 2;
        }
        return 1;
    }
}
