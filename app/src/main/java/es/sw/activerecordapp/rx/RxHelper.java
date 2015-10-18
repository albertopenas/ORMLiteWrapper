package es.sw.activerecordapp.rx;

import java.util.concurrent.Callable;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by alberto on 27/9/15.
 */
public class RxHelper {
    /**
     * Method for create an observable with a callable
     * @param func
     * @param <T>
     * @return
     */
    public static <T> Observable<T> createObservable(final Callable<T> func) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(func.call());
                    subscriber.onCompleted();
                } catch (Exception ex) {
                    subscriber.onError(ex);
                }
            }
        });
    }
}
