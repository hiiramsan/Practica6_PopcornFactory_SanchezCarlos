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

        // textos y btns
        val movieName: TextView = findViewById(R.id.movieName)
        val clienteNombre: TextView = findViewById(R.id.clientName)
        val seat: TextView = findViewById(R.id.seat)
        val paymentMethod: TextView = findViewById(R.id.paymentMethod)
        val btnHome : Button = findViewById(R.id.homeBtn)


        var nombreCliente = ""
        var pelicula = ""
        var tipoPago = ""
        var asiento = 0

        // datos del intent
        var bundle = intent.extras


        if(bundle != null) {
            nombreCliente = bundle.getString("nombreCliente").toString()
            pelicula = bundle.getString("movieName").toString()
            tipoPago = bundle.getString("tipoPago").toString()
            asiento = bundle.getInt("seat")
        }

        movieName.text = "Movie: $pelicula"
        clienteNombre.text = "Client: $nombreCliente"
        seat.text = "Seat: $asiento"
        paymentMethod.text = "Payment methdd: $tipoPago"

        btnHome.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        }



    }
}