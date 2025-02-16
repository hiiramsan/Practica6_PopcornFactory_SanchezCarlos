package sanchez.carlos.popcornfactory

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class DetallePelicula : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_pelicula)

        var iv_pelicula_imagen : ImageView = findViewById(R.id.iv_pelicula_imagen)
        var tv_nombre : TextView = findViewById(R.id.tv_nombre_jaja)
        var tv_pelicula_desc : TextView = findViewById(R.id.tv_nombre_desc)
        var seatsLeft : TextView = findViewById(R.id.seatsLeft)
        var buyTickets : Button = findViewById(R.id.buyTickets)

        val bundle = intent.extras

        var ns = 0
        var id = -1
        var title = ""
        if(bundle != null) {
            ns = bundle.getInt("numberSeats")
            title = bundle.getString("titulo")!!
            iv_pelicula_imagen.setImageResource(bundle.getInt("header"))
            tv_nombre.setText(bundle.getString("titulo"))
            tv_pelicula_desc.setText(bundle.getString("sinopsis"))
            seatsLeft.text ="$ns seats available"
            id = bundle.getInt("pos")
        }

        if(ns == 0) {
            buyTickets.isEnabled = false
        } else {
            buyTickets.setOnClickListener() {
                val intent : Intent = Intent(this, SeatSelection::class.java)

                intent.putExtra("movie", id)
                intent.putExtra("name", title)

                this.startActivity(intent)
            }
        }

    }
}