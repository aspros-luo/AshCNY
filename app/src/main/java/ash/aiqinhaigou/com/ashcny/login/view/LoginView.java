package ash.aiqinhaigou.com.ashcny.login.view;

/**
 * Created by Aspros on 16/6/7.
 */
public interface LoginView {
    void login(String name,String password);

    void loginSuccess();

    void loginFail(int type);
}
