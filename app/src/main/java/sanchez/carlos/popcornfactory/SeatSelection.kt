package sanchez.carlos.popcornfactory

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SeatSelection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_seat_selection)

        val title : TextView = findViewById(R.id.titleSeats)
        var posMovie = -1

        val bundle = intent.extras

        if(bundle != null) {
            title.setText(bundle.getString("name"))
            posMovie = bundle.getInt("id")
        }

        val confirm : Button = findViewById(R.id.confirm_button)

        confirm.setOnClickListener{
            val row1: RadioGroup = findViewById(R.id.row1)
            val row2: RadioGroup = findViewById(R.id.row2)
            val row3: RadioGroup = findViewById(R.id.row3)
            val row4: RadioGroup = findViewById(R.id.row4)

           // logica para reservar el asiento seleccionado
            val selectedSeatId = when {
                row1.checkedRadioButtonId != -1 -> row1.checkedRadioButtonId
                row2.checkedRadioButtonId != -1 -> row2.checkedRadioButtonId
                row3.checkedRadioButtonId != -1 -> row3.checkedRadioButtonId
                row4.checkedRadioButtonId != -1 -> row4.checkedRadioButtonId
                else -> -1
            }

            if(selectedSeatId == -1) {
                Toast.makeText(this, "Please select a seat!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }



            val intent = Intent(this, ConfirmPurchase::class.java)

            intent.putExtra("movieName", title.text.toString())
            intent.putExtra("seatId", selectedSeatId)
            intent.putExtra("posMovie", posMovie)

            this.startActivity(intent);





            // hacer una neuva actividad donde se vea el resumen de la compra es decir que se agregeue el nombre del cliente y
            // se vea el asiento que selecciono
            //Toast.makeText(this, "Enjoy the movie!", Toast.LENGTH_LONG).show()

        }
    }
}