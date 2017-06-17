package ir.hosseinabbasi.getyourguide.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ListView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.hosseinabbasi.getyourguide.R;
import ir.hosseinabbasi.getyourguide.data.db.model.Data;
import ir.hosseinabbasi.getyourguide.data.db.model.ReviewPOJO;
import ir.hosseinabbasi.getyourguide.ui.base.BaseActivity;
import ir.hosseinabbasi.getyourguide.utils.AppLogger;

/**
 * Created by Dr.jacky on 2017/05/19.
 *
 */
public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    /*@BindView(R.id.cards_container)
    SwipePlaceHolderView mCardsContainerView;*/

    @BindView(R.id.reviews_container)
    ListView mReviewsContainerView;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);

        setUnBinder(ButterKnife.bind(this));

        mPresenter.onAttach(this);

        setUp();
    }

    @Override
    public void refreshReviewList(List<Data> reviewList) {
        /*for (Data data : reviewList) {
            if (data != null) {
                mCardsContainerView.addView(new ReviewCard(data));
            }
        }*/

        //ArrayAdapter<Data> adapter = new ArrayAdapter<Data>(this,android.R.layout.simple_list_item_1,reviewList);
        mReviewsContainerView.setAdapter(new CustomArrayAdapter(MainActivity.this,reviewList));
    }

    @Override
    public void reloadReviewList(List<Data> reviewList) {
        refreshReviewList(reviewList);
        ScaleAnimation animation =
                new ScaleAnimation(
                        1.15f, 1, 1.15f, 1,
                        Animation.RELATIVE_TO_SELF, 0.5f,
                        Animation.RELATIVE_TO_SELF, 0.5f);

        mReviewsContainerView.setAnimation(animation);
        animation.setDuration(100);
        animation.start();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    public void onFragmentDetached(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .remove(fragment)
                    .commitNow();
        }
    }

    @Override
    protected void setUp() {
        setSupportActionBar(mToolbar);
        //setupCardContainerView();
        mPresenter.onViewInitialized();
    }
}
