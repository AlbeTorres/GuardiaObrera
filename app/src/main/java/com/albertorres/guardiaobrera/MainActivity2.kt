package com.albertorres.guardiaobrera

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AlertDialogLayout
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.albertorres.guardiaobrera.Adapters.RecyclerSearchAdapter
import com.albertorres.guardiaobrera.Database.AppDatabase
import com.albertorres.guardiaobrera.Database.Dao
import com.albertorres.guardiaobrera.POJO.PlanGuardiaObrera
import com.albertorres.guardiaobrera.databinding.ActivityMain2Binding
import com.albertorres.guardiaobrera.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.coroutines.CoroutineContext


class MainActivity2 : AppCompatActivity(){
    private lateinit var binding: ActivityMain2Binding
    private lateinit var dao: Dao
    private lateinit var guardias: ArrayList<PlanGuardiaObrera>
    private lateinit var adapter: RecyclerSearchAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar2.setTitle("Guardia Obrera")
        setSupportActionBar(binding.toolbar2)



        dao= AppDatabase.getInstance(this).Dao()
        CalgarPlanes()

        binding.recyclerViewSearch.layoutManager= LinearLayoutManager(this)
        adapter=RecyclerSearchAdapter(guardias){
            Toast.makeText(this,it.listaObreros,Toast.LENGTH_LONG).show()
        }
        binding.recyclerViewSearch.adapter= adapter

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.searchmenu,menu)
        val item = menu?.findItem(R.id.buscarserachbar)
       val searchView: SearchView = item?.actionView as SearchView
        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.add-> Toast.makeText(this, "it Works", Toast.LENGTH_SHORT).show()
            R.id.buscarserachbar-> Toast.makeText(this, "it Works", Toast.LENGTH_SHORT).show()
        }

        return true
        }


    fun CalgarPlanes(){
        guardias= ArrayList(dao.getPlanes())

    }

}


