package ru.e2k.chechina.xkeeperch.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.GridView
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.mapview.MapView
import java.util.ArrayList
import android.content.SharedPreferences




class PasswordActivity : AppCompatActivity() {

     private var menu: Menu? = null
    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_password)
        (findViewById(R.id.send_button) as Button).setOnClickListener(this::send)

    }

    fun send(v:View){

        val settings = getSharedPreferences("my_settings", Context.MODE_PRIVATE)
        val prefEditor = settings.edit()
        prefEditor.putString("token", (findViewById(R.id.user_input_text) as EditText).text.toString());
        prefEditor.apply();
         val e = settings.edit()
        e.putBoolean("hasVisited", true)
        e.commit()

        val intent_home = Intent(this, MainActivity::class.java)
        startActivity(intent_home)
        this.finish()

    }

}