package project.ramezreda.flickrsearch.utils

import project.ramezreda.flickrsearch.model.PhotoModel

object UrlBuilder {
    fun buildThumbnailUrl(photo: PhotoModel) : String {
        return "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_m.jpg"
    }

    fun buildBigSizePhotoUrl(photo: PhotoModel) : String {
        return "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_b.jpg"
    }
}