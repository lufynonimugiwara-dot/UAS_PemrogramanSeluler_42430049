package com.example.uas_pemrogramanseluler_42430049

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Locale

class MainActivity : AppCompatActivity() {

    // Menyimpan data asli dan data hasil filter pencarian
    private val daftarRaketAsli = arrayListOf("Yonex Astrox 99 Pro", "Li-Ning Aeronaut 9000", "Victor Thruster F")
    private val spekAsli = arrayListOf(
        "Berat: 4U (Avg. 83g)\nTension: 20-28 Lbs\nFlex: Stiff\nCocok untuk pemain menyerang.",
        "Berat: 3U (Avg. 86g)\nTension: Up to 32 Lbs\nFlex: Flexible\nCocok untuk kontrol dan power.",
        "Berat: 4U\nTension: 20-26 Lbs\nFlex: Medium\nRaket andalan bertipe serang dan cepat."
    )
    private val gambarAsli = arrayListOf(
        R.drawable.yonex_astrox_99,
        R.drawable.lining_aeronaut_9000,
        R.drawable.victor_thruster_f
    )

    private val daftarTampil = ArrayList<String>()
    private val spekTampil = ArrayList<String>()
    private val gambarTampil = ArrayList<Int>()

    private lateinit var customAdapter: RaketAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        android.util.Log.d("UAS_NIM", "Aplikasi Katalog Raket - Yohana Imanuel - 42430049")
        // Salin data awal ke daftar yang akan ditampilkan di screen
        ResetDataTampilan()

        // Inisialisasi Komponen UI (Pastikan ID ini sama dengan activity_main.xml kamu)
        val listView = findViewById<ListView>(R.id.listViewRaket)
        val btnSort = findViewById<Button>(R.id.btnSort)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        val etSearch = findViewById<EditText>(R.id.etSearch)

        // Set Adapter menggunakan data dinamis
        customAdapter = RaketAdapter(this, daftarTampil)
        listView.adapter = customAdapter

        // 1. Logika Klik Item untuk Pindah ke Halaman Detail
        listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("NAMA_RAKET", daftarTampil[position])
            intent.putExtra("SPEK_RAKET", spekTampil[position])
            intent.putExtra("GAMBAR_RAKET", gambarTampil[position])
            startActivity(intent)
        }

        // 2. Fitur Cari Raket (Anti-Crash)
        btnSearch.setOnClickListener {
            val query = etSearch.text.toString().trim().lowercase(Locale.getDefault())

            daftarTampil.clear()
            spekTampil.clear()
            gambarTampil.clear()

            if (query.isEmpty()) {
                ResetDataTampilan()
                Toast.makeText(this, "Menampilkan semua raket", Toast.LENGTH_SHORT).show()
            } else {
                for (i in 0 until daftarRaketAsli.size) {
                    if (daftarRaketAsli[i].lowercase(Locale.getDefault()).contains(query)) {
                        daftarTampil.add(daftarRaketAsli[i])
                        spekTampil.add(spekAsli[i])
                        gambarTampil.add(gambarAsli[i])
                    }
                }
                if (daftarTampil.isEmpty()) {
                    Toast.makeText(this, "Raket tidak ditemukan!", Toast.LENGTH_SHORT).show()
                }
            }
            customAdapter.notifyDataSetChanged()
        }

        // 3. Fitur Urutkan Pakai Algoritma Bubble Sort (A-Z)
        btnSort.setOnClickListener {
            if (daftarTampil.size > 1) {
                for (i in 0 until daftarTampil.size - 1) {
                    for (j in 0 until daftarTampil.size - i - 1) {
                        if (daftarTampil[j].lowercase(Locale.getDefault()) > daftarTampil[j + 1].lowercase(Locale.getDefault())) {
                            // Tukar Posisi Nama
                            val tempNama = daftarTampil[j]
                            daftarTampil[j] = daftarTampil[j + 1]
                            daftarTampil[j + 1] = tempNama

                            // Tukar Posisi Spek
                            val tempSpek = spekTampil[j]
                            spekTampil[j] = spekTampil[j + 1]
                            spekTampil[j + 1] = tempSpek

                            // Tukar Posisi Gambar
                            val tempGambar = gambarTampil[j]
                            gambarTampil[j] = gambarTampil[j + 1]
                            gambarTampil[j + 1] = tempGambar
                        }
                    }
                }
                customAdapter.notifyDataSetChanged()
                Toast.makeText(this, "Daftar berhasil diurutkan A-Z!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Tidak ada data yang cukup untuk diurutkan", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun ResetDataTampilan() {
        daftarTampil.clear()
        spekTampil.clear()
        gambarTampil.clear()

        daftarTampil.addAll(daftarRaketAsli)
        spekTampil.addAll(spekAsli)
        gambarTampil.addAll(gambarAsli)
    }

    // Custom Adapter Sederhana & Super Stabil
    class RaketAdapter(
        private val context: Context,
        private val nama: ArrayList<String>
    ) : BaseAdapter() {
        override fun getCount(): Int = nama.size
        override fun getItem(position: Int): Any = nama[position]
        override fun getItemId(position: Int): Long = position.toLong()
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = convertView ?: LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false)
            val text = view.findViewById<TextView>(android.R.id.text1)
            text.text = nama[position]
            text.textSize = 16f
            return view
        }
    }
}