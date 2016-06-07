package ash.aiqinhaigou.com.ashcny.index.presenter;

import ash.aiqinhaigou.com.ashcny.presenter.SubscriberOnNextListener;

/**
 * Created by Aspros on 16/6/7.
 */
public interface GoodsDetailPresenter  {
    void loadGoodsDetail(String id, SubscriberOnNextListener subscriberOnNextListener);
}
