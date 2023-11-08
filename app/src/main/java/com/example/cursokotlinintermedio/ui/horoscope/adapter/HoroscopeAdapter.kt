package com.example.cursokotlinintermedio.ui.horoscope.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cursokotlinintermedio.R
import com.example.cursokotlinintermedio.domain.model.HoroscopeInfo

class HoroscopeAdapter(private var horoscopeList:List<HoroscopeInfo> = emptyList(),
    private val onItemSelected:(HoroscopeInfo) ->Unit
    ) : RecyclerView.Adapter<HoroscopeViewHolder>() {
    override fun getItemCount() = horoscopeList.size;

    //crea el viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        //Crea la intancia de view holder, mismo que pide una vista, para mostrar un item, el
        //recycle view creara item x item
        return HoroscopeViewHolder(
          LayoutInflater.from(parent.context).inflate(R.layout.item_horoscope, parent, false)
        );
    }

    //Indica que pintar al view Holder creado
    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        //sen encarga de decirle al view holder que pintar
        holder.render(horoscopeList[position], onItemSelected);//le pasamos un obj de la lista a la vez
    }

    fun updateList(list: List<HoroscopeInfo>){
        horoscopeList = list;
        notifyDataSetChanged();
        //solo se recomienda usar esta notificacion con cambio grandes en los datos
    }
}