package ir.hosseinabbasi.getyourguide.ui.main;

import android.util.Log;

import ir.hosseinabbasi.getyourguide.data.DataManager;
import ir.hosseinabbasi.getyourguide.data.db.model.Data;
import ir.hosseinabbasi.getyourguide.data.db.model.ReviewPOJO;
import ir.hosseinabbasi.getyourguide.ui.base.BasePresenter;
import ir.hosseinabbasi.getyourguide.utils.AppLogger;
import ir.hosseinabbasi.getyourguide.utils.rx.SchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;


/**
 * Created by Dr.jacky on 2017/05/19.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V>
        implements MainMvpPresenter<V> {

    private static final String TAG = MainPresenter.class.getSimpleName();

    @Inject
    public MainPresenter(DataManager dataManager,
                         SchedulerProvider schedulerProvider,
                         CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onViewInitialized() {
        getCompositeDisposable().add(getDataManager()
                .getAllQuestions()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<Data>>() {
                    @Override
                    public void accept(List<Data> questionList) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        if (questionList != null) {
                            getMvpView().refreshQuestionnaire(questionList);
                        }
                    }
                }));
    }

    @Override
    public void onCardExhausted() {
        getCompositeDisposable().add(getDataManager()
                .getAllQuestions()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<Data>>() {
                    @Override
                    public void accept(List<Data> questionList) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        if (questionList != null) {
                            getMvpView().reloadQuestionnaire(questionList);
                        }
                    }
                }));
    }
}
