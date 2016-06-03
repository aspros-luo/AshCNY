package ash.aiqinhaigou.com.ashcny.index.model;

import java.util.List;

import ash.aiqinhaigou.com.ashcny.api.HttpMethods;
import ash.aiqinhaigou.com.ashcny.model.Subject;
import ash.aiqinhaigou.com.ashcny.presenter.SubscriberOnNextListener;
import rx.Subscriber;

/**
 * Created by Aspros on 16/6/1.
 */
public class GoodsModelImpl implements GoodsModel {


    @Override
    public void loadGoods(String url, int type, int pageIndex, final OnLoadGoodsListListener listListener, final SubscriberOnNextListener subscriberOnNextListener) {
        HttpMethods.getInstance().getTopMovie(new Subscriber<List<Subject>>() {
            @Override
            public void onCompleted() {
                listListener.onSuccess();
            }

            @Override
            public void onError(Throwable e) {
                listListener.onFailure(e.getMessage(), e);
            }

            @Override
            public void onNext(List<Subject> subjects) {
                if (subscriberOnNextListener != null) {
                    subscriberOnNextListener.onNext(subjects);
                }
            }
        }, pageIndex, 10);
    }

    public interface OnLoadGoodsListListener {
        void onSuccess();
        void onFailure(String msg, Throwable e);
    }
}
