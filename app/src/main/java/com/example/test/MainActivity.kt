package com.example.test

import android.net.http.HttpException
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresExtension
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test.databinding.ActivityMainBinding
import com.example.test.model.Character
import com.example.test.model.DisneyCharacter
import com.example.test.model.Joke
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request

class MainActivity : AppCompatActivity(), CharacterAdapter.CharacterViewHolder.OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    var a : DisneyCharacter? = null
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        callAPI()





        // Set the adapter to the RecyclerView


    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun callAPI() {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://api.disneyapi.dev/character")
            .build()
        lifecycleScope.launch(Dispatchers.IO) {
            try{
                client.newCall(request).execute().use { response ->
                    if (response.isSuccessful) {
                        val responseBody = response.body?.string()
                        val gson = Gson()
                        val disney = gson.fromJson(responseBody, DisneyCharacter::class.java)
                        println(disney.info)
                        println(disney.data.size)
                        updateUI(disney)
                        a = disney

                    } else {
                        println("Request failed: ${response.code}")
                    }
                }
            }
            catch (_:HttpException){

            }
    }}

    fun updateUI(disney: DisneyCharacter) {
        runOnUiThread {
//            binding.tvA.text = joke.setup
//            binding.tvB.text = joke.punchline
//            binding.tvType.text = joke.type }
            binding.recycleView.layoutManager = LinearLayoutManager(this)
            val adapter = CharacterAdapter(disney.data,this)
            binding.recycleView.adapter = adapter
        }
    }

    override fun onItemClick(character: Character) {
       println( character)
    }

}