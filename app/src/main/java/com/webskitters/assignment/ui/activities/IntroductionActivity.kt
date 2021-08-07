package com.webskitters.assignment.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayoutMediator
import com.webskitters.assignment.R
import com.webskitters.assignment.databinding.ActivityIntroductionBinding

class IntroductionActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityIntroductionBinding = DataBindingUtil.setContentView(this@IntroductionActivity, R.layout.activity_introduction)
        binding.viewpager.adapter = IntroAdapter()

        TabLayoutMediator(binding.tabLayout,binding.viewpager){tab, position ->


        }.attach()


        binding.button5.setOnClickListener {
            binding.viewpager.currentItem=binding.viewpager.currentItem+1
        }
        binding.button6.setOnClickListener {
            startActivity(Intent(this,HomeActivity::class.java))
            finishAffinity()
        }

        binding.viewpager.registerOnPageChangeCallback(object : OnPageChangeCallback(){
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                Log.d("PageChangeCallback","$position and $positionOffset  and $positionOffsetPixels")
                if (position==3)
                {
                    binding.button5.visibility=View.GONE
                    binding.button6.visibility=View.VISIBLE
                }
                else{
                    binding.button6.visibility=View.GONE
                    binding.button5.visibility=View.VISIBLE
                }

                if (position==0)
                {
                    binding.button5.visibility=View.VISIBLE
                    binding.button6.visibility=View.GONE
                }


                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }


        })

    }
}