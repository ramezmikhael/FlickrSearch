package project.ramezreda.flickrsearch.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import project.ramezreda.flickrsearch.R
import project.ramezreda.flickrsearch.model.Photo
import project.ramezreda.flickrsearch.model.Photos
import project.ramezreda.flickrsearch.ui.main.SearchDataAdapter.SearchViewHolder
import project.ramezreda.flickrsearch.utils.UrlBuilder

class SearchDataAdapter(var photos: Photos?, val photoSelect: IPhotoSelect) : RecyclerView.Adapter<SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_list_item, parent, false)
        return SearchViewHolder(view)
    }

    override fun getItemCount() : Int {
        return if(photos != null) {
            photos?.photo?.size!!
        } else
            0
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(photos?.photo?.get(position)!!)
    }

    inner class SearchViewHolder(private val view : View) : RecyclerView.ViewHolder(view) {
        private val image : ImageView = view.findViewById(R.id.imageThumb)
        private val title: TextView = view.findViewById(R.id.textViewTitle)

        fun bind(photo: Photo) {
            title.text = photo.title
            Glide.with(image.context).load(photo.let {
                UrlBuilder.buildThumbnailUrl(
                    it
                )
            }).into(image)

            view.setOnClickListener {
                photoSelect.let {  photoSelect.onPhotoSelected(photo) }
            }
        }
    }
}

