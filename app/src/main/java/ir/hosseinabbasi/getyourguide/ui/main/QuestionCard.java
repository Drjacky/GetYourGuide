package ir.hosseinabbasi.getyourguide.ui.main;

import android.widget.TextView;

import ir.hosseinabbasi.getyourguide.R;
import ir.hosseinabbasi.getyourguide.data.db.model.Review;

import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

/**
 * Created by Dr.jacky on 2017/05/19.
 */

@NonReusable
@Layout(R.layout.card_layout)
public class QuestionCard {
    private static final String TAG = "QuestionCard";

    @View(R.id.tv_question_txt)
    private TextView mQuestionTextView;

    private Review mQuestion;

    public QuestionCard(Review question) {
        mQuestion = question;
    }

    @Resolve
    private void onResolved() {
        mQuestionTextView.setText(mQuestion.getRating());
    }
}
