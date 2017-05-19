package ir.hosseinabbasi.getyourguide.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ListView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import ir.hosseinabbasi.getyourguide.R;
import ir.hosseinabbasi.getyourguide.data.db.model.Review;
import ir.hosseinabbasi.getyourguide.ui.base.BaseActivity;

/**
 * Created by Dr.jacky on 2017/05/19.
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
    public void refreshQuestionnaire(List<Review.dataz> questionList) {
        /*for (Question question : questionList) {
            if (question != null) {
                mCardsContainerView.addView(new QuestionCard(question));
            }
        }*/

        //ArrayAdapter<Question> adapter = new ArrayAdapter<Question>(this,android.R.layout.simple_list_item_1,questionList);
        mReviewsContainerView.setAdapter(new CustomArrayAdapter(MainActivity.this,questionList));
    }

    @Override
    public void reloadQuestionnaire(List<Review.dataz> questionList) {
        refreshQuestionnaire(questionList);
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

    /*private void setupCardContainerView() {

        int screenWidth = ScreenUtils.getScreenWidth(this);
        int screenHeight = ScreenUtils.getScreenHeight(this);

        mCardsContainerView.getBuilder()
                .setDisplayViewCount(3)
                .setHeightSwipeDistFactor(10)
                .setWidthSwipeDistFactor(5)
                .setSwipeDecor(new SwipeDecor()
                        .setViewWidth((int) (0.90 * screenWidth))
                        .setViewHeight((int) (0.75 * screenHeight))
                        .setPaddingTop(20)
                        .setSwipeRotationAngle(10)
                        .setRelativeScale(0.01f));

        mCardsContainerView.addItemRemoveListener(new ItemRemovedListener() {
            @Override
            public void onItemRemoved(int count) {
                if (count == 0) {
                    // reload the contents again after 1 sec delay
                    new Handler(getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mPresenter.onCardExhausted();
                        }
                    }, 800);
                }
            }
        });
    }*/
}
