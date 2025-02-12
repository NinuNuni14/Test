package com.example.test

import android.net.http.HttpException
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresExtension
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.test.databinding.ActivityMainBinding
import com.example.test.model.Joke
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        callAPI()

    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun callAPI() {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://official-joke-api.appspot.com/random_joke")
            .build()
        lifecycleScope.launch(Dispatchers.IO) {
            try{
                client.newCall(request).execute().use { response ->
                    if (response.isSuccessful) {
                        val responseBody = response.body?.string()
                        val gson = Gson()
                        val joke = gson.fromJson(responseBody, Joke::class.java)
                        println("Type = " + joke.type)
                        println("Setup = ${joke.setup}")
                        println("Punchline = ${joke.punchline}")
                        updateUI(joke)

                    } else {
                        println("Request failed: ${response.code}")
                    }
                }
            }
            catch (_:HttpException){

            }
    }}

    fun updateUI(joke: Joke){
        runOnUiThread {
            binding.tvA.text = joke.setup
            binding.tvB.text = joke.punchline
            binding.tvType.text = joke.type }

    }

}