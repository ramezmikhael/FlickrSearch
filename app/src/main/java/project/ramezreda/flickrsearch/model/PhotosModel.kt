package project.ramezreda.flickrsearch.model

data class PhotosModel(
    val page: Int,
    val pages: String,
    val perPage: Int,
    val total: String,
    val photo: List<PhotoModel>
)