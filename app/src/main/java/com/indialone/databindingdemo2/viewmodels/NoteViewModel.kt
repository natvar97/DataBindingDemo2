package com.indialone.databindingdemo2.viewmodels

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.indialone.databindingdemo2.NoteItem

class NoteViewModel : ViewModel(), Observable {

    val isStringEmpty = MutableLiveData<Boolean>()

    @Bindable
    val inputTitle = MutableLiveData<String>()

    @Bindable
    val inputDescription = MutableLiveData<String>()

    val list = MutableLiveData<ArrayList<NoteItem>>()

    private val arrayList = ArrayList<NoteItem>()

    init {
        isStringEmpty.value = false
    }

    fun addData() {
        val title = inputTitle.value!!
        val description = inputDescription.value!!

        if (title.isEmpty() || description.isEmpty()) {
            isStringEmpty.value = true
        } else {
            inputTitle.value = ""
            inputDescription.value = ""
            val note = NoteItem(title, description)
            arrayList.add(note)
            list.value = arrayList
        }

    }

    fun clearData() {
        arrayList.clear()
        list.value = arrayList
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}