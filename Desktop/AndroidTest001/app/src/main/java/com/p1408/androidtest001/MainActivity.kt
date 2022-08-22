package com.p1408.androidtest001

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.p1408.androidtest001.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val hola = challenge02("promo", "mopra")
        if (hola) {
            println("Son iguales")
        }else{
            println("Son diferentes")
        }
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
    }

    private fun challenge01(){
        for (i in 1..100) {
            var firstText = ""
            if (i%3 == 0) {
                "fizz".also { firstText = it }
            }
            if (i%5 == 0) {
                (firstText + "buzz").also { firstText = it }
            }
            if (firstText == "") "$i".also { firstText = it }
            println(firstText)
        }
    }

    private fun challenge02(word1: String, word2: String): Boolean{

        val arrayWord1: Array<String> = word1.toCharArray().map { it.toString() }.toTypedArray()
        val arrayWord2: Array<String> = word2.toCharArray().map { it.toString() }.toTypedArray()
        var arrayBolean = BooleanArray(word1.length) {false}
        var number: Int = 0
        if ((word1.length != word2.length) || word1.equals(word2) ){
            return  false
        }
        for (w1 in arrayWord1){
            for (w2 in arrayWord2){
                if (w1 == w2){
                    arrayBolean[number] = true
                }
            }
            number = number + 1
        }
        for (condition in arrayBolean) {
            if (!condition) {
                return false
            }
        }
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
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

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}