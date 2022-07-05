package com.redeyesncode.thinklogical.onboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.redeyesncode.thinklogical.R
import com.redeyesncode.thinklogical.auth.LoginActivity
import com.redeyesncode.thinklogical.databinding.ActivityIntroBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityIntroBinding
    private var layouts: IntArray = intArrayOf( R.layout.intro_one_layout,
        R.layout.intro_one_layout,
        R.layout.intro_one_layout,
        R.layout.intro_one_layout)
    private var dots: Array<ImageView?> = arrayOfNulls<ImageView>(layouts.size)
    private var myViewPagerAdapter: MyViewPagerAdapter = MyViewPagerAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.pager.adapter =myViewPagerAdapter
        binding.pager.addOnPageChangeListener(viewPageChangeListener)

        // layouts of all welcome sliders

        binding.btnGetStarted.setOnClickListener {
            var intentLogin = Intent(this@MainActivity,LoginActivity::class.java)
            startActivity(intentLogin)
            finish()

        }
        addBottomDots(0)


    }
    private fun addBottomDots(currentPage: Int) {

        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.gravity = Gravity.CENTER_VERTICAL
        layoutParams.setMargins(2, 0, 2, 0)
        binding.layoutDots.removeAllViews()
        for (i in dots.indices) {
            dots[i] = ImageView(this)
            dots[i]!!.setBackground(resources.getDrawable(R.drawable.dot_in_active))
            dots[i]!!.setScaleX(0.8.toFloat())
            dots[i]!!.setScaleY(0.8.toFloat())
            dots[i]!!.setLayoutParams(layoutParams)
            binding.layoutDots.addView(dots[i])
        }
        if (dots.size > 0) dots[currentPage]!!.setBackground(resources.getDrawable(R.drawable.dot_active))
    }
    var viewPageChangeListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            addBottomDots(position)
            if (position == 3) {
                binding.btnGetStarted.text = "Login >"
            } else {
                binding.btnGetStarted.text = "Skip"
            }
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }
    /**
     * View pager adapter
     */
    inner class MyViewPagerAdapter : PagerAdapter() {
        private var layoutInflater: LayoutInflater? = null
        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            layoutInflater = this@MainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater?
            val view: View = layoutInflater!!.inflate(layouts.get(position), container, false)
            container.addView(view)
            return view
        }

        override fun getCount(): Int {
            return layouts.size
        }

        override fun isViewFromObject(view: View, obj: Any): Boolean {
            return view === obj
        }

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            val view = `object` as View
            container.removeView(view)
        }
    }
}