package project.ramezreda.flickrsearch.ui.main

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import project.ramezreda.flickrsearch.R

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewPhotos: RecyclerView
    private lateinit var buttonGo: Button
    private lateinit var editTextSearch: EditText
    private lateinit var textViewMessage: TextView

    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }

    private val adapter: SearchDataAdapter by lazy {
        SearchDataAdapter(viewModel.photos.value?.photos)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        viewModel.photos.observe(this, Observer {
            Log.d("CHANGE", "count: ${viewModel.photos.value?.photos?.photo?.size}")
            refreshRecyclerView()
        })

        initUI()
        initRecyclerView()

    }

    private fun initRecyclerView() {
        recyclerViewPhotos.adapter = adapter
        recyclerViewPhotos.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
    }

    private fun initUI() {
        recyclerViewPhotos = findViewById(R.id.recyclerViewPhotos)
        editTextSearch = findViewById(R.id.editTextSearch)
        buttonGo = findViewById(R.id.buttonGo)
        textViewMessage = findViewById(R.id.textViewMessage)
        buttonGo.setOnClickListener { search() }
    }

    private fun refreshRecyclerView() {
        adapter.photos = viewModel.photos.value?.photos!!
        adapter.notifyDataSetChanged()

        adapter.photos.let { textViewMessage.visibility = View.GONE }
    }

    private fun search() {
        viewModel.searchPhotos(editTextSearch.text.toString())
    }
}