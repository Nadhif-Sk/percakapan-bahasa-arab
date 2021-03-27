package com.example.percakapanbahasaarab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_menu_percakapan.*

class Menu_percakapan : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_percakapan)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN )

        btn_kebangsaan.setOnClickListener{
            val go = Intent(this@Menu_percakapan, PercakapanKebangsaan::class.java)
            startActivity(go)
        }

        btn_perkenalan.setOnClickListener{
            val go = Intent(this@Menu_percakapan, PercakapanPerkenalan::class.java)
            startActivity(go)
        }

        btn_keluarga.setOnClickListener{
            val go = Intent(this@Menu_percakapan, PercakapanKeluarga::class.java)
            startActivity(go)
        }

        btn_profesi.setOnClickListener{
            val go = Intent(this@Menu_percakapan, PercakapanProfesi::class.java)
            startActivity(go)
        }

        btn_sholat.setOnClickListener{
            val go = Intent(this@Menu_percakapan, PercakapanSholat::class.java)
            startActivity(go)
        }
    }
}