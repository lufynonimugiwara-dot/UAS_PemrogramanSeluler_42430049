package com.example.uas_pemrogramanseluler_42430049

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // 1. Hubungkan variabel dengan ID yang ada di activity_detail.xml
        val tvNama = findViewById<TextView>(R.id.tv_nama_raket)
        val tvSpek = findViewById<TextView>(R.id.tv_spek_raket)
        val ivFoto = findViewById<ImageView>(R.id.iv_foto_raket)
        val btnBack = findViewById<Button>(R.id.btn_back)

        // 2. Tangkap data yang dikirim oleh MainActivity tadi
        val namaRaket = intent.getStringExtra("NAMA_RAKET")
        val spekRaket = intent.getStringExtra("SPEK_RAKET")
        val gambarRaket = intent.getIntExtra("GAMBAR_RAKET", 0)

        // 3. Tampilkan data ke komponen Layout
        tvNama.text = namaRaket
        tvSpek.text = spekRaket
        ivFoto.setImageResource(gambarRaket)

        // 4. Fungsi tombol kembali
        btnBack.setOnClickListener {
            finish()
        }
         }
}