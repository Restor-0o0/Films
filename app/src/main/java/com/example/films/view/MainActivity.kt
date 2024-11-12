package com.example.films.view

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity

import com.example.films.R


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        window.statusBarColor = getColor(R.color.title)
        this.supportActionBar?.hide()
        if(supportFragmentManager.backStackEntryCount > 0){

        }else{
            onBackPressedDispatcher.addCallback(onBackPressedCallback)
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container,ListFilmsFragment::class.java,null)
                .commit()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
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
