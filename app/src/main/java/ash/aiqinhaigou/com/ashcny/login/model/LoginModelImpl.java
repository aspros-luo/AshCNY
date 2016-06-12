package ash.aiqinhaigou.com.ashcny.login.model;


/**
 * Created by Aspros on 16/6/7.
 */
public class LoginModelImpl implements LoginModel {


    @Override
    public void loginSys(String name, String password, OnLoginListener loginListener) {

        if (name.equals("user") && password.equals("123")) {
            loginListener.successLogin();
        } else {
            loginListener.failLogin();
        }
    }

    public interface OnLoginListener {
        void successLogin();

        void failLogin();
    }


}
