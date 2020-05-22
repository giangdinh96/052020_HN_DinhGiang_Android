package vn.teko.test.ui

import android.util.Log
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import vn.teko.test.base.BaseActivity
import vn.teko.test.base.BaseFragment
import vn.teko.test.ui.productlist.ProductListFragment
import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class MainActivity : BaseActivity() {

    override fun getRootFragment(): BaseFragment<*> = ProductListFragment.newInstance()

    override fun initLayout() {

        val publishSubject = PublishSubject.create<String>()
        publishSubject
            .debounce(300, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap { value ->
                Observable.create<String> {
                    it.onNext(value)
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                Log.d("Test", it)
            }, {
                Log.d("Test", it.toString())
            }, {
                Log.d("Test", "Complete")
            })

        thread {
            publishSubject.onNext("1")
            publishSubject.onNext("2")
            Thread.sleep(400)
            publishSubject.onNext("3")
            publishSubject.onNext("4")
        }


//        Observable.concat(getData(),getData(), getData2(),getData2())
//            .firstElement()
//            .retry()
//            .subscribe({
//                Log.d("Test", it)
//            }, {
//                Log.d("Test", it.toString())
//            }, {
//                Log.d("Test", "Complete")
//            })
    }

    fun getData(): Observable<String> {
        return Observable.create<String> {
            Log.d("Test", "First")
//            it.onNext("1")
//            it.onError(Exception("Eerror"))
            it.onComplete()
        }
    }

    fun getData2(): Observable<String> {
        return Observable.create<String> {
            Log.d("Test", "Second")
            it.onNext("2")
//            it.onComplete()
        }
    }
}
