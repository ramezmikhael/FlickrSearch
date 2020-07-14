package project.ramezreda.flickrsearch.ui.main

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
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
            refreshRecyclerView()
        })

        initUI()
        initRecyclerView()
    }

    private fun initUI() {
        recyclerViewPhotos = findViewById(R.id.recyclerViewPhotos)
        editTextSearch = findViewById(R.id.editTextSearch)
        buttonGo = findViewById(R.id.buttonGo)
        textViewMessage = findViewById(R.id.textViewMessage)

        buttonGo.setOnClickListener { search() }

        historyAdapter.apply {
            editTextSearch.setAdapter(this)
        }
    }

    private fun initRecyclerView() {
        recyclerViewPhotos.adapter = adapter
        recyclerViewPhotos.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
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
        historyAdapter.add(editTextSearch.text.toString())
    }

    override fun onPhotoSelected(photo: Photo) {
        val intent = Intent(this, PhotoActivity::class.java)
        intent.putExtra(PhotoActivity.EXTRA_PHOTO_KEY, photo)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        MenuInflater(this).inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        if (item.itemId == R.id.action_result_count) {

            showResultsCountDialog()
        }
        return false
    }

    private fun showResultsCountDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.result_count)

        val input = EditText(this)
        input.setText(viewModel.resultsCount.value?.toString())
        input.inputType = InputType.TYPE_NUMBER_VARIATION_NORMAL
        builder.setView(input)

        builder.setPositiveButton(
            getString(R.string.ok)
        ) { _, _ -> viewModel.resultsCount.value = input.text.toString().toInt() }
        builder.setNegativeButton(
            getString(R.string.cancel)
        ) { dialog, _ -> dialog.cancel() }

        builder.show()
    }
}