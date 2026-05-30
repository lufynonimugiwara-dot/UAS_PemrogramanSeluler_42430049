package com.example.uas_pemrogramanseluler_42430049

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail) // Pastikan nama layout detail kamu benar

        // 1. Inisialisasi komponen UI dari activity_detail.xml
        val tvNama = findViewById<TextView>(R.id.tv_nama_raket)

        val tvSpek = findViewById<TextView>(R.id.tv_spek_raket)

        val ivFoto = findViewById<ImageView>(R.id.iv_foto_raket)

        val btnBack = findViewById<Button>(R.id.btn_back)
        // 2. Menerima data yang dititipkan dari MainActivity
        val namaRaket = intent.getStringExtra("NAMA_RAKET")
        val spekRaket = intent.getStringExtra("SPEK_RAKET")
        // Menerima data gambar berupa ID Resource (Int). Jika tidak ada, default ke gambar gallery bawaan
        val gambarRaket = intent.getIntExtra("GAMBAR_RAKET", android.R.drawable.ic_menu_gallery)

        // 3. Menampilkan data ke komponen UI halaman detail
        tvNama.text = namaRaket
        tvSpek.text = spekRaket
        ivFoto.setImageResource(gambarRaket) // <-- Baris krusial ini yang akan memunculkan gambar raket asli kamu!

        // 4. Logika tombol kembali ke halaman utama
        btnBack.setOnClickListener {
            finish() // Menutup DetailActivity dan otomatis kembali ke MainActivity
        }
    }
}