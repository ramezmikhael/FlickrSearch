package project.ramezreda.flickrsearch.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import project.ramezreda.flickrsearch.model.JsonFlickrAPIModel
import project.ramezreda.flickrsearch.repository.SearchRepository

class MainActivityViewModel : ViewModel() {
    var photos: MutableLiveData<JsonFlickrAPIModel?> = MutableLiveData()

    fun searchPhotos(searchText: String) {
        val call = SearchRepository()
            .searchPhotos(searchText)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                photos.value = it
                Log.d("Success", it.stat)
            }, {
                Log.d("Error", it.message)
            })
    }
}