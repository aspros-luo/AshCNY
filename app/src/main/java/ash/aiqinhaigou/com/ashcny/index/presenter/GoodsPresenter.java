package ash.aiqinhaigou.com.ashcny.index.presenter;

import ash.aiqinhaigou.com.ashcny.presenter.SubscriberOnNextListener;

/**
 * Created by Aspros on 16/6/1.
 */
public interface GoodsPresenter {
    void loadGoods(int type, int pageIndex, SubscriberOnNextListener subscriberOnNextListener);
}
