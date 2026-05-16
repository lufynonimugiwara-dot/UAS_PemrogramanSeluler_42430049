package com.example.detailactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Memanggil tampilan detail raket
            DetailRaketScreen(onBackClick = { finish() })
        }
    }
}

@Composable
fun DetailRaketScreen(onBackClick: () -> Unit) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Detail Raket Pro", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Spesifikasi: \n- Berat: 4U \n- Tension: 30 Lbs")

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = onBackClick) {
                Text("Kembali ke Katalog")
            }
        }
    }
}