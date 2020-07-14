package project.ramezreda.flickrsearch.repository

import io.reactivex.Single
import project.ramezreda.flickrsearch.BuildConfig
import project.ramezreda.flickrsearch.model.JsonFlickrAPI
import project.ramezreda.flickrsearch.network.APIClient
import project.ramezreda.flickrsearch.network.APIInterface

class SearchRepository {
    private val apiInterface: APIInterface = APIClient.getClient().create(APIInterface::class.java)

    fun searchPhotos(searchText: String): Single<JsonFlickrAPI> {
        return apiInterface.searchPhotos(
            "flickr.photos.search",
            BuildConfig.FLICKR_APIKEY,
            searchText, 25, "json", 1
        )
    }
}