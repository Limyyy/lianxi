package lqy.bwie.com.lianxi.mvp.p;

import java.util.ArrayList;

import lqy.bwie.com.lianxi.AddShoppingCar;
import lqy.bwie.com.lianxi.DetailsActivity;
import lqy.bwie.com.lianxi.mvp.HomeShow;
import lqy.bwie.com.lianxi.mvp.HomeShowEvaluate;
import lqy.bwie.com.lianxi.mvp.HomeShowMod;
import lqy.bwie.com.lianxi.mvp.m.IHomeShow;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/17
 * desc:
 */
public class HomeShowPre {
    IHomeShow iHomeShowl;
    HomeShowMod homeShowMod;

    public HomeShowPre(IHomeShow iHomeShowl) {
        this.iHomeShowl = iHomeShowl;
        homeShowMod=new HomeShowMod();
    }

    public void setPreHomeShowEvaluate(int commodityId,int page,int count){
        homeShowMod.setPreHomeShowEvaluate( commodityId, page, count, new HomeShowMod.CallBack() {
            @Override
            public void onSuccess(HomeShow homeShow, HomeShowEvaluate homeShowEvaluate) {
                iHomeShowl.onSuccess(homeShow,homeShowEvaluate);
            }
        });
    }

    public void onDestroy(){
        if (homeShowMod!=null) {
            homeShowMod.onDestroy();
            homeShowMod=null;
        }
    }
    //加入购物车
    public void setPreAddShoppingCar( ArrayList<AddShoppingCar> list) {
        homeShowMod.setModAddShoppingCar(1, "1", list, new HomeShowMod.AddCallBack() {
            @Override
            public void onAddSuccess(HomeShowEvaluate homeShowEvaluate) {
                iHomeShowl.onAddSuccess(homeShowEvaluate);
            }
        });
    }
}
