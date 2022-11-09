package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class SharedViewModel : ViewModel() {
    val shoeName = MutableLiveData<String>()
    val shoeSize = MutableLiveData<String>()
    val shoeCompany = MutableLiveData<String>()
    val shoeDescription = MutableLiveData<String>()


    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
            get() = _shoeList

    fun allFieldsNotEmpty() : Boolean{
        return fieldNotEmpty(shoeName) &&
                fieldNotEmpty(shoeSize) &&
                fieldNotEmpty(shoeCompany) &&
                fieldNotEmpty(shoeDescription)
    }

    private fun fieldNotEmpty(data: MutableLiveData<String>) : Boolean{
        return !data.value.isNullOrEmpty()
    }

    fun shoeListEmpty() : Boolean{
        return _shoeList.value == null
    }

    fun buildShoeComplete(){
        shoeName.value = ""
        shoeSize.value = ""
        shoeCompany.value = ""
        shoeDescription.value = ""
    }

    fun buildShoe(){
         val shoe = Shoe(shoeName.value?: "",
                     shoeSize.value?.toDouble()?: 0.0,
                     shoeCompany.value?: "",
                     shoeDescription.value?: "")

        if (_shoeList.value == null) {
            _shoeList.value = mutableListOf(shoe)
        } else {
            _shoeList.value?.add(shoe)
        }
    }
}