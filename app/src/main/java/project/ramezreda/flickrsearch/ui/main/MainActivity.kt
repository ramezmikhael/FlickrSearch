package project.ramezreda.flickrsearch.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import okhttp3.internal.notify
import project.ramezreda.flickrsearch.R
import project.ramezreda.flickrsearch.model.Photo
import project.ramezreda.flickrsearch.ui.details.PhotoActivity

class MainActivity : AppCompatActivity(), IPhotoSelect {

    private lateinit var recyclerViewPhotos: RecyclerView
    private lateinit var buttonGo: Button
    private lateinit var editTextSearch: AutoCompleteTextView
    private lateinit var textViewMessage: TextView

    private val historyAdapter: ArrayAdapter<String> by lazy {
        ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)
    }

    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    private val adapter: SearchDataAdapter by lazy {
        SearchDataAdapter(viewModel.photos?.value?.photos, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        viewModel.photos?.observe(this, Observer {
            Log.d("CHANGE", "count: ${viewModel.photos?.value?.photos?.photo?.size}")
            refreshRecyclerView()
        })

        initUI()
        initRecyclerView()

    }

    private fun initRecyclerView() {
        recyclerViewPhotos.adapter = adapter
        recyclerViewPhotos.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    private fun initUI() {
        recyclerViewPhotos = findViewById(R.id.recyclerViewPhotos)
        editTextSearch = findViewById(R.id.editTextSearch)
        buttonGo = findViewById(R.id.buttonGo)
        textViewMessage = findViewById(R.id.textViewMessage)

        buttonGo.setOnClickListener { search() }

        historyAdapter.also {
            editTextSearch.setAdapter(it)
        }
    }

    private fun refreshRecyclerView() {
        adapter.photos = viewModel.photos?.value?.photos
        adapter.notifyDataSetChanged()

        if (adapter.photos != null) {
            textViewMessage.visibility = View.GONE
        } else {
            textViewMessage.visibility = View.VISIBLE
        }
    }

    private fun search() {
        viewModel.searchPhotos(editTextSearch.text.toString())

        // Save search term to the recent list
        historyAdapter.add(editTextSearch.text.toString())
    }

    override fun onPhotoSelected(photo: Photo) {
        val intent = Intent(this, PhotoActivity::class.java)
        intent.putExtra(PhotoActivity.EXTRA_PHOTO_KEY, photo)
        startActivity(intent)
    }
}