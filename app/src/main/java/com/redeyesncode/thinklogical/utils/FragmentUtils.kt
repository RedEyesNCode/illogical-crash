package com.redeyesncode.thinklogical.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.redeyesncode.thinklogical.R

class FragmentUtils {
    fun addFragmentBackStack(
        fragmentManager: FragmentManager,
        fragment: Fragment?,
        tag: String?,
        isAddToBackStack: Boolean
    ) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.mainContainer, fragment!!)
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(null)
        }
        fragmentTransaction.commit()
    }

    fun replaceFragmentBackStack(
        fragmentManager: FragmentManager,
        fragment: Fragment?,
        tag: String?,
        isAddToBackStack: Boolean
    ) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.mainContainer, fragment!!)
        if (isAddToBackStack) {
            fragmentTransaction.addToBackStack(tag)
        }
        fragmentTransaction.commit()
    }

}