package ir.hosseinabbasi.getyourguide.ui.main;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import ir.hosseinabbasi.getyourguide.data.DataManager;
import ir.hosseinabbasi.getyourguide.data.db.model.Data;
import ir.hosseinabbasi.getyourguide.ui.base.BasePresenter;
import ir.hosseinabbasi.getyourguide.utils.rx.SchedulerProvider;


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
                .getAllReviews()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<Data>>() {
                    @Override
                    public void accept(List<Data> reviewList) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        if (reviewList != null) {
                            getMvpView().refreshReviewList(reviewList);
                        }
                    }
                }));
    }

    @Override
    public void onCardExhausted() {
        getCompositeDisposable().add(getDataManager()
                .getAllReviews()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<List<Data>>() {
                    @Override
                    public void accept(List<Data> reviewList) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }

                        if (reviewList != null) {
                            getMvpView().reloadReviewList(reviewList);
                        }
                    }
                }));
    }
}
