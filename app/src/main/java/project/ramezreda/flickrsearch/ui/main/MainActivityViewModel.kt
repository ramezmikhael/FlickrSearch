package project.ramezreda.flickrsearch.ui.main

import android.app.Application
import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import project.ramezreda.flickrsearch.model.JsonFlickrAPI
import project.ramezreda.flickrsearch.repository.SearchRepository

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {
    var photos: MutableLiveData<JsonFlickrAPI?>? = MutableLiveData()
    var resultsCount: MutableLiveData<Int> = MutableLiveData(25)
    val historyAdapter: MutableLiveData<ArrayAdapter<String>> =
        MutableLiveData(ArrayAdapter(application, android.R.layout.simple_list_item_1))

    fun searchPhotos(searchText: String) {
        val call = SearchRepository()
            .searchPhotos(searchText, resultsCount.value!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                photos?.value = it
                Log.d("Success", it.stat)
            }, {
                Log.d("Error", it.message)
            })
    }
}