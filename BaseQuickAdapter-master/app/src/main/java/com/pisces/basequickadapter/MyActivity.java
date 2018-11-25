package com.pisces.basequickadapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.pisces.basequickadapter.example.MovieEntity;
import com.pisces.basequickadapter.example.MovieQuickAdapter;
import com.pisces.basequickadapter.example.MyMovieQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者: x00378851
 * 日期: 2018/11/25 16:32
 */

public class MyActivity extends AppCompatActivity implements Callback<MovieEntity>{

    private static final String TAG = MyActivity.class.getSimpleName();
    private RecyclerView mRcv;
    private MyMovieQuickAdapter mAdapter;
    List<MovieEntity.SubjectsBean> mBeanList;
    LinearLayoutManager mLinearLayoutManager;
    static int CURRENT_PAGE = 0;//起始页第一页
    static final int PAGE_COUNT = 50;//每页有50条数据


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        mRcv = (RecyclerView)findViewById(R.id.rcv);

        mLinearLayoutManager = new LinearLayoutManager(this);
        mRcv.setLayoutManager(mLinearLayoutManager);

        Log.e(TAG, "onCreate: ");
        mBeanList = new ArrayList<>();
        mAdapter = new MyMovieQuickAdapter(this, mBeanList);
        RetrofitManager.getService().getTopMovie(CURRENT_PAGE * PAGE_COUNT , PAGE_COUNT).enqueue(this);

    }

    @Override
    public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
        Log.e(TAG, "onResponse: " + mBeanList);
        if (mRcv.getAdapter() == null) {
            mBeanList.addAll(response.body().getSubjects());
            mRcv.setAdapter(mAdapter);
        } else {
            //mAdapter.appendList(response.body().getSubjects());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure(Call<MovieEntity> call, Throwable t) {
        Log.e(TAG, "====>" + t.toString());
    }
}
