package lqy.bwie.com.lianxi.mvp;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import rx.Observable;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/17
 * desc:
 */
public interface HomeService {
    //商品详情
    @GET("commodity/v1/findCommodityDetailsById")
    Observable<HomeShow> RetrofitHomeShow( @Query("commodityId")int commodityId);

    //商品评论列表
    @GET("commodity/v1/CommodityCommentList")
    Observable<HomeShowEvaluate> RetrofitHomeShowEvaluate(@Query("page")int page, @Query("count")int count, @Query("commodityId")int commodityId);

    //加入购物车
    @PUT("order/verify/v1/syncShoppingCart")
    @FormUrlEncoded
    Observable<HomeShowEvaluate> RetrofitAddShoppingCar(@Header("userId")int userId, @Header("sessionId")String sessionId, @Field("data")String data);


}
