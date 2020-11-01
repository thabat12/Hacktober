package com.example.hacktober.activities

import MyAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import com.example.hacktober.R
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pin.*

class MainActivity : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    var isOpen = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "KotlinApp"
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        tabLayout.addTab(tabLayout.newTab().setText("Private"))
        tabLayout.addTab(tabLayout.newTab().setText("Conversations"))

        tabLayout.tabGravity = TabLayout.GRAVITY_FILL
        val adapter = MyAdapter(this, supportFragmentManager,
            tabLayout.tabCount)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        val fab: View = findViewById(R.id.fab)


        val fabOpen = AnimationUtils.loadAnimation(this,R.anim.rotate_open_anim)
        val fabClose = AnimationUtils.loadAnimation(this,R.anim.rotate_close_anim)
        val fabRClockwise = AnimationUtils.loadAnimation(this,R.anim.from_bottom_anim)
        val fabRAntiClockwise = AnimationUtils.loadAnimation(this,R.anim.to_botton_anim)
        fab.setOnClickListener { view ->
            if(isOpen)
            {
                fab.startAnimation(fabClose)
                camera.startAnimation(fabClose)
                camera.visibility = View.INVISIBLE
                new_album.startAnimation(fabClose)
                new_album.visibility = View.INVISIBLE
                photos.startAnimation(fabClose)
                photos.visibility = View.INVISIBLE
                fab.startAnimation(fabRAntiClockwise)
                isOpen = false
            }
            else
            {
                fab.startAnimation(fabOpen)
                camera.startAnimation(fabOpen)
                new_album.startAnimation(fabOpen)
                photos.startAnimation(fabOpen)
                fab.startAnimation(fabRClockwise)
                fab.isClickable
                camera.isClickable
                new_album.isClickable
                photos.isClickable
                isOpen = true
            }


        }


    }
}