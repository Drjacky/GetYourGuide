package ir.hosseinabbasi.getyourguide.ui.base;

import android.support.annotation.StringRes;

/**
 * Created by Dr.jacky on 2017/05/19.
 */

public interface MvpView {
    void showLoading();

    void hideLoading();

    void onError(@StringRes int resId);

    void onError(String message);

    boolean isNetworkConnected();

    void hideKeyboard();
}
