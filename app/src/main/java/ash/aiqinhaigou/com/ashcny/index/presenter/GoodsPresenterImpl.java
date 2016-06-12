package ash.aiqinhaigou.com.ashcny.index.presenter;

import android.util.Log;

import java.util.List;

import ash.aiqinhaigou.com.ashcny.index.model.GoodsModel;
import ash.aiqinhaigou.com.ashcny.index.model.GoodsModelImpl;
import ash.aiqinhaigou.com.ashcny.index.view.GoodsView;
import ash.aiqinhaigou.com.ashcny.presenter.SubscriberOnNextListener;
import rx.Subscriber;

/**
 * Created by Aspros on 16/6/1.
 */
public class GoodsPresenterImpl implements GoodsPresenter, GoodsModelImpl.OnLoadGoodsListListener {

    private GoodsView goodsView;
    private GoodsModel goodsModel;

    public GoodsPresenterImpl(GoodsView goodsView) {
        this.goodsView = goodsView;
        this.goodsModel = new GoodsModelImpl();
    }

    @Override
    public void loadGoods(int type, int pageIndex, SubscriberOnNextListener subscriberOnNextListener) {
        String url = "";
//        if (pageIndex == 0) {
//            goodsView.showProgress();
//        }
        goodsModel.loadGoods(url, type, pageIndex, this, subscriberOnNextListener);
    }

    @Override
    public void onSuccess() {
        goodsView.hideProgress();
    }

    @Override
    public void onFailure(String msg, Throwable e) {
        goodsView.hideProgress();
        goodsView.showFailMsg(msg,e);
    }
}
