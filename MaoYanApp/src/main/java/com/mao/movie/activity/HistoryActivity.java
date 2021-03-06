package com.mao.movie.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mao.movie.R;
import com.mao.movie.adapter.HistoryAdapter;
import com.mao.movie.model.History;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by GaoMatrix on 2016/10/26.
 */
public class HistoryActivity extends BaseActivity {

    @BindView(R.id.backButton)
    ImageButton mBackButton;
    @BindView(R.id.titleTextView)
    TextView mTitleTextView;
    @BindView(R.id.editTextView)
    TextView mEditTextView;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.selectAllTextView)
    TextView mSelectAllTextView;
    @BindView(R.id.deleteAllTextView)
    TextView mDeleteAllTextView;
    @BindView(R.id.unSelectAllTextView)
    TextView mUnSelectAllTextView;
    @BindView(R.id.selectOperationLayout)
    LinearLayout mSelectOperationLayout;

    private HistoryAdapter mAdapter = new HistoryAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

        init();
        mockData();
    }

    private void init() {
        mTitleTextView.setText("观看历史");
        mEditTextView.setVisibility(View.VISIBLE);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private void mockData() {
        List<History> historyList = new ArrayList<History>();
        historyList.add(new History("冰与火之歌", "2014-222--2 34:34:34"));
        historyList.add(new History("权利的游戏", "2014-222--2 34:34:34"));
        historyList.add(new History("破产姐妹花", "2014-222--2 34:34:34"));
        historyList.add(new History("地球百子", "2014-222--2 34:34:34"));
        historyList.add(new History("Nikita", "2014-222--2 34:34:34"));
        mAdapter.setDataList(historyList);
    }

    @OnClick({R.id.backButton, R.id.editTextView, R.id.unSelectAllTextView,
            R.id.selectAllTextView, R.id.deleteAllTextView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.backButton:
                finish();
                break;
            case R.id.editTextView:
                if (mAdapter.isEditMode()) {
                    mEditTextView.setText("编辑");
                    mAdapter.changeEditMode(false);
                    mSelectOperationLayout.setVisibility(View.GONE);
                } else {
                    mEditTextView.setText("取消");
                    mAdapter.changeEditMode(true);
                    mSelectOperationLayout.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.unSelectAllTextView:
                mAdapter.changeSelectAllMode(false);
                break;
            case R.id.selectAllTextView:
                mAdapter.changeSelectAllMode(true);
                break;
            case R.id.deleteAllTextView:
                mAdapter.deleteAll();
                break;
        }
    }
}
