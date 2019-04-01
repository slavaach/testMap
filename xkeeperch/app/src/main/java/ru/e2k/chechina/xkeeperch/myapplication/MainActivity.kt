package ru.e2k.chechina.xkeeperch.myapplication

import android.content.Context
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
import android.content.SharedPreferences
import com.yandex.runtime.image.ImageProvider






class MainActivity : AppCompatActivity() {

    private val MAPKIT_API_KEY = "e534529d-f0cb-4336-a8bd-e190337f708e"
    private val TARGET_LOCATION = Point(55.771218, 36.781363)

    private var mapView: MapView? = null
    private val BASE_URL = "https://jsonplaceholder.typicode.com"
    private var menu: Menu? = null
    private var toolbar: Toolbar? = null
    var settings: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        val sp = getSharedPreferences("my_settings",
                Context.MODE_PRIVATE)
        // проверяем, первый ли раз открывается программа
        val hasVisited = sp.getBoolean("hasVisited", false)

        if (!hasVisited) {

            val e = sp.edit()
            e.putBoolean("hasVisited", true)
            e.commit()

            val intent_home = Intent(this, PasswordActivity::class.java)
            startActivity(intent_home)
            this.finish()

        }
        MapKitFactory.setApiKey(MAPKIT_API_KEY)
        MapKitFactory.initialize(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // верхний тулбар
        toolbar = findViewById<Toolbar>(R.id.toolbar) as Toolbar
        //my_toolbar.setTitle("Проба")
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)

       mapView = findViewById(R.id.mapview) as MapView


        // And to show what can be done with it, we move the camera to the center of Saint Petersburg.

        mapView!!.getMap().move(
                CameraPosition(TARGET_LOCATION, 14.0f, 0.0f, 0.0f),
                Animation(Animation.Type.SMOOTH, 5.0f ),
                null)


         val point = Point(TARGET_LOCATION.getLatitude(), TARGET_LOCATION.getLongitude())


        val placemarcs =  mapView!!.getMap().mapObjects
                .addPlacemark(point
                        , ImageProvider.fromResource(this, R.drawable.baseline_clear_black_24))
        //mapView!!.getMap().mapObjects.add()

        NetworkService.instance
                .jsonApi
                .getAllPosts()
                .enqueue(object : Callback<List<Post>> {
                    override fun onResponse(call: Call<List<Post>>?, response: Response<List<Post>>?) {
                        val listPost:List<Post>? = response!!.body()
                        for(post in listPost!!)  MapsPost.instance.add(post);
                    }
                    override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                        t.printStackTrace()
                    }
                })




    }

    protected override fun onStop() {
        super.onStop()
        mapView!!.onStop()
        MapKitFactory.getInstance().onStop()
    }

    override fun onStart() {
        super.onStart()
        mapView!!.onStart()
        MapKitFactory.getInstance().onStart()
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
                return true
            }

            R.id.listss -> {
                val intent_home = Intent(this, TableActivity::class.java)
                startActivity(intent_home)
                this.finish()
                return true
            }
            R.id.exit -> {
                val settings = getSharedPreferences("my_settings", Context.MODE_PRIVATE)
                val e = settings.edit()
                e.remove("token")
                e.remove("hasVisited")
                e.commit()
                this.finish()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }

    }

}
