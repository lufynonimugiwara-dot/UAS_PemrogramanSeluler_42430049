package com.example.uas_pemrogramanseluler_42430049

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Mencari komponen item raket di activity_main.xml
        val itemRaketYonex = findViewById<LinearLayout>(R.id.item_raket_yonex)

        // 2. Logika ketika item raket diklik
        itemRaketYonex?.setOnClickListener {
            // Berpindah halaman dari MainActivity ke DetailActivity
            val intent = Intent(this, DetailActivity::class.java)

            // Menitipkan data spesifikasi raket untuk dibawa ke halaman detail
            intent.putExtra("NAMA_RAKET", "Yonex Astrox 99 Pro")
            intent.putExtra("SPEK_RAKET", "Berat: 4U (Avg. 83g)\nTension: 20-28 Lbs\nFlex: Stiff\nCocok untuk pemain menyerang.")
            intent.putExtra("GAMBAR_RAKET", R.drawable.yonex_astrox_99)

            startActivity(intent)
        }
    }
}