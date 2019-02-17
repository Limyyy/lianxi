package lqy.bwie.com.lianxi.mvp.m;

import lqy.bwie.com.lianxi.bean.Datas;
import lqy.bwie.com.lianxi.http.RetrofitUtil;
import lqy.bwie.com.lianxi.mvp.CallBack;
import lqy.bwie.com.lianxi.mvp.DatasService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/16
 * desc:
 */
public class DataModel {

    public void getDatas(String keyword,int page, int count, final CallBack callBack){
        DatasService service = RetrofitUtil.getInstance().createApi(DatasService.class);
        Call<Datas> datas = service.getDatas(keyword,page, count);
        datas.enqueue(new Callback<Datas>() {
            @Override
            public void onResponse(Call<Datas> call, Response<Datas> response) {
                Datas body = response.body();
                callBack.getDatas(body);
            }

            @Override
            public void onFailure(Call<Datas> call, Throwable t) {

            }
        });
    }
}
