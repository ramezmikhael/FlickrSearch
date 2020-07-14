package project.ramezreda.flickrsearch.repository

import io.reactivex.Single
import project.ramezreda.flickrsearch.model.JsonFlickrAPIModel
import project.ramezreda.flickrsearch.network.APIClient
import project.ramezreda.flickrsearch.network.APIInterface

class SearchRepository {
    private val apiInterface: APIInterface = APIClient.getClient().create(APIInterface::class.java)

    fun searchPhotos(searchText: String): Single<JsonFlickrAPIModel> {
        return apiInterface.searchPhotos(
            "flickr.photos.search",
            "1508443e49213ff84d566777dc211f2a",
            searchText, 25, "json", 1
        )
    }
}