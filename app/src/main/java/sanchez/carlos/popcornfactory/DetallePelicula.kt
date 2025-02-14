package sanchez.carlos.popcornfactory

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetallePelicula : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_pelicula)

        var iv_pelicula_imagen : ImageView = findViewById(R.id.iv_pelicula_imagen)
        var tv_nombre : TextView = findViewById(R.id.tv_nombre_jaja)
        var tv_pelicula_desc : TextView = findViewById(R.id.tv_nombre_desc)

        val bundle = intent.extras

        if(bundle != null) {
            iv_pelicula_imagen.setImageResource(bundle.getInt("header"))
            tv_nombre.setText(bundle.getString("titulo"))
            tv_pelicula_desc.setText(bundle.getString("sinopsis"))
        }

    }
}