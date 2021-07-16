package com.indialone.databindingdemo2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.indialone.databindingdemo2.databinding.ActivityMainBinding
import com.indialone.databindingdemo2.viewmodels.NoteViewModel
import com.indialone.databindingdemo2.viewmodels.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private val noteViewModel: NoteViewModel by lazy {
        ViewModelProvider(this, ViewModelFactory()).get(NoteViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        mBinding.noteViewModel = noteViewModel
        mBinding.lifecycleOwner = this
        noteViewModel.list.observe(this) { list ->
            for (item in list) {
                mBinding.content.text = "Title: ${item.title}\nDescription: ${item.description}"
            }
        }

        noteViewModel.isStringEmpty.observe(this) {
            if (it == true) {
                Toast.makeText(this, "Please provide all details", Toast.LENGTH_SHORT).show()
            }
        }

    }
}