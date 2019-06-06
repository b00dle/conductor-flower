package com.example.conductortree

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.example.conductortree.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var router: Router

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        router = Conductor.attachRouter(this, binding.changeHandlerFrameLayout, savedInstanceState)
        if(!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(HomeController()))
        }
    }

    override fun onBackPressed() {
        if(!router.handleBack()) {
            super.onBackPressed()
        }
    }
}
