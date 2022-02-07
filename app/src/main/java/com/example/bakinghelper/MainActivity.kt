package com.example.bakinghelper

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.bakinghelper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    private var ft : FragmentTransaction = supportFragmentManager.beginTransaction()

    var timer = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        ft.replace(R.id.frameLayout, match(), "match").commit()
        ft = supportFragmentManager.beginTransaction()
        ft.addToBackStack(null).commit()
        ft = supportFragmentManager.beginTransaction()

        binding.btnMatch.setOnClickListener{
            setMatch()
        }
        binding.btnTimer.setOnClickListener{
            setTimer()
        }
    }

    private fun setMatch(){
        ft.replace(R.id.frameLayout, match(), "match").commit()
        ft = supportFragmentManager.beginTransaction()
        ft.addToBackStack(null).commit()
        ft = supportFragmentManager.beginTransaction()
    }

    private fun setTimer(){
        ft.replace(R.id.frameLayout, timer(), "timer").commit()
        ft = supportFragmentManager.beginTransaction()
        ft.addToBackStack(null).commit()
        ft = supportFragmentManager.beginTransaction()
    }
}