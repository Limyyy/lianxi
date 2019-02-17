package lqy.bwie.com.lianxi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * author lim
 * email:lim@123.com
 * date 2019/2/17
 * desc:
 */
public class HomeShowMainViewPagerAdapter extends PagerAdapter {

    private String[] split;
    private Context context;

    public HomeShowMainViewPagerAdapter(String[] split, Context context) {
        this.split = split;
        this.context = context;
    }

    @Override
    public int getCount() {
        return split.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return o == view;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        String s = split[position];
        ImageView imageView = new ImageView(context);
        Picasso.with(context).load(s).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
