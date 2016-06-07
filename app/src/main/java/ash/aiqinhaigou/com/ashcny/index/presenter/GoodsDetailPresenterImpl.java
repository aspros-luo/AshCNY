package ash.aiqinhaigou.com.ashcny.index.presenter;

import ash.aiqinhaigou.com.ashcny.index.model.GoodsModel;
import ash.aiqinhaigou.com.ashcny.index.model.GoodsModelImpl;
import ash.aiqinhaigou.com.ashcny.presenter.SubscriberOnNextListener;

/**
 * Created by Aspros on 16/6/7.
 */
public class GoodsDetailPresenterImpl implements GoodsDetailPresenter,GoodsModelImpl.OnLoadGoodsListListener {

    private GoodsModel mGoodsModel;

    public GoodsDetailPresenterImpl() {
        mGoodsModel=new GoodsModelImpl();
    }

    @Override
    public void loadGoodsDetail(String id, SubscriberOnNextListener subscriberOnNextListener) {
        mGoodsModel.loadGoodsDetail(id,this,subscriberOnNextListener);
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFailure(String msg, Throwable e) {

    }
}
