package lqy.bwie.com.lianxi.adapter;

import android.content.Context;
import android.os.Handler;
import lqy.bwie.com.lianxi.R;

import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import lqy.bwie.com.lianxi.DetailsActivity;
import lqy.bwie.com.lianxi.mvp.HomeShow;
import lqy.bwie.com.lianxi.mvp.HomeShowEvaluate;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/17
 * desc:
 */
public class HomeShowMainAdapter extends RecyclerView.Adapter {

    private Context context;
    private HomeShow.ResultBean result;
    private HomeShowEvaluate homeShowEvaluate;
    private String[] split;
    private Handler handler;
    public HomeShowMainAdapter(Context context, HomeShow.ResultBean result, HomeShowEvaluate homeShowEvaluate) {
        this.context = context;
        this.result = result;
        this.homeShowEvaluate = homeShowEvaluate;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder=null;
        int itemViewType = getItemViewType(i);
        if (itemViewType==0) {
            View view = LayoutInflater.from(context).inflate(R.layout.home_show_goods_item, null);
            viewHolder=new HomeShowMainViewHolderOne(view);
        }
        else if (itemViewType==1){
            View view = LayoutInflater.from(context).inflate(R.layout.home_show_goods_webview, null);
            viewHolder = new HomeShowMainViewHolderTwo(view);
        }
        else {
            View view = LayoutInflater.from(context).inflate(R.layout.home_show_goods_pingjia, null);
            viewHolder=new HomeShowMainViewHolderThree(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int itemViewType = getItemViewType(i);
        if (itemViewType==0) {
            final HomeShowMainViewHolderOne homeShowMainViewHolderOne= (HomeShowMainViewHolderOne) viewHolder;
            //图片
            String picture = result.getPicture();
            split = picture.split(",");
            HomeShowMainViewPagerAdapter homeShowMainViewPagerAdapter = new HomeShowMainViewPagerAdapter(split,context);
            homeShowMainViewHolderOne.home_show_goods_item_viewpager.setAdapter(homeShowMainViewPagerAdapter);
            homeShowMainViewHolderOne.home_show_goods_item_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i1) {

                }

                @Override
                public void onPageSelected(int i) {
                    if (i==0) {
                        homeShowMainViewHolderOne.home_show_goods_item_viewpager_page.setText(1+"/"+ split.length);
                    }
                    else if (i==1){
                        homeShowMainViewHolderOne.home_show_goods_item_viewpager_page.setText(2+"/"+ split.length);
                    }
                    else if (i==2){
                        homeShowMainViewHolderOne.home_show_goods_item_viewpager_page.setText(3+"/"+ split.length);
                    }
                    else if (i==3){
                        homeShowMainViewHolderOne.home_show_goods_item_viewpager_page.setText(4+"/"+ split.length);
                    }
                    else {
                        homeShowMainViewHolderOne.home_show_goods_item_viewpager_page.setText(5+"/"+ split.length);
                    }
                }
                @Override
                public void onPageScrollStateChanged(int i) {

                }
            });
            //数量
            homeShowMainViewHolderOne.home_show_goods_item_viewpager_page.setText(1+"/"+ split.length);
            //价格
            int price = result.getPrice();
            homeShowMainViewHolderOne.home_show_goods_item_price.setText("￥:"+price);
            //已售
            int saleNum = result.getSaleNum();
            homeShowMainViewHolderOne.home_show_goods_item_saleNum.setText("已销"+saleNum+"件");
            //名字
            String commodityName = result.getCommodityName();
            homeShowMainViewHolderOne.home_show_goods_item_commodityName.setText(commodityName);
            //重量
            int weight = result.getWeight();
            homeShowMainViewHolderOne.home_show_goods_item_weight.setText("重量:"+weight+"kg");

            handler.sendEmptyMessage(0);

        }
        else if (itemViewType==1){
            final HomeShowMainViewHolderTwo homeShowMainViewHolderTwo= (HomeShowMainViewHolderTwo) viewHolder;
            homeShowMainViewHolderTwo.home_show_goods_webview_webview.setWebViewClient(new WebViewClient());
            homeShowMainViewHolderTwo.home_show_goods_webview_webview.getSettings().setLoadsImagesAutomatically(true);
            homeShowMainViewHolderTwo.home_show_goods_webview_webview.loadDataWithBaseURL(null,"<!DOCTYPE html><html><body>"+result.getDetails()+"</body></html>", "text/html" , "utf-8", null);
        }
        else {
            final HomeShowMainViewHolderThree homeShowMainViewHolderThree= (HomeShowMainViewHolderThree) viewHolder;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0) {
            return 0;
        }
        else if (position==1){
            return 1;
        }
        else {
            return 2;
        }
    }
    //商品
    public class HomeShowMainViewHolderOne extends RecyclerView.ViewHolder {
        ViewPager home_show_goods_item_viewpager;
        TextView home_show_goods_item_viewpager_page;
        TextView home_show_goods_item_price;
        TextView home_show_goods_item_saleNum;
        TextView home_show_goods_item_commodityName;
        TextView home_show_goods_item_weight;

        public HomeShowMainViewHolderOne(@NonNull View itemView) {
            super(itemView);
            home_show_goods_item_viewpager=itemView.findViewById(R.id.home_show_goods_item_viewpager);
            home_show_goods_item_viewpager_page=itemView.findViewById(R.id.home_show_goods_item_viewpager_page);
            home_show_goods_item_price=itemView.findViewById(R.id.home_show_goods_item_price);
            home_show_goods_item_saleNum=itemView.findViewById(R.id.home_show_goods_item_saleNum);
            home_show_goods_item_commodityName=itemView.findViewById(R.id.home_show_goods_item_commodityName);
            home_show_goods_item_weight=itemView.findViewById(R.id.home_show_goods_item_weight);

            handler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    switch (msg.what){
                        case 0:
                            int currentItem = home_show_goods_item_viewpager.getCurrentItem();
                            home_show_goods_item_viewpager.setCurrentItem((currentItem+1)%split.length);
                            handler.sendEmptyMessageDelayed(0,2000);
                            break;
                    }
                }
            };
        }
    }
    //详情
    public class HomeShowMainViewHolderTwo extends RecyclerView.ViewHolder {
        WebView home_show_goods_webview_webview;
        public HomeShowMainViewHolderTwo(@NonNull View itemView) {
            super(itemView);
            home_show_goods_webview_webview=itemView.findViewById(R.id.home_show_goods_webview_webview);
        }
    }
    //评论
    public class HomeShowMainViewHolderThree extends RecyclerView.ViewHolder {

        public HomeShowMainViewHolderThree(@NonNull View itemView) {
            super(itemView);
        }
    }

}
