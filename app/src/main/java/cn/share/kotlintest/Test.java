package cn.share.kotlintest;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import org.jetbrains.annotations.NotNull;

import cn.share.kotlintest.adapter.MainAdapterRecycler;
import cn.share.kotlintest.recyclerview.OnRecyclerItemClickListener;

/**
 * Created by jack on 2018/5/23
 */

public class Test extends Activity {

    private MainAdapterRecycler mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new MainAdapterRecycler(this);

        mAdapter.setOnItemClickListener(new OnRecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void click(@NotNull View view, int position) {

            }
        });
    }
}
