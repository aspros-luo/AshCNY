package ash.aiqinhaigou.com.ashcny.api;


import java.util.List;

import ash.aiqinhaigou.com.ashcny.bean.MovieBean;
import ash.aiqinhaigou.com.ashcny.bean.SubjectsBean;
import ash.aiqinhaigou.com.ashcny.model.HttpResult;
import ash.aiqinhaigou.com.ashcny.model.Subject;
import retrofit2.http.GET;
import retrofit2.http.Path;
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

    /**
     * 用于查询Top250 使用@Query 拼接参数
     * @param start 起始位置
     * @param count 长度
     * @return
     */
    @GET("top250")
    Observable<HttpResult<List<SubjectsBean>>> getGoodsBean(@Query("start") int start, @Query("count") int count);

    @GET("in_theaters")
    Observable<HttpResult<List<SubjectsBean>>> getInTheaters(@Query("city") String city,@Query("start") int start,@Query("count") int count);

    @GET("coming_soon")
    Observable<HttpResult<List<SubjectsBean>>> getComeSoon(@Query("start") int start,@Query("count") int count);

    /**
     * 通过id查询电影详情，使用@Path 替代｛id｝的参数
     * @param movieId 电影Id
     * @return
     */
    @GET("subject/{id}")
    Observable<MovieBean> getMovieDetail(@Path("id") String movieId);
}
