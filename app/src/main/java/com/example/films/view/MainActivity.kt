package com.example.films.view

import android.os.Bundle
import android.window.OnBackInvokedCallback
import android.window.OnBackInvokedDispatcher
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.replace
import com.example.films.R
import com.example.films.databinding.MainActivityBinding
import java.util.Objects

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        this.supportActionBar?.hide()

        onBackPressedDispatcher.addCallback(onBackPressedCallback)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,ListFilmsFragment::class.java,null)
            .commit()
    }


    public val onBackPressedCallback = object: OnBackPressedCallback(true){

        override fun handleOnBackPressed() {
            if(supportFragmentManager.backStackEntryCount > 0){
                supportFragmentManager.popBackStack()
            }else{
                finishAffinity()
            }
        }

    }
}
