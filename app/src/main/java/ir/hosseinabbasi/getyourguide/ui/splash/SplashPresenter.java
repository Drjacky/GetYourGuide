package ir.hosseinabbasi.getyourguide.ui.splash;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import ir.hosseinabbasi.getyourguide.R;
import ir.hosseinabbasi.getyourguide.data.DataManager;
import ir.hosseinabbasi.getyourguide.ui.base.BasePresenter;
import ir.hosseinabbasi.getyourguide.utils.rx.SchedulerProvider;

/**
 * Created by Dr.jacky on 2017/05/19.
 * It is the decision-making counterpart of the View and is a pure java class, with no access to Android APIs.
 * It receives the user interactions passed on from its View and then takes the decision based on the business logic, finally instructing the View to perform specific actions.
 * It also communicates with the DataManager for any data it needs to perform business logic.
 */

public class SplashPresenter<V extends SplashMvpView> extends BasePresenter<V>
        implements SplashMvpPresenter<V> {

    @Inject
    public SplashPresenter(DataManager dataManager,
                           SchedulerProvider schedulerProvider,
                           CompositeDisposable compositeDisposable) {
        super(dataManager, schedulerProvider, compositeDisposable);
    }

    @Override
    public void onAttach(V mvpView) {
        super.onAttach(mvpView);

        //getMvpView().startSyncService();

        getCompositeDisposable().add(getDataManager()
                .seedDatabaseReviews()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        decideNextActivity();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (!isViewAttached()) {
                            return;
                        }
                        getMvpView().onError(R.string.some_error);
                        decideNextActivity();
                    }
                }));


    }

    private void decideNextActivity() {
        getMvpView().openMainActivity();
    }
}
