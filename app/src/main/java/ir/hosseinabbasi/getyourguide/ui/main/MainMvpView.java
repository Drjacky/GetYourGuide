package ir.hosseinabbasi.getyourguide.ui.main;

import java.util.List;

//import ir.hosseinabbasi.getyourguide.data.db.model.Review;
import ir.hosseinabbasi.getyourguide.data.db.model.Data;
import ir.hosseinabbasi.getyourguide.data.db.model.ReviewPOJO;
import ir.hosseinabbasi.getyourguide.ui.base.MvpView;

/**
 * Created by Dr.jacky on 2017/05/19.
 */

public interface MainMvpView extends MvpView {
    void refreshQuestionnaire(List<Data> questionList);
    void reloadQuestionnaire(List<Data> questionList);
}
