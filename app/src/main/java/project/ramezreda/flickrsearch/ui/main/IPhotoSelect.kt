package project.ramezreda.flickrsearch.ui.main

import project.ramezreda.flickrsearch.model.Photo

interface IPhotoSelect {
    fun onPhotoSelected(photo: Photo)
}