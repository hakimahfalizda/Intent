package com.f.myintent

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_implisit_intent.*

class ImplisitIntentActivity : AppCompatActivity() {
    private val TAKE_PICTURE = 1
    private val imageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_implisit_intent)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            TAKE_PICTURE -> if (resultCode == Activity.RESULT_OK) {
                val selectedImage = imageUri
                contentResolver.notifyChange(selectedImage,
                    null)
                val cr = contentResolver
                val bitmap: Bitmap
                try {
                    bitmap = MediaStore.Images.Media
                        .getBitmap(cr, selectedImage)

                    iv.setImageBitmap(bitmap)
                    Toast.makeText(
                        this, selectedImage.
                            toString(), Toast.LENGTH_LONG
                    ).show()
                } catch (e: Exception) {
                    Toast.makeText(this, "Failed to load",
                        Toast.LENGTH_SHORT)
                        .show()
                    Log.e("Camera", e.toString())
                }
            }
        }
    }

}


