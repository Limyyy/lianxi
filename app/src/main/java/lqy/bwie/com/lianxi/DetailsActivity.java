package lqy.bwie.com.lianxi;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import lqy.bwie.com.lianxi.adapter.HomeShowMainAdapter;
import lqy.bwie.com.lianxi.http.SpUtil;
import lqy.bwie.com.lianxi.mvp.HomeShow;
import lqy.bwie.com.lianxi.mvp.HomeShowEvaluate;
import lqy.bwie.com.lianxi.mvp.m.IHomeShow;
import lqy.bwie.com.lianxi.mvp.p.HomeShowPre;

public class DetailsActivity extends AppCompatActivity implements IHomeShow{


    private RecyclerView home_show_ryl;
    private ImageView home_show_image_tui;
    private TextView home_show_up_goods;
    private View home_show_up_goods_view;
    private TextView home_show_up_details;
    private View home_show_up_details_view;
    private TextView home_show_up_evaluate;
    private View home_show_up_evaluate_view;
    private LinearLayout lay111;
    private HomeShowFragmentCallBack homeShowFragmentCallBack;
    private ImageView home_show_up_evaluate_buy;
    private ImageView home_show_up_evaluate_add;
    private ArrayList<AddShoppingCar> list=new ArrayList<>();
    private HomeShowPre homeShowPre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        initView();
        EventBus.getDefault().register(this);

        homeShowPre = new HomeShowPre(this);

        //退出本页面
        home_show_image_tui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeShowFragmentCallBack.callback();
            }
        });

        //滑动监听
        home_show_ryl.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState){
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        lay111.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        });
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCommodityId(ShowEventBus showEventBus) {
         homeShowPre.setPreHomeShowEvaluate(showEventBus.getCommodityId(), 1, 10);
    }

    private void initView() {
        home_show_ryl = (RecyclerView) findViewById(R.id.home_show_ryl);
        home_show_image_tui = (ImageView) findViewById(R.id.home_show_image_tui);
        home_show_up_goods = (TextView) findViewById(R.id.home_show_up_goods);
        home_show_up_goods_view = (View) findViewById(R.id.home_show_up_goods_view);
        home_show_up_details = (TextView) findViewById(R.id.home_show_up_details);
        home_show_up_details_view = (View) findViewById(R.id.home_show_up_details_view);
        home_show_up_evaluate = (TextView) findViewById(R.id.home_show_up_evaluate);
        home_show_up_evaluate_view = (View) findViewById(R.id.home_show_up_evaluate_view);
        lay111 = (LinearLayout) findViewById(R.id.lay111);
        home_show_up_evaluate_buy = (ImageView) findViewById(R.id.home_show_up_evaluate_buy);
        home_show_up_evaluate_add = (ImageView) findViewById(R.id.home_show_up_evaluate_add);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public void setHomeShowFragmentCallBack(HomeShowFragmentCallBack homeShowFragmentCallBack1) {
        this.homeShowFragmentCallBack = homeShowFragmentCallBack1;
    }

    public interface HomeShowFragmentCallBack {
        void callback();
    }


    @Override
    public void onSuccess(final HomeShow homeShow, HomeShowEvaluate homeShowEvaluate) {
        HomeShow.ResultBean result = homeShow.getResult();
        HomeShowMainAdapter homeShowMainAdapter = new HomeShowMainAdapter(this, result, homeShowEvaluate);
        home_show_ryl.setLayoutManager(new LinearLayoutManager(this));
        home_show_ryl.setAdapter(homeShowMainAdapter);

        //加入购物车
        home_show_up_evaluate_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.clear();
                HomeShow.ResultBean result = homeShow.getResult();
                int commodityId = result.getCommodityId();
                AddShoppingCar addShoppingCar = new AddShoppingCar(commodityId, 1);
                list.add(addShoppingCar);
                homeShowPre.setPreAddShoppingCar(list);
            }
        });
    }

    @Override
    public void onFailer(String msg) {

    }


    //加入购物车
    @Override
    public void onAddSuccess(HomeShowEvaluate homeShowEvaluate) {
        EventBus.getDefault().post(new AddShoppingCar(1,1));


}
}
