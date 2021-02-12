package com.sankalp.imageview

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    lateinit var imgView : ImageView
    lateinit var btnImage : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgView = findViewById(R.id.imageView)
        btnImage = findViewById(R.id.button_chooseImage)

        btnImage.setOnClickListener {
            val picImg = Intent(Intent.ACTION_GET_CONTENT)
            picImg.setType("image/")
            startActivityForResult(Intent.createChooser(picImg,"Pick an Image"),1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == 1){
            imgView = findViewById(R.id.imageView)
            val inputStream = data?.getData()?.let { getContentResolver().openInputStream(it) }
            val bitmap = BitmapFactory.decodeStream(inputStream)
            imgView.setImageBitmap(bitmap)

        }
    }
}