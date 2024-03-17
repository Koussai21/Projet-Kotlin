package com.example.testform

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DisplayInformation : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.information_display)

        // Retrieve data passed from previous activity
        val voyage = intent.getParcelableExtra<Voyage>("voyage")

        // Display data in the views
        findViewById<TextView>(R.id.textViewPays).text = "Pays: ${voyage?.pays}"
        findViewById<TextView>(R.id.textViewVilles).text = "Ville: ${voyage?.ville}"
        findViewById<TextView>(R.id.textViewMonuments).text = "Monuments notables: ${voyage?.monuments}"
        findViewById<TextView>(R.id.textViewAvis).text = "Avis: ${voyage?.avis}"
        voyage?.imageUri?.let {
            findViewById<ImageView>(R.id.imageView).setImageURI(Uri.parse(it))
        }
}