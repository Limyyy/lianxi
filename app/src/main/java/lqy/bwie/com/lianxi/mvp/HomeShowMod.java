package lqy.bwie.com.lianxi.mvp;

import com.google.gson.Gson;

import java.util.ArrayList;

import lqy.bwie.com.lianxi.AddShoppingCar;
import lqy.bwie.com.lianxi.http.RetrofitUtil;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/17
 * desc:
 */
public class HomeShowMod {
    private HomeService anInterface;

    public void setPreHomeShowEvaluate( final int commodityId, final int page, final int count, final CallBack callBack){
        anInterface = RetrofitUtil.getInstance().createApi(HomeService.class);
        anInterface.RetrofitHomeShow(commodityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HomeShow>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(final HomeShow homeShow) {
                        anInterface.RetrofitHomeShowEvaluate(page,count,commodityId)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Subscriber<HomeShowEvaluate>() {
                                    @Override
                                    public void onCompleted() {

                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }

                                    @Override
                                    public void onNext(HomeShowEvaluate homeShowEvaluate) {
                                        callBack.onSuccess(homeShow,homeShowEvaluate);
                                    }
                                });
                    }
                });
    }

    public void setModAddShoppingCar(int userId, String sessionId, ArrayList<AddShoppingCar> list, final AddCallBack addCallBack) {
        Gson gson=new Gson();
        String s = gson.toJson(list);
        Observable<HomeShowEvaluate> homeShowEvaluateObservable = anInterface.RetrofitAddShoppingCar(userId, sessionId, s);
        homeShowEvaluateObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<HomeShowEvaluate>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(HomeShowEvaluate homeShowEvaluate) {
                        addCallBack.onAddSuccess(homeShowEvaluate);
                    }
                });
    }

    public interface AddCallBack{
        void onAddSuccess(HomeShowEvaluate homeShowEvaluate);
    }

    public interface CallBack{
        void onSuccess(HomeShow homeShow, HomeShowEvaluate homeShowEvaluate);;
    }

    public void onDestroy(){
        if (anInterface!=null) {
            anInterface=null;
        }
    }
}
