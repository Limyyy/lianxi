package lqy.bwie.com.lianxi.mvp;

import lqy.bwie.com.lianxi.bean.Datas;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/16
 * desc:
 */
public interface DatasService {

    @GET("commodity/v1/findCommodityByKeyword")
    Call<Datas> getDatas(@Query("keyword")String keyword,@Query("page")int page,@Query("count")int count );
}
