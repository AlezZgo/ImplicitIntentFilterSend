package com.example.implicitintentfiltersend

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException


class ShareActivity : AppCompatActivity() {

    private lateinit var imageView : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share)

        imageView = findViewById<ImageView>(R.id.imageView)

        val intent = intent
        intent.type = "image/*"
        val action = intent.action
        val type = intent.type

        if(Intent.ACTION_SEND.equals(action) && type != null){
            handleIncomingData(intent);
        }
    }

    fun handleIncomingData(data: Intent) {
        val imageSelected: Uri? = data.getParcelableExtra(Intent.EXTRA_STREAM)
        try {
            val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageSelected)
            imageView.setImageBitmap(bitmap)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }


}