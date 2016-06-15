package ash.aiqinhaigou.com.ashcny.shoppingcar.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import ash.aiqinhaigou.com.ashcny.R;
import ash.aiqinhaigou.com.ashcny.bean.SubjectsBean;
import ash.aiqinhaigou.com.ashcny.index.widget.GoodsDetailActivity;
import ash.aiqinhaigou.com.ashcny.shoppingcar.adapter.ShoppingAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Aspros on 16/6/13.
 */
public class CarFragment extends Fragment {


    @Bind(R.id.shopping_pay_btn)
    Button shoppingPayBtn;
    @Bind(R.id.shopping_fragment_recycle)
    RecyclerView shoppingFragmentRecycle;

    private View view;
    private LinearLayoutManager mLinearLayoutManager;

    private static List<SubjectsBean> mSubjectsBeen = new ArrayList<>();
    private static ShoppingAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shopping_car, container, false);
        ButterKnife.bind(this, view);

        shoppingFragmentRecycle.setHasFixedSize(true);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        shoppingFragmentRecycle.setLayoutManager(mLinearLayoutManager);
        shoppingFragmentRecycle.setItemAnimator(new DefaultItemAnimator());

        adapter = new ShoppingAdapter(getContext());
        adapter.setBeenData(mSubjectsBeen);
        adapter.setOnItemClickListener(mOnItemClickListener);
        shoppingFragmentRecycle.setAdapter(adapter);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public static void addSubjectBeen(SubjectsBean subjectsBean) {

        mSubjectsBeen.add(subjectsBean);
        if (adapter != null) {
            adapter.setBeenData(mSubjectsBeen);
        }

    }

    private ShoppingAdapter.OnItemClickListener mOnItemClickListener = new ShoppingAdapter.OnItemClickListener() {
        @Override
        public void OnItemClick(View view, int position) {
            SubjectsBean subjectsBean = adapter.getItem(position);
            Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
            intent.putExtra("goodsId", subjectsBean.getId());
            startActivity(intent);
        }

        @Override
        public void OnItemDelete(View view, int position) {
            adapter.removeBeenData(position);
        }
    };

}
