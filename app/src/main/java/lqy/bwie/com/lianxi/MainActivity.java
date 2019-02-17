package lqy.bwie.com.lianxi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lqy.bwie.com.lianxi.adapter.MyAdapter;
import lqy.bwie.com.lianxi.bean.Datas;
import lqy.bwie.com.lianxi.mvp.p.DataPresenter;
import lqy.bwie.com.lianxi.mvp.v.DataView;

public class MainActivity extends AppCompatActivity implements DataView {

    @BindView(R.id.xRecyclerView)
    XRecyclerView xRecyclerView;
    @BindView(R.id.img_pop)
    ImageView imgPop;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.img_search)
    ImageView imgSearch;
    private DataPresenter dataPresenter;
    private String s;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        s = etSearch.getText().toString().trim();

        dataPresenter = new DataPresenter(this);
        dataPresenter.getDatas("板",1, 100);
    }

    @Override
    public void getDatas(Datas datas) {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        xRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mAdapter = new MyAdapter(this, datas);
        xRecyclerView.setAdapter(mAdapter);
    }

    @OnClick({R.id.img_pop, R.id.img_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_pop:
                break;
            case R.id.img_search:
                if (TextUtils.isEmpty(s)) {
                    Toast.makeText(this, "请输入你要搜索的商品", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    dataPresenter.getDatas(s, 1, 100);
                }
                break;
        }
    }
}
