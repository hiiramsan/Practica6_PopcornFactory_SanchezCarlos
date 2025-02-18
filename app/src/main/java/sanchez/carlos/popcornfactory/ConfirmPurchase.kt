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

        val bundle = intent.extras

        var movieName = ""
        var seat = -1
        var posMovie = -1
        var clientes = ArrayList<Cliente>()

        if(bundle != null) {
            movieName = bundle.getString("movieName").toString()
            seat = bundle.getInt("seat")
            posMovie = bundle.getInt("posMovie")
            clientes = bundle.getSerializable("clientes") as ArrayList<Cliente>
        }

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
                    Toast.makeText(this, "payment needed", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
            }

            val cliente = Cliente(name, paymentType, seat)
            clientes.add(cliente)
            Toast.makeText(this, "Cliente: ${cliente.nombre}, Asiento: ${cliente.asiento}", Toast.LENGTH_LONG).show()
            val intent = Intent(this, PurchaseSummary::class.java)

            intent.putExtra("movieName", movieName)
            intent.putExtra("posMovie", posMovie)
            intent.putExtra("nombreCliente", cliente.nombre)
            intent.putExtra("tipoPago", cliente.tipoPago)
            intent.putExtra("seat", cliente.asiento)

            this.startActivity(intent)


        }
    }
}