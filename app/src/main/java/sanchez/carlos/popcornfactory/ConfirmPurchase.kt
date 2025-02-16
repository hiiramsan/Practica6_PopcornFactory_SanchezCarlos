package sanchez.carlos.popcornfactory

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ConfirmPurchase : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confirm_purchase)

        val tvMovieTitle: TextView = findViewById(R.id.tvMovieTitle)
        val etName: EditText = findViewById(R.id.etName)
        val paymentGroup: RadioGroup = findViewById(R.id.paymentGroup)
        val btnConfirm: Button = findViewById(R.id.btnConfirm)

        val movieName = intent.getStringExtra("movieName")
        val seatId = intent.getIntExtra("seatId", -1)
        val posMovie = intent.getIntExtra("posMovie", -1)

        tvMovieTitle.text = "Movie: $movieName"

        btnConfirm.setOnClickListener {
            val name = etName.text.toString()
            if (name.isEmpty()) {
                Toast.makeText(this, "Name needed", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val paymentType = when (paymentGroup.checkedRadioButtonId) {
                R.id.paymentCard -> "Card"
                R.id.paymentCash -> "Cash"
                else -> {
                    Toast.makeText(this, "Payment needed", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
            }

            val cliente = Cliente(name, paymentType, seatId)

            val intent = Intent(this, PurchaseSummary::class.java)

            intent.putExtra("movieName", movieName)
            intent.putExtra("posMovie", posMovie)
            intent.putExtra("nombreCliente", cliente.nombre)
            intent.putExtra("tipoPago", cliente.tipoPago)
            intent.putExtra("asientoId", cliente.asiento)

            this.startActivity(intent)


        }
    }
}