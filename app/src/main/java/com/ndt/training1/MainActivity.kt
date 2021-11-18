package com.ndt.training1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayoutMediator
import com.ndt.training1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val titles = arrayOf("Song", "Album", "Artist")

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MusicPagerAdapter(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
        binding.vpMusic.adapter = adapter
        //This for ViewPager one
//        binding.tabMusic.setupWithViewPager(binding.vpMusic)
        TabLayoutMediator(binding.tabMusic, binding.vpMusic) { tab, position ->
            tab.text = titles[position]
        }.attach()
        binding.navMusic.setNavigationItemSelectedListener(this)
        binding.dotsIndicator.setViewPager2(binding.vpMusic)
//        binding.vpMusic.setPageTransformer(CubeOutScalingTransformation())
        binding.vpMusic.setPageTransformer(VerticalFlipTransformation())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            return if (binding.drawerMusic.isDrawerOpen(GravityCompat.START)) {
                binding.drawerMusic.closeDrawer(GravityCompat.START)
                true
            } else {
                binding.drawerMusic.openDrawer(GravityCompat.START)
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        binding.drawerMusic.closeDrawer(GravityCompat.START)
        return when (item.itemId) {
            R.id.mn_song -> {
                binding.vpMusic.setCurrentItem(0, true)
                true
            }
            R.id.mn_album -> {
                binding.vpMusic.setCurrentItem(1, true)
                true
            }
            R.id.mn_artist -> {
                binding.vpMusic.setCurrentItem(2, true)
                true
            }
            else -> true
        }
    }
}