package com.example.feorin.hidingfab;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.transition.TransitionManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private FloatingActionButton floatingActionButton;
    boolean mIsHiding = false;
    private CoordinatorLayout mainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainContent = (CoordinatorLayout) findViewById(R.id.main_content);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        List<MyItem> fixedDataSet = createFixedDataSet();
        mAdapter = new MyAdapter(fixedDataSet);

        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                // While RecyclerView enters dragging state
                // (scroll, fling) we want our FAB to disappear.

                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    ScaleTransition scaleTransition = new ScaleTransition();
                    scaleTransition.addTarget(floatingActionButton);
                    scaleTransition.setDuration(200);
                    scaleTransition.setInterpolator(new LinearInterpolator());

                    TransitionManager.beginDelayedTransition(mainContent, scaleTransition);
                    floatingActionButton.setVisibility(View.INVISIBLE);

                }

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    ScaleTransition scaleTransition = new ScaleTransition();
                    scaleTransition.addTarget(floatingActionButton);
                    scaleTransition.setDuration(350);
                    scaleTransition.setInterpolator(new OvershootInterpolator());

                    TransitionManager.beginDelayedTransition(mainContent, scaleTransition);
                    floatingActionButton.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    public List<MyItem> createFixedDataSet() {

        List<MyItem> mockContentList = new ArrayList<>();

        mockContentList.add(new MyItem(R.drawable.pic1));
        mockContentList.add(new MyItem(R.drawable.pic2));
        mockContentList.add(new MyItem(R.drawable.pic3));
        mockContentList.add(new MyItem(R.drawable.pic4));
        mockContentList.add(new MyItem(R.drawable.pic5));
        mockContentList.add(new MyItem(R.drawable.pic6));
        mockContentList.add(new MyItem(R.drawable.pic7));
        mockContentList.add(new MyItem(R.drawable.pic8));
        mockContentList.add(new MyItem(R.drawable.pic9));
        mockContentList.add(new MyItem(R.drawable.pic10));
        mockContentList.add(new MyItem(R.drawable.pic11));
        mockContentList.add(new MyItem(R.drawable.pic12));
        mockContentList.add(new MyItem(R.drawable.pic13));
        mockContentList.add(new MyItem(R.drawable.pic14));
        mockContentList.add(new MyItem(R.drawable.pic15));
        mockContentList.add(new MyItem(R.drawable.pic16));
        mockContentList.add(new MyItem(R.drawable.pic17));
        mockContentList.add(new MyItem(R.drawable.pic18));
        mockContentList.add(new MyItem(R.drawable.pic19));
        mockContentList.add(new MyItem(R.drawable.pic20));
        mockContentList.add(new MyItem(R.drawable.pic21));
        mockContentList.add(new MyItem(R.drawable.pic22));
        mockContentList.add(new MyItem(R.drawable.pic23));
        mockContentList.add(new MyItem(R.drawable.pic14));

        return mockContentList;
    }

    public void animateHideFab() {

        if (mIsHiding || floatingActionButton.getVisibility() != View.VISIBLE) {
            return;
        }

        mIsHiding = true;
        Animation anim = AnimationUtils.loadAnimation(
                floatingActionButton.getContext(), R.anim.design_fab_out);
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(200);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mIsHiding = true;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mIsHiding = false;
                floatingActionButton.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        floatingActionButton.startAnimation(anim);

    }

    public void animateShowFab() {

        if (floatingActionButton.getVisibility() != View.VISIBLE || mIsHiding) {

            floatingActionButton.clearAnimation();
            floatingActionButton.setVisibility(View.VISIBLE);

            Animation anim = android.view.animation.AnimationUtils.loadAnimation(
                    floatingActionButton.getContext(), R.anim.design_fab_in);
            anim.setDuration(400);
            anim.setInterpolator(new OvershootInterpolator());
            anim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    floatingActionButton.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
            floatingActionButton.startAnimation(anim);
        }
    }
}