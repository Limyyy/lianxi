package lqy.bwie.com.lianxi.mvp.p;

import lqy.bwie.com.lianxi.bean.Datas;
import lqy.bwie.com.lianxi.mvp.CallBack;
import lqy.bwie.com.lianxi.mvp.m.DataModel;
import lqy.bwie.com.lianxi.mvp.v.DataView;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/16
 * desc:
 */
public class DataPresenter {
    private DataModel mDataModel;
    private DataView mDataView;

    public DataPresenter(DataView dataView) {
        mDataView = dataView;
        mDataModel = new DataModel();
    }

    public void getDatas(String keyword, int page, int count) {
        mDataModel.getDatas(keyword, page, count, new CallBack() {
            @Override
            public void getDatas(Datas datas) {
                mDataView.getDatas(datas);
            }
        });
    }

}
