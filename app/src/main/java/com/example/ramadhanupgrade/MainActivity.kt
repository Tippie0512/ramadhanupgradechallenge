package com.example.ramadhanupgrade

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cbSholat = findViewById<CheckBox>(R.id.cbSholat)
        val cbTilawah = findViewById<CheckBox>(R.id.cbTilawah)
        val cbSedekah = findViewById<CheckBox>(R.id.cbSedekah)
        val cbTaraweh = findViewById<CheckBox>(R.id.cbTaraweh)
        val btnUpdateProgress = findViewById<MaterialButton>(R.id.btnUpdateProgress)
        val btnShare = findViewById<MaterialButton>(R.id.btnShare)
        val authorTextView = findViewById<TextView>(R.id.authorTextView)

        // Set text with superscript for the author credit
        val creditText = getString(R.string.author_credit)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            authorTextView.text = Html.fromHtml(creditText, Html.FROM_HTML_MODE_COMPACT)
        } else {
            @Suppress("DEPRECATION")
            authorTextView.text = Html.fromHtml(creditText)
        }

        btnUpdateProgress.setOnClickListener {
            val tasks = listOf(cbSholat, cbTilawah, cbSedekah, cbTaraweh)
            val completed = tasks.count { it.isChecked }
            val total = tasks.size
            
            val message = if (completed == total) {
                "Maa syaa Allah! Semua tantangan selesai hari ini!"
            } else {
                "Semangat! Kamu sudah menyelesaikan $completed dari $total tantangan."
            }
            
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        }

        btnShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                val shareText = getString(R.string.share_text)
                putExtra(Intent.EXTRA_TEXT, shareText)
            }
            startActivity(Intent.createChooser(shareIntent, getString(R.string.share_goodness)))
        }
    }
}
