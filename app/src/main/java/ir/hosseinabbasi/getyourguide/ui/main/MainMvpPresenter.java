package ir.hosseinabbasi.getyourguide.ui.main;


import ir.hosseinabbasi.getyourguide.di.PerActivity;
import ir.hosseinabbasi.getyourguide.ui.base.MvpPresenter;

/**
 * Created by Dr.jacky on 2017/05/19.
 */

@PerActivity
public interface MainMvpPresenter<V extends MainMvpView> extends MvpPresenter<V> {
    void onViewInitialized();
    void onCardExhausted();
}
