package sanchez.carlos.popcornfactory

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PurchaseSummary : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_purchase_summary)

        val tvSummary: TextView = findViewById(R.id.tvSummary)

        val cliente = intent.getSerializableExtra("cliente") as Cliente?
        val movieName = intent.getStringExtra("movieName")



    }
}