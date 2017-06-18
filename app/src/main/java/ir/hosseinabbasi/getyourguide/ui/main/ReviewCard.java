package ir.hosseinabbasi.getyourguide.ui.main;

import android.widget.TextView;

import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

import ir.hosseinabbasi.getyourguide.R;
import ir.hosseinabbasi.getyourguide.data.db.model.Data;

/**
 * Created by Dr.jacky on 2017/05/19.
 */

@NonReusable
@Layout(R.layout.card_layout)
public class ReviewCard {
    private static final String TAG = "ReviewCard";

    @View(R.id.tv_review_txt)
    private TextView mReviewTextView;

    private Data mReview;

    public ReviewCard(Data review) {
        mReview = review;
    }

    @Resolve
    private void onResolved() {
        mReviewTextView.setText(mReview.getRating());
    }
}
