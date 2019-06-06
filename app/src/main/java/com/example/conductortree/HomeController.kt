package com.example.conductortree

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction
import com.example.conductortree.databinding.ControllerHomeBinding

class HomeController: Controller(), BlossomController.BlossomListener {

    private lateinit var binding: ControllerHomeBinding

    private var showFlower = false

    private var blossomCount = 32

    private var maxBlossomCount = 32

    override fun blossomCount(count: Int) {
        blossomCount = count
        if(blossomCount <= 0) {
            blossomCount = maxBlossomCount
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.controller_home, container, false)

        binding.root.setOnClickListener{
            if(showFlower) {
                val blossomController = BlossomController(blossomCount)
                blossomController.targetController = this
                router.pushController(RouterTransaction.with(blossomController))
            }
            else {
                showFlower = true
                update()
            }
        }

        return binding.root
    }

    private fun update() {
        binding.flower.apply {
            if(showFlower) {
                visibility = View.VISIBLE
            }
            else {
                visibility = View.GONE
            }
        }
        binding.text.apply {
            if(showFlower) {
                visibility = View.GONE
            }
            else {
                visibility = View.VISIBLE
            }
        }
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        update()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("showFlower", showFlower)
        d("b00dle", "saving instance state $showFlower")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        showFlower = savedInstanceState.getBoolean("showFlower")
        d("b00dle", "restoring instance state $showFlower")
    }
}