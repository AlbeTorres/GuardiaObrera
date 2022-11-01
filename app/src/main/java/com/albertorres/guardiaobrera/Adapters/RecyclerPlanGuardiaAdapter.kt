package com.albertorres.guardiaobrera.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.albertorres.guardiaobrera.POJO.PlanGuardiaObrera
import com.albertorres.guardiaobrera.R
import com.albertorres.guardiaobrera.databinding.ItemPlanguardiaBinding
import java.util.*
import kotlin.collections.ArrayList

class RecyclerPlanGuardiaAdapter(private val guardias:
                                 ArrayList<PlanGuardiaObrera> = ArrayList()):RecyclerView.Adapter<RecyclerPlanGuardiaAdapter.GuardiasViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuardiasViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.item_planguardia,parent,false)
        return GuardiasViewHolder(view)

    }

    override fun getItemCount(): Int {
       return guardias.size
    }

    override fun onBindViewHolder(holder: GuardiasViewHolder, position: Int) {

        val c = guardias[position].fecha
        val dia= c.get(Calendar.DAY_OF_MONTH)
        val mes= c.getDisplayName(Calendar.MONTH,Calendar.LONG, Locale.getDefault())
        val ano= c.get(Calendar.YEAR)
        val itemFecha = " $dia de $mes del $ano"
        holder.bind(itemFecha)

    }


    class GuardiasViewHolder(view: View):RecyclerView.ViewHolder(view){
        val binding = ItemPlanguardiaBinding.bind(view)


        fun bind(fecha:String){

           binding.textViewDate.text= fecha

        }



    }
}





