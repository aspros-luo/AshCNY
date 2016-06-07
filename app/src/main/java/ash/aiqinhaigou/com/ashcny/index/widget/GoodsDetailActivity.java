package ash.aiqinhaigou.com.ashcny.index.widget;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import ash.aiqinhaigou.com.ashcny.R;
import ash.aiqinhaigou.com.ashcny.api.HttpMethods;
import ash.aiqinhaigou.com.ashcny.bean.MovieBean;
import ash.aiqinhaigou.com.ashcny.index.presenter.GoodsDetailPresenter;
import ash.aiqinhaigou.com.ashcny.index.presenter.GoodsDetailPresenterImpl;
import ash.aiqinhaigou.com.ashcny.index.presenter.GoodsPresenter;
import ash.aiqinhaigou.com.ashcny.index.presenter.GoodsPresenterImpl;
import ash.aiqinhaigou.com.ashcny.presenter.SubscriberOnNextListener;
import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;

public class GoodsDetailActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.goods_detail_img)
    ImageView goodsDetailImg;
    @Bind(R.id.goods_detail_title)
    TextView goodsDetailTitle;
    @Bind(R.id.goods_detail_summary)
    TextView goodsDetailSummary;
    @Bind(R.id.goods_detail_fragment)
    FrameLayout goodsDetailFragment;
    @Bind(R.id.goods_detail_menu)
    ListView goodsDetailMenu;

    private String mGoodsId = "";

    private SubscriberOnNextListener mSubscriberOnNextListener;
    private GoodsDetailPresenter mGoodsDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_detail);
        ButterKnife.bind(this);

        mGoodsId = getIntent().getStringExtra("goodsId");

        toolbar.setTitle("详情");

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        final DisplayImageOptions options = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.)
//                .showImageOnFail(R.drawable.ic_error)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        mSubscriberOnNextListener = new SubscriberOnNextListener<MovieBean>() {
            @Override
            public void onNext(MovieBean mMovieBean) {
                ImageLoader.getInstance().displayImage(mMovieBean.getImages().getLarge(), goodsDetailImg, options);
                goodsDetailTitle.setText(mMovieBean.getTitle() + "|" + mMovieBean.getOriginal_title());
                goodsDetailSummary.setText(mMovieBean.getSummary());
            }
        };
        mGoodsDetailPresenter = new GoodsDetailPresenterImpl();
        mGoodsDetailPresenter.loadGoodsDetail(mGoodsId,mSubscriberOnNextListener);
    }
}
