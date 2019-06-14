package com.example.chocofood.domain.usecase;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public abstract class UseCase<T, Params> {

    private final CompositeDisposable mCompositeDisposable;

    public UseCase() {
        mCompositeDisposable = new CompositeDisposable();
    }

    abstract Observable<T> createObservable(Params params);

    public void execute(DisposableObserver<T> observer, Params params) {
        final Observable<T> observable = this.createObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        addDisposable(observable.subscribeWith(observer));
    }

    public void dispose() {
        if (!mCompositeDisposable.isDisposed())
            mCompositeDisposable.dispose();
    }

    private void addDisposable(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }
}
