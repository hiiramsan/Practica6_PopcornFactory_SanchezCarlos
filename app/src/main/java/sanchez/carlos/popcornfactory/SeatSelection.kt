package sanchez.carlos.popcornfactory

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SeatSelection : AppCompatActivity() {

    private lateinit var rows: List<RadioGroup>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seat_selection)

        val title : TextView = findViewById(R.id.titleSeats)
        var posMovie = -1
        var movieName = ""
        var clientes = ArrayList<Cliente>()


        val bundle = intent.extras

        if(bundle != null) {
            movieName = bundle.getString("name", "")
            posMovie = bundle.getInt("id")
            clientes = bundle.getSerializable("clientes") as ArrayList<Cliente>
        }

        val confirm : Button = findViewById(R.id.confirm_button)

        rows = listOf(
            findViewById(R.id.row1),
            findViewById(R.id.row2),
            findViewById(R.id.row3),
            findViewById(R.id.row4)
        )

        disableTakenSeats(clientes)

        confirm.setOnClickListener{
           // logica para reservar el asiento seleccionado
            val selectedSeat = getSelectedSeat()

            if (selectedSeat == -1) {
                Toast.makeText(this, "Please select a seat!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val intent = Intent(this, ConfirmPurchase::class.java)

            intent.putExtra("movieName", movieName)
            intent.putExtra("seatId", selectedSeat)
            intent.putExtra("posMovie", posMovie)
            intent.putExtra("clientes", clientes)

            startActivity(intent)
            // hacer una neuva actividad donde se vea el resumen de la compra es decir que se agregeue el nombre del cliente y
            // se vea el asiento que selecciono
            //Toast.makeText(this, "Enjoy the movie!", Toast.LENGTH_LONG).show()

        }
    }

    fun getSelectedSeat(): Int {
        rows.forEachIndexed { rowIndex, row ->
            val checked = row.checkedRadioButtonId
            if (checked != -1) return (rowIndex * row.childCount) + row.indexOfChild(findViewById(checked))
        }
        return -1
    }

     fun disableTakenSeats(clientes: ArrayList<Cliente>) {
        clientes.forEach { cliente ->
            val seatIndex = cliente.asiento
            val rowIndex = seatIndex / rows[0].childCount
            val colIndex = seatIndex % rows[0].childCount

            if (rowIndex in rows.indices) {
                val seat = rows[rowIndex].getChildAt(colIndex) as? RadioButton
                seat?.isEnabled = false
            }
        }
    }
}