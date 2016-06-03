package ash.aiqinhaigou.com.ashcny.index.model;

import java.util.List;

import ash.aiqinhaigou.com.ashcny.model.Subject;
import ash.aiqinhaigou.com.ashcny.presenter.SubscriberOnNextListener;
import rx.Subscriber;

/**
 * Created by Aspros on 16/5/31.
 */
public interface GoodsModel {
    void loadGoods(String url, int type, int pageIndex, GoodsModelImpl.OnLoadGoodsListListener listListener,SubscriberOnNextListener subscriberOnNextListener);
}
