package lqy.bwie.com.lianxi.mvp.m;

import lqy.bwie.com.lianxi.mvp.HomeShow;
import lqy.bwie.com.lianxi.mvp.HomeShowEvaluate;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/17
 * desc:
 */
public interface IHomeShow {
    void onSuccess(HomeShow homeShow, HomeShowEvaluate homeShowEvaluate);
    void onFailer(String msg);
    void onAddSuccess(HomeShowEvaluate homeShowEvaluate);
}
