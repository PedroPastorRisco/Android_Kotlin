package com.example.mdc

import android.graphics.Color
import android.os.Bundle
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.webkit.URLUtil
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.mdc.databinding.ActivityScrollingBinding
import com.google.android.material.bottomappbar.BottomAppBar

class ScrollingActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityScrollingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.fab.setOnClickListener(this)
        binding.bottomAppBar.setNavigationOnClickListener {
            Snackbar.make(binding.root, R.string.message_snackbar, Snackbar.LENGTH_SHORT)
                .setAnchorView(binding.fab)
                .show()
        }
        binding.contentActivity.btnSkip!!.setOnClickListener { binding.contentActivity.cardVBuy!!.visibility = View.GONE }
        binding.contentActivity.btnBuy!!.setOnClickListener {
            Snackbar.make(it,R.string.message_buy,Snackbar.LENGTH_SHORT)
                .setAnchorView(binding.fab)
                .setAction(R.string.action_snack) {
                    Toast.makeText(this, R.string.message_toast, Toast.LENGTH_SHORT).show()
                }
                .show()
        }
        loadImage()
        binding.contentActivity.checkBPasswordEnable!!.setOnClickListener {
            binding.contentActivity.editTPassword!!.isEnabled = !binding.contentActivity.editTPassword!!.isEnabled
        }
        binding.contentActivity.editTUrl!!.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            var error: String? = null
            val url = binding.contentActivity.editTUrl!!.text.toString()
            if(!hasFocus){
                if (url.isEmpty()){
                    error = getString(R.string.card_required)
                }else if(URLUtil.isValidUrl(url)){
                    loadImage(url)
                }else{
                    error = getString(R.string.invalid_url)
                }
                binding.contentActivity.tilUrl!!.error = error
            }
        }
        binding.contentActivity.toogleButton!!.addOnButtonCheckedListener { group, checkedId, isChecked ->
            binding.contentActivity.root.setBackgroundColor(
                when(checkedId){
                    R.id.btnRed -> Color.RED
                    R.id.btnBlue -> Color.BLUE
                    else -> Color.GREEN
                }
            )
        }
    }
    private fun loadImage(url: String = "http://1.bp.blogspot.com/-p5JKVzHrag0/U0VhwDoA53I/AAAAAAAAC7g/3wxZ3GEw9kE/s1600/patata+2.png"){
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .into(binding.contentActivity.imgCover!!)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(v: View?) {
        if(binding.bottomAppBar.fabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER){
            binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
        }else{
            binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
        }
    }
}