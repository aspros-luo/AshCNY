package ash.aiqinhaigou.com.ashcny.login.model;

/**
 * Created by Aspros on 16/6/7.
 */
public interface LoginModel {
    void loginSys(String name , String password,LoginModelImpl.OnLoginListener loginListener);
}
