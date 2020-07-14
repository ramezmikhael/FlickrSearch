package project.ramezreda.flickrsearch.utils

import project.ramezreda.flickrsearch.model.Photo

object UrlBuilder {
    fun buildThumbnailUrl(photo: Photo) : String {
        return "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_m.jpg"
    }

    fun buildBigSizePhotoUrl(photo: Photo) : String {
        return "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_b.jpg"
    }
}