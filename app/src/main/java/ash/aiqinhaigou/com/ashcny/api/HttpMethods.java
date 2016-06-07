package ash.aiqinhaigou.com.ashcny.api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import ash.aiqinhaigou.com.ashcny.bean.MovieBean;
import ash.aiqinhaigou.com.ashcny.bean.SubjectsBean;
import ash.aiqinhaigou.com.ashcny.model.HttpResult;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class HttpMethods {

    public static final String BASE_URL = "https://api.douban.com/v2/movie/";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private MovieService movieService;

    //构造方法私有
    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        movieService = retrofit.create(MovieService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 用于获取豆瓣电影Top250的数据
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param start      起始位置
     * @param count      获取长度
     */
//    public void getTopMovie(Subscriber<List<Subject>> subscriber, int start, int count) {
//
////        movieService.getTopMovie(start, count)
////                .map(new HttpResultFunc<List<Subject>>())
////                .subscribeOn(Schedulers.io())
////                .unsubscribeOn(Schedulers.io())
////                .observeOn(AndroidSchedulers.mainThread())
////                .subscribe(subscriber);
//
//        Observable observable = movieService.getTopMovie(start, count)
//                .map(new HttpResultFunc<List<Subject>>());
//
//        toSubscribe(observable, subscriber);
//    }

    public void getGoodsBean(Subscriber<List<SubjectsBean>> subscriber, int start, int count) {

        Observable observable = movieService.getGoodsBean(start, count)
                .map(new HttpResultFunc<List<SubjectsBean>>());


        toSubscribe(observable, subscriber);
    }

    /**
     * 获取豆瓣最新地区的电影
     *
     * @param subscriber 观察者对象
     * @param city       参数：城市（可用id或者中文）
     * @param start      参数：起始位置
     * @param count      参数：长度
     */
    public void getInTheaters(Subscriber<List<SubjectsBean>> subscriber, String city, int start, int count) {
        Observable observable = movieService.getInTheaters(city, start, count)
                .map(new HttpResultFunc<List<SubjectsBean>>());

        toSubscribe(observable, subscriber);
    }

    /**
     * 用于获取即将上映电影的信息
     *
     * @param subscriber 观察这者对象
     * @param start      参数：起始位置
     * @param count      参数：长度
     */
    public void getComeSoon(Subscriber<List<SubjectsBean>> subscriber, int start, int count) {
        Observable observable = movieService.getComeSoon(start, count)
                .map(new HttpResultFunc<List<SubjectsBean>>());


        toSubscribe(observable, subscriber);
    }

    /**
     * 用于获取电影详细信息
     *
     * @param subscriber 观察者对象
     * @param id         参数：电影Id
     */
    public void getMovieDetail(Subscriber<MovieBean> subscriber, String id) {
        Observable<MovieBean> observable = movieService.getMovieDetail(id)
                .map(new Func1<MovieBean, MovieBean>() {
                    @Override
                    public MovieBean call(MovieBean movieBean) {
                        return movieBean;
                    }
                });
        toSubscribe(observable, subscriber);
    }

    /**
     * 用于订阅事件
     *
     * @param o   被观察者对象
     * @param s   观察者对象
     * @param <T>
     */
    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Subject部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.getCount() == 0) {
                throw new ApiException(100);
            }
            return httpResult.getSubjects();
        }
    }
}
