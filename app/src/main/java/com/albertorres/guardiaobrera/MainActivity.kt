package com.albertorres.guardiaobrera


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.albertorres.guardiaobrera.Adapters.RecyclerPlanGuardiaAdapter
import com.albertorres.guardiaobrera.Database.AppDatabase
import com.albertorres.guardiaobrera.Database.Dao
import com.albertorres.guardiaobrera.POJO.PlanGuardiaObrera
import com.albertorres.guardiaobrera.TrabajarExels.TrabajarExel
import com.albertorres.guardiaobrera.databinding.ActivityMainBinding
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var guardias: ArrayList<PlanGuardiaObrera>
    private lateinit var dao:Dao
    private val trabajarexel = TrabajarExel(this)



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var  c: Calendar = Calendar.getInstance()
        val toolbar =binding.toolbar
        binding.toolbar.setTitle("Guardia Obrera")
        setSupportActionBar(toolbar)

        dao= AppDatabase.getInstance(this).Dao()

        CalgarPlanes(c)

        binding.planguardiaRecycler.adapter= RecyclerPlanGuardiaAdapter(guardias)
        binding.planguardiaRecycler.layoutManager = LinearLayoutManager(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbarmenu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent= Intent(this,MainActivity2::class.java)
        when(item.itemId){
            R.id.exel-> Toast.makeText(this, trabajarexel.GuardarExel(), Toast.LENGTH_SHORT).show()
            R.id.buscar-> startActivityForResult(intent, Activity.RESULT_OK)

        }



        return true
    }

    fun CalgarPlanes(c:Calendar){

        dao.InsertarPlanGuardiaObrera(PlanGuardiaObrera(1,"Kanolo, Elin, caka",c,"Sede"))
        dao.InsertarPlanGuardiaObrera(PlanGuardiaObrera(2,"Miguel, Elin, Juana",c,"Sede"))
        dao.InsertarPlanGuardiaObrera(PlanGuardiaObrera(3,"Idilia, Elin, ernesto",c,"Sede"))


        guardias= ArrayList(dao.getPlanes())

    }




}