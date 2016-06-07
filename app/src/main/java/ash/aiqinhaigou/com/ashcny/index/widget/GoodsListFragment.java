package ash.aiqinhaigou.com.ashcny.index.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ash.aiqinhaigou.com.ashcny.R;
import ash.aiqinhaigou.com.ashcny.api.HttpMethods;
import ash.aiqinhaigou.com.ashcny.bean.SubjectsBean;
import ash.aiqinhaigou.com.ashcny.index.adapter.GoodsAdapter;
import ash.aiqinhaigou.com.ashcny.index.presenter.GoodsPresenter;
import ash.aiqinhaigou.com.ashcny.index.presenter.GoodsPresenterImpl;
import ash.aiqinhaigou.com.ashcny.index.view.GoodsView;
import ash.aiqinhaigou.com.ashcny.presenter.SubscriberOnNextListener;
import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;

/**
 * Created by Aspros on 16/5/31.
 */
public class GoodsListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, GoodsView {


    private int mType = HotFragment.TAB_TAG_HOT;
    private LinearLayoutManager mLinearLayoutManager;
    private StaggeredGridLayoutManager mStaggerGridLayout;
    private GoodsAdapter mGoodsAdapter;
    private List<SubjectsBean> mGoodsBeanList;
    private int pageIndex = 0;
    private RecyclerView recycleView;
    private SwipeRefreshLayout swipeRefreshWidget;
    private GridLayoutManager mGridLayoutManager;
    private GoodsPresenter mGoodsPresenter;
    private SubscriberOnNextListener subscriberOnNextListener;

    public static GoodsListFragment newInstance(int type) {
        Bundle bundle = new Bundle();
        GoodsListFragment goodsListFragment = new GoodsListFragment();
        bundle.putInt("type", type);
        goodsListFragment.setArguments(bundle);
        return goodsListFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mType = getArguments().getInt("type");
        mGoodsPresenter = new GoodsPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goods_list, null);

        recycleView = (RecyclerView) view.findViewById(R.id.recycle_view);
        swipeRefreshWidget = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_widget);


//        swipeRefreshWidget.setColorSchemeResources(R.color.primary,
//                R.color.primary_dark, R.color.primary_light,
//                R.color.accent);

        //setting fresh listener
        swipeRefreshWidget.setOnRefreshListener(this);

        recycleView.setHasFixedSize(true);

        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mStaggerGridLayout = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mGridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recycleView.setLayoutManager(mGridLayoutManager);
        recycleView.setItemAnimator(new DefaultItemAnimator());

        mGoodsAdapter = new GoodsAdapter(getActivity());
        mGoodsAdapter.setmOnItemClickListener(onItemClickListener);
        mGoodsAdapter.setmOnBtnClickListener(onBtnClickListener);

        subscriberOnNextListener = new SubscriberOnNextListener<List<SubjectsBean>>() {
            @Override
            public void onNext(List<SubjectsBean> o) {
                addData(o);
                recycleView.setAdapter(mGoodsAdapter);
            }
        };

        recycleView.addOnScrollListener(mOnScrollListener);
        onRefresh();
        return view;
    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        private int lastVisiblePositionItem;

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisiblePositionItem >= mGoodsAdapter.getItemCount() - 1 && mGoodsAdapter.isShowFooter()) {
                subscriberOnNextListener = new SubscriberOnNextListener<List<SubjectsBean>>() {
                    @Override
                    public void onNext(List<SubjectsBean> o) {
                        addData(o);
                    }
                };
                mGoodsPresenter.loadGoods(mType, pageIndex, subscriberOnNextListener);
                pageIndex = pageIndex + 10;
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
//            lastVisiblePositionItem = mLinearLayoutManager.findLastVisibleItemPosition();
            lastVisiblePositionItem = mGridLayoutManager.findLastVisibleItemPosition();
//            Log.e("Last_Position", "" + lastVisiblePositionItem);
//            int[] lastPositions = mStaggerGridLayout.findLastVisibleItemPositions(new int[mStaggerGridLayout.getSpanCount()]);
//            lastVisiblePositionItem = getMaxPosition(lastPositions);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {
//        Log.e("fresh", "Fresh");
        if (mGoodsBeanList != null) {
            pageIndex = 0;
            mGoodsBeanList = new ArrayList<SubjectsBean>();
        }
        mGoodsPresenter.loadGoods(mType, pageIndex, subscriberOnNextListener);

        pageIndex = pageIndex + 10;
    }

    @Override
    public void showProgress() {
        swipeRefreshWidget.setRefreshing(true);
    }

    @Override
    public void addData(List<SubjectsBean> goodsBeanList) {
        mGoodsAdapter.isShowFooter(true);

        if (mGoodsBeanList == null) {
            mGoodsBeanList = new ArrayList<SubjectsBean>();
        }
//        mGoodsBeanList.addAll(goodsBeanList);
//        if (pageIndex == 0) {
//            mGoodsAdapter.clearData();
//        }
//        else {
//            if (goodsBeanList == null || goodsBeanList.size() == 0) {
//                mGoodsAdapter.isShowFooter(false);
//            }
//            mGoodsAdapter.notifyDataSetChanged();
//        }
//        pageIndex += 10;
        mGoodsBeanList.addAll(goodsBeanList);
        mGoodsAdapter.setmData(mGoodsBeanList);
        mGoodsAdapter.notifyDataSetChanged();

    }

    @Override
    public void hideProgress() {
        swipeRefreshWidget.setRefreshing(false);
    }

    @Override
    public void showFailMsg() {
        Toast.makeText(getActivity(), "something error", Toast.LENGTH_SHORT).show();
    }

    /**
     * 获得最大的位置
     *
     * @param positions
     * @return
     */
    private int getMaxPosition(int[] positions) {
        int size = positions.length;
        int maxPosition = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            maxPosition = Math.max(maxPosition, positions[i]);
        }
        return maxPosition;
    }

    private GoodsAdapter.OnItemClickListener onItemClickListener = new GoodsAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {

            SubjectsBean subjectsBean = mGoodsAdapter.getItem(position);
            Intent intent = new Intent(getActivity(), GoodsDetailActivity.class);
            intent.putExtra("goodsId",subjectsBean.getId());

            startActivity(intent);
        }
    };

    private GoodsAdapter.OnBtnClickListener onBtnClickListener = new GoodsAdapter.OnBtnClickListener() {
        @Override
        public void onBtnClick(View view, int position) {
            Toast.makeText(getActivity(), mGoodsAdapter.getItem(position).getOriginal_title(), Toast.LENGTH_SHORT).show();
        }
    };
}
