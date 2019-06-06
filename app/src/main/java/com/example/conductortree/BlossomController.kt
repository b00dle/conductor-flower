package com.example.conductortree

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import com.bluelinelabs.conductor.Controller
import com.example.conductortree.databinding.ControllerBlossomBinding

class BlossomController(var bundle: Bundle?): Controller() {

    constructor(blossomCount: Int) :
            this(bundleOf("blossomCount" to blossomCount))

    private lateinit var binding: ControllerBlossomBinding

    private var blossomCount = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.controller_blossom, container, false)

        blossomCount = bundle!!.getInt("blossomCount")

        binding.root.setOnClickListener{
            if(blossomCount <= 0) {
                router.popCurrentController()
            }
            else {
                blossomCount -= 1
                update()
            }
        }

        return binding.root
    }

    fun update() {
        binding.text.text = "Blossom count: $blossomCount"
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        update()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("blossomCount", blossomCount)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        blossomCount = savedInstanceState.getInt("blossomCount")
    }

    override fun handleBack(): Boolean {
        if(targetController != null) {
            val listener = targetController as BlossomListener
            listener.blossomCount(blossomCount)
        }
        return super.handleBack()
    }

    interface BlossomListener {
        fun blossomCount(count: Int)
    }
}