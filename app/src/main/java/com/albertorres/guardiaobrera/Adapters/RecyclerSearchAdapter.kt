package com.albertorres.guardiaobrera.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.albertorres.guardiaobrera.POJO.PlanGuardiaObrera
import com.albertorres.guardiaobrera.R
import com.albertorres.guardiaobrera.databinding.ItemPlanguardiaBinding
import java.util.*
import kotlin.collections.ArrayList


class RecyclerSearchAdapter (private val guardias: ArrayList<PlanGuardiaObrera> = ArrayList(), private  var listener: (PlanGuardiaObrera)-> Unit)
    :RecyclerView.Adapter<RecyclerSearchAdapter.ViewHolder>(), Filterable  {


    private var guardiastodas = ArrayList<PlanGuardiaObrera>(guardias)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_planguardia,parent,false)
        return ViewHolder(view)
        }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = guardias[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {listener(item) }


    }

    override fun getItemCount(): Int {
        return guardias.size
    }



    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ItemPlanguardiaBinding.bind(view)



        fun bind(item:PlanGuardiaObrera){

            val c = item.fecha
            val dia= c.get(Calendar.DAY_OF_MONTH)
            val mes= c.getDisplayName(Calendar.MONTH,Calendar.LONG, Locale.getDefault())
            val ano= c.get(Calendar.YEAR)
            val itemFecha = " $dia de $mes del $ano"

            binding.textViewDate.text= itemFecha


        }
    }

    override fun getFilter(): Filter {
        return object: Filter(){

            override fun performFiltering(p0: CharSequence?): FilterResults {
                var aux = ArrayList<PlanGuardiaObrera>()
                if(p0.toString().isEmpty()){

                    aux.addAll(guardiastodas)

                }else{

                    for (plan in guardiastodas){
                        if( plan.listaObreros.toLowerCase().contains(p0.toString().toLowerCase())){
                            aux.add(plan)
                        }
                    }
                }

                val filterResults = FilterResults()
                filterResults.values= aux
                return filterResults
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                guardias.clear()
                guardias.addAll(p1?.values as Collection<PlanGuardiaObrera>)
                notifyDataSetChanged()
            }
        }
    }



}