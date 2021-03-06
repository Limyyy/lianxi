package lqy.bwie.com.lianxi.http;

import android.util.Log;

import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.Locale;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/16
 * desc:
 */
public class NetWorkInterceptor implements Interceptor {

    @Override
public Response intercept(Interceptor.Chain chain) throws IOException {
    //这个chain里面包含了request和response，所以你要什么都可以从这里拿
    Request request = chain.request();

    long t1 = System.nanoTime();//请求发起的时间
    Logger.i(String.format(Locale.CHINA, "发送请求 %s on %s%n%s",
            request.url(), chain.connection(), request.headers()));

    Response response = chain.proceed(request);

    long t2 = System.nanoTime();//收到响应的时间

    ResponseBody responseBody = response.peekBody(1024 * 1024);

    //这里不能直接使用response.body().string()的方式输出日志
    //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
    //个新的response给应用层处理
    Logger.i(String.format(Locale.CHINA, "接收响应: [%s] %n返回json:【%s】 %.1fms%n%s",
            response.request().url(),
            responseBody.string(),
            (t2 - t1) / 1e6d,
            response.headers()));

    return response;
}
}
