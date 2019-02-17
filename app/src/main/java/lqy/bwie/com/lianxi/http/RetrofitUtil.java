package lqy.bwie.com.lianxi.http;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/16
 * desc:
 */
public class RetrofitUtil {
    private static RetrofitUtil instance;
        private Retrofit mRetrofit;
        //private OkHttpClient client = new OkHttpClient();
        private OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new NetWorkInterceptor())
                .build();

        public static RetrofitUtil getInstance(){
            if (instance==null){
                instance = new RetrofitUtil();
            }
            return instance;
        }

        private RetrofitUtil() {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl("http://172.17.8.100/small/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(client)
                    .build();
        }

        public <T> T createApi(Class<T> cls){
            T t = mRetrofit.create(cls);
            return t;
        }
}
