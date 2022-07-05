package com.redeyesncode.thinklogical.main.navFragments.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.redeyesncode.thinklogical.databinding.SampleListBinding
import com.redeyesncode.thinklogical.main.models.SampleDataClass

class CRUDAdapter(var context: Context, var list:ArrayList<SampleDataClass>, var onClicks:CRUDAdapter.onClick) :RecyclerView.Adapter<CRUDAdapter.MyViewHolder>(){
    private lateinit var listBinding: SampleListBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        listBinding = SampleListBinding.inflate(LayoutInflater.from(context))
        return MyViewHolder(listBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var sampleDataClass = list.get(position)
        with(holder){
            if(sampleDataClass.isEditable){
                binding.btnDeleteCard.visibility = View.VISIBLE
            }
            binding.btnDeleteCard.setOnClickListener {
                onClicks.onDataDelete(position)
            }
            binding.tvPaymentMethod.text = sampleDataClass.string
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(var binding:SampleListBinding):RecyclerView.ViewHolder(binding.root)
    interface onClick{
        fun onDataDelete(position: Int)
    }
}