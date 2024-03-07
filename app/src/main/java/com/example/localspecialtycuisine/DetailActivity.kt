package com.example.localspecialtycuisine

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity: AppCompatActivity(), View.OnClickListener {
    private lateinit var btnShare: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val photo: ImageView = findViewById(R.id.rv_image)
        val name: TextView = findViewById(R.id.tv_data_name)
        val description: TextView = findViewById(R.id.tv_data_description)

        val foodPhoto = intent.getIntExtra("food_photo", 0)
        val foodName = intent.getStringExtra("food_name")
        val foodDescription = intent.getIntExtra("food_description", 0)

        photo.setImageResource(foodPhoto)
        name.text = foodName
        description.text = resources.getStringArray(R.array.detail_description)[foodDescription]

        btnShare = findViewById(R.id.action_share)
        btnShare.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.action_share -> {
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT,"Food Name: ${intent.getStringExtra("food_name")}\nDescription: ${resources.getStringArray(R.array.detail_description)[intent.getIntExtra("food_description", 0)]}")
                    type = "text/plain"
                }
                startActivity(Intent.createChooser(shareIntent, "Share"))
            }
        }
    }
}