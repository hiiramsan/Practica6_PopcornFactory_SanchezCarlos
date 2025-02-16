package sanchez.carlos.popcornfactory

import android.content.Intent
import android.os.Bundle
import android.widget.Button
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

        // textos
        val movieName: TextView = findViewById(R.id.movieName)
        val clienteNombre: TextView = findViewById(R.id.clientName)
        val seat: TextView = findViewById(R.id.seat)
        val paymentMethod: TextView = findViewById(R.id.paymentMethod)

        val btnHome : Button = findViewById(R.id.homeBtn)


        // datos del intent
        val nombreCliente = intent.getStringExtra("nombreCliente")
        val pelicula = intent.getStringExtra("movieName")
        val tipoPago = intent.getStringExtra("tipoPago")
        val asientoId = intent.getStringExtra("asientoId")

        movieName.text = "Movie: $pelicula"
        clienteNombre.text = "Client: $nombreCliente"
        seat.text = "Seat: $asientoId"
        paymentMethod.text = "$tipoPago"


        btnHome.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }



    }
}