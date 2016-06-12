package ash.aiqinhaigou.com.ashcny;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ash.aiqinhaigou.com.ashcny.index.widget.MainActivity;
import ash.aiqinhaigou.com.ashcny.login.widget.LoginSystem;

public class Welcome extends AppCompatActivity {

    private static final int GUIDE_LOGIN = 0;
    private static final int GUIDE_MAIN = 1;
    private static final int DISPLAY_TIME = 3000;
    private Boolean isLogin = false;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case GUIDE_LOGIN:
                    go_Login();
                    break;
                case GUIDE_MAIN:
                    go_Main();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initView();
    }

    private void initView() {
        SharedPreferences s = getSharedPreferences("loginInfo", MODE_MULTI_PROCESS);
        isLogin = s.getBoolean("isLogin", false);
        if (isLogin) {
            handler.sendEmptyMessageDelayed(GUIDE_MAIN, DISPLAY_TIME);
        } else {
            handler.sendEmptyMessageDelayed(GUIDE_LOGIN, DISPLAY_TIME);
        }
    }

    private void go_Main() {
//        Intent i = new Intent(Welcome.this, MainActivity.class);
        Intent i = new Intent(Welcome.this, LoginSystem.class);

        startActivity(i);
        finish();
    }

    private void go_Login() {
        Intent i = new Intent(Welcome.this, LoginSystem.class);
        startActivity(i);
        finish();
    }
}
