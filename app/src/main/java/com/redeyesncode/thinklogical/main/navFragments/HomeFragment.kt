package com.redeyesncode.thinklogical.main.navFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.redeyesncode.thinklogical.R
import com.redeyesncode.thinklogical.databinding.FragmentHomeBinding
import com.redeyesncode.thinklogical.main.models.SampleDataClass
import com.redeyesncode.thinklogical.main.navFragments.adapter.CRUDAdapter
import java.lang.IndexOutOfBoundsException

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
class HomeFragment : Fragment() , CRUDAdapter.onClick{
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding:FragmentHomeBinding
    private var sampleDataClass:ArrayList<SampleDataClass> = ArrayList()
    private lateinit var crudAdapter: CRUDAdapter
    private var isAllFabVisible:Boolean = false


    override fun onDataDelete(position: Int) {
        try {
            sampleDataClass.removeAt(position)
            crudAdapter.notifyItemRemoved(position)
            updateCardList(true)
        }catch (e:IndexOutOfBoundsException){
            Toast.makeText(requireContext(),"Index out of bounds exception",Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        initRecycler()
        initClicks()


        return binding.root
    }

    private fun initRecycler() {
        for (i in 1..10){
            sampleDataClass.add(SampleDataClass("Data number $i",false))

        }
        crudAdapter = CRUDAdapter(requireContext(),sampleDataClass,this)
        binding.recvCards.adapter = crudAdapter
        binding.recvCards.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

    }

    private fun initClicks() {
        binding.fabProductListing.setOnClickListener { v -> addRandomData() }
        binding.btnAddCard.setOnClickListener { v -> addRandomData() }
        binding.efabCard.setOnClickListener { v ->
            updateCardList(false)
            // this is not needed by the way
            if (!isAllFabVisible) {
                showHideFab(true)
            } else {
                showHideFab(false)
            }
        }
        binding.fabAddCard.setOnClickListener { v -> addRandomData() }
        binding.fabDeleteCard.setOnClickListener { v ->
            updateCardList(true)
        }
    }

    private fun addRandomData() {
        sampleDataClass.add(SampleDataClass("Data number : ${sampleDataClass.size+1}",false))
        crudAdapter.notifyDataSetChanged()
    }

    private fun updateCardList(isEditable: Boolean) {
        for (i in sampleDataClass.indices) {
            sampleDataClass.get(i).isEditable = isEditable
        }
        crudAdapter.notifyDataSetChanged()
    }
    private fun showHideFab(isShow: Boolean) {
        if (isShow) {
            binding.addFablayout.visibility = View.VISIBLE
            binding.fabAddCard.show()
            binding.deleteFabLayout.visibility = View.VISIBLE
            binding.fabDeleteCard.show()
            isAllFabVisible = true
            binding.efabCard.extend()
            binding.efabCard.setIcon(resources.getDrawable(R.drawable.ic_back))
        } else {
            binding.addFablayout.visibility = View.GONE
            binding.fabAddCard.hide()
            binding.deleteFabLayout.visibility = View.GONE
            binding.fabDeleteCard.hide()
            updateCardList(false)
            isAllFabVisible = false
            binding.efabCard.shrink()
            binding.efabCard.setIcon(resources.getDrawable(R.drawable.ic_edit_pencil))
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}