package ash.aiqinhaigou.com.ashcny.api;


import java.util.List;

import ash.aiqinhaigou.com.ashcny.bean.GoodsBean;
import ash.aiqinhaigou.com.ashcny.model.HttpResult;
import ash.aiqinhaigou.com.ashcny.model.Subject;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by liukun on 16/3/9.
 */
public interface MovieService {

//    @GET("top250")
//    Call<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);

//    @GET("top250")
//    Observable<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);

//    @GET("top250")
//    Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);

    @GET("top250")
    Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);

    @GET("top250")
    Observable<HttpResult<List<GoodsBean>>> getGoodsBean(@Query("start") int start, @Query("count") int count);
}
