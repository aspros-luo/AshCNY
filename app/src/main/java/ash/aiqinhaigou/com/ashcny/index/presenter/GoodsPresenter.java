package ash.aiqinhaigou.com.ashcny.index.presenter;

import java.util.List;

import ash.aiqinhaigou.com.ashcny.model.Subject;
import ash.aiqinhaigou.com.ashcny.presenter.SubscriberOnNextListener;
import rx.Subscriber;

/**
 * Created by Aspros on 16/6/1.
 */
public interface GoodsPresenter {
    void loadGoods(int type, int pageIndex, SubscriberOnNextListener subscriberOnNextListener);
}
