package ash.aiqinhaigou.com.ashcny.index.widget;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ash.aiqinhaigou.com.ashcny.R;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Aspros on 16/5/26.
 */
public class HotFragment extends Fragment {


    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.viewpager)
    ViewPager viewpager;

    private View view;
    public final static int TAB_TAG_HOT=0;
    public final static int TAB_TAG_NEW_GOODS=1;
    public final static int TAB_TAG_PROGRESS=2;
    public final static int TAB_TAG_SUM_COUNT=3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_goods, container, false);
        ButterKnife.bind(this, view);

        viewpager.setOffscreenPageLimit(3);
        setupViewPager(viewpager);

        tabLayout.addTab(tabLayout.newTab().setText(R.string.hot));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.newGoods));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.progress));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.sumCount));

        tabLayout.setupWithViewPager(viewpager);

        return view;
    }

    private void setupViewPager(ViewPager viewpager) {
        MyPagerAdapter myPagerAdapter=new MyPagerAdapter(getChildFragmentManager());
        myPagerAdapter.addFragment(GoodsListFragment.newInstance(TAB_TAG_HOT),getString(R.string.hot));
        myPagerAdapter.addFragment(GoodsListFragment.newInstance(TAB_TAG_NEW_GOODS),getString(R.string.newGoods));
        myPagerAdapter.addFragment(GoodsListFragment.newInstance(TAB_TAG_PROGRESS),getString(R.string.progress));
        myPagerAdapter.addFragment(GoodsListFragment.newInstance(TAB_TAG_SUM_COUNT),getString(R.string.sumCount));
        viewpager.setAdapter(myPagerAdapter);
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter{
        private final List<Fragment> mFragmentList=new ArrayList<>();
        private final List<String> mFragmentTitle=new ArrayList<>();

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment,String title)
        {
            mFragmentList.add(fragment);
            mFragmentTitle.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            return mFragmentTitle.get(position);
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
