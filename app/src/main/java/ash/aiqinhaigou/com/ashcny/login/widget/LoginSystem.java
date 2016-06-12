package ash.aiqinhaigou.com.ashcny.login.widget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import ash.aiqinhaigou.com.ashcny.R;
import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginSystem extends AppCompatActivity {

    @Bind(R.id.login_fragment)
    FrameLayout loginFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_system);
        ButterKnife.bind(this);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.login_fragment, new LoginFragment())
                .commit();
    }


}
