package ash.aiqinhaigou.com.ashcny.index.model;

import java.util.List;

import ash.aiqinhaigou.com.ashcny.api.HttpMethods;
import ash.aiqinhaigou.com.ashcny.bean.MovieBean;
import ash.aiqinhaigou.com.ashcny.bean.SubjectsBean;
import ash.aiqinhaigou.com.ashcny.index.widget.HotFragment;
import ash.aiqinhaigou.com.ashcny.presenter.SubscriberOnNextListener;
import rx.Subscriber;

/**
 * Created by Aspros on 16/6/1.
 */
public class GoodsModelImpl implements GoodsModel {

    @Override
    public void loadGoods(String url, int type, int pageIndex, final OnLoadGoodsListListener listListener, final SubscriberOnNextListener subscriberOnNextListener) {
        switch (type) {
            case HotFragment.TAB_TAG_HOT:
                HttpMethods.getInstance().getGoodsBean(new Subscriber<List<SubjectsBean>>() {
                    @Override
                    public void onCompleted() {
                        listListener.onSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        listListener.onFailure(e.getMessage(), e);
                    }

                    @Override
                    public void onNext(List<SubjectsBean> subjects) {
                        if (subscriberOnNextListener != null) {
                            subscriberOnNextListener.onNext(subjects);
                        }
                    }
                }, pageIndex, 10);
                break;
            case HotFragment.TAB_TAG_NEW_GOODS:
                HttpMethods.getInstance().getInTheaters(new Subscriber<List<SubjectsBean>>() {
                    @Override
                    public void onCompleted() {
                        listListener.onSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        listListener.onFailure(e.getMessage(), e);
                    }

                    @Override
                    public void onNext(List<SubjectsBean> subjectsBeen) {
                        if (subscriberOnNextListener != null) {
                            subscriberOnNextListener.onNext(subjectsBeen);
                        }
                    }
                }, "杭州", pageIndex, 10);
                break;
            case HotFragment.TAB_TAG_PROGRESS:
                HttpMethods.getInstance().getComeSoon(new Subscriber<List<SubjectsBean>>() {
                    @Override
                    public void onCompleted() {
                        listListener.onSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        listListener.onFailure(e.getMessage(), e);
                    }

                    @Override
                    public void onNext(List<SubjectsBean> subjectsBeen) {
                        if (subscriberOnNextListener != null) {
                            subscriberOnNextListener.onNext(subjectsBeen);
                        }
                    }
                }, pageIndex, 10);
                break;
            case HotFragment.TAB_TAG_SUM_COUNT:
                HttpMethods.getInstance().getInTheaters(new Subscriber<List<SubjectsBean>>() {
                    @Override
                    public void onCompleted() {
                        listListener.onSuccess();
                    }

                    @Override
                    public void onError(Throwable e) {
                        listListener.onFailure(e.getMessage(), e);
                    }

                    @Override
                    public void onNext(List<SubjectsBean> subjectsBeen) {
                        if (subscriberOnNextListener != null) {
                            subscriberOnNextListener.onNext(subjectsBeen);
                        }
                    }
                }, "武汉", pageIndex, 10);
                break;
            default:
                break;
        }
    }

    @Override
    public void loadGoodsDetail(String id, final OnLoadGoodsListListener listListener, final SubscriberOnNextListener subscriberOnNextListener) {
        HttpMethods.getInstance().getMovieDetail(new Subscriber<MovieBean>() {
            @Override
            public void onCompleted() {
                listListener.onSuccess();
            }

            @Override
            public void onError(Throwable e) {
                listListener.onFailure(e.getMessage(), e);
            }

            @Override
            public void onNext(MovieBean movieBean) {
                if (subscriberOnNextListener != null) {
                    subscriberOnNextListener.onNext(movieBean);
                }
            }
        }, id);
    }

    public interface OnLoadGoodsListListener {
        void onSuccess();
        void onFailure(String msg, Throwable e);
    }
}
