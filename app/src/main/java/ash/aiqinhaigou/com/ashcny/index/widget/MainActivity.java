package ash.aiqinhaigou.com.ashcny.index.widget;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import ash.aiqinhaigou.com.ashcny.R;
import ash.aiqinhaigou.com.ashcny.shoppingcar.widget.CarFragment;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {


    @Bind(R.id.main_content)
    FrameLayout mainContent;
    @Bind(R.id.id_tab_bottom_index)
    LinearLayout idTabBottomIndex;
    @Bind(R.id.id_tab_bottom_friend)
    LinearLayout idTabBottomFriend;
    @Bind(R.id.id_tab_bottom_contact)
    LinearLayout idTabBottomContact;
    @Bind(R.id.id_tab_bottom_setting)
    LinearLayout idTabBottomSetting;
    @Bind(R.id.toolbar)
    Toolbar toolbar;


    private FragmentManager mFragmentManager;
    private HotFragment mHotFragment;
    private CarFragment mCarFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        toolbar.setTitle("首页");

        setSupportActionBar(toolbar);

        mFragmentManager = getSupportFragmentManager();

        initView();
    }

    private void initView() {
        setSelection(0);
    }


    private void setSelection(int index) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        hideFragments(transaction, mHotFragment);
        hideFragments(transaction, mCarFragment);
        switch (index) {
            case 0:
                if (mHotFragment == null) {
                    mHotFragment = new HotFragment();
                    transaction.add(R.id.main_content, mHotFragment);
                } else {
                    transaction.show(mHotFragment);
                }
                break;
            case 1:
                break;
            case 2:
                if (mCarFragment == null) {
                    mCarFragment = new CarFragment();
                    transaction.add(R.id.main_content, mCarFragment);
                } else {
                    transaction.show(mCarFragment);
                }
                break;
            case 3:
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction, Fragment fragment) {
        if (fragment != null) {
            transaction.hide(fragment);
        }
    }

    @OnClick({R.id.id_tab_bottom_index, R.id.id_tab_bottom_friend, R.id.id_tab_bottom_contact, R.id.id_tab_bottom_setting})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_tab_bottom_index:
                setSelection(0);
                toolbar.setTitle("首页");
                break;
            case R.id.id_tab_bottom_friend:
                setSelection(1);
                break;
            case R.id.id_tab_bottom_contact:
                setSelection(2);
                toolbar.setTitle("清单");
                break;
            case R.id.id_tab_bottom_setting:
                setSelection(3);
                break;
        }
    }
}
