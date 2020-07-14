package project.ramezreda.flickrsearch.network;

import io.reactivex.Single;
import project.ramezreda.flickrsearch.model.JsonFlickrAPIModel;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET("services/rest")
    Single<JsonFlickrAPIModel> searchPhotos(@Query("method") String method,
                                            @Query("api_key") String apiKey,
                                            @Query("text") String text,
                                            @Query("per_page") int perPage,
                                            @Query("format") String format,
                                            @Query("nojsoncallback") int noJsonCallback);
}
