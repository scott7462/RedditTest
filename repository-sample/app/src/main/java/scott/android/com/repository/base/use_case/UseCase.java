package scott.android.com.repository.base.use_case;


import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

public abstract class UseCase<T> {

    private final CompositeSubscription compositeDisposable = new CompositeSubscription();

    protected UseCase() {
    }

    public void execute(UseCaseSubscription<T> subscription) {
        if (subscription == null) {
            throw new IllegalArgumentException("subscription must not be null");
        }
        Observable<T> observable =
                this.createObservableUseCase()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
        compositeDisposable.add(observable.subscribe(subscription));
    }

    public void dispose() {
        if (!compositeDisposable.isUnsubscribed()) {
            compositeDisposable.unsubscribe();
        }
    }

    protected abstract Observable<T> createObservableUseCase();
}
