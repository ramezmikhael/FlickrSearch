package project.ramezreda.flickrsearch.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Photo(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: String,
    val title: String,
    @SerializedName("ispublic")
    val isPublic: String,
    @SerializedName("isfriend")
    val isFriend: String,
    @SerializedName("isfamily")
    val isFamily: String
) : Parcelable