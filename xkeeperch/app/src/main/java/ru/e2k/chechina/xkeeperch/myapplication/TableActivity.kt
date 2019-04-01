package ru.e2k.chechina.xkeeperch.myapplication


import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import android.widget.ArrayAdapter
import android.widget.GridView
import java.util.ArrayList


class TableActivity : AppCompatActivity() {


    private var menu: Menu? = null
    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.table_main)


        // верхний тулбар
        toolbar = findViewById<Toolbar>(R.id.toolbar) as Toolbar
        //my_toolbar.setTitle("Проба")
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)


       // получаем элемент GridView
        val countriesList = findViewById<View>(R.id.gridview) as GridView
        var listGPS : MutableList<String> = ArrayList()

        for(post in MapsPost.instance.gsmPoints){
            listGPS.add(post.id.toString())
            listGPS.add(post.userId.toString())
            listGPS.add(post.title.toString())
        }


         // создаем адаптер
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listGPS)
        countriesList.adapter = adapter


    }



    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        this.menu = menu

        return true
    }

    override//описание меню
    fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId


        when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                //тут выход из приложения
                return true
            }
            R.id.mapss -> {
                val intent_home = Intent(this, MainActivity::class.java)
                startActivity(intent_home)
                this.finish()
                //тут выход из приложения
                return true
            }

            R.id.listss -> {
                //тип товаров

                return true
            }
            R.id.exit -> {
                //всегда надо
                this.finish()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }

    }

}