package com.example.appcalculadora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.appcalculadora.calculadora.CalculatorScreen
import com.example.appcalculadora.ui.theme.AppCalculadoraTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()  // Esto habilita el uso del área completa de la pantalla (sin barras de navegación o status bar)
        setContent {
            AppCalculadoraTheme { // Aplica el tema de la app
                // Surface es el contenedor principal donde se renderiza el contenido
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    CalculatorScreen() // Llama a la función que define la UI de la calculadora
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppCalculadoraTheme {
        CalculatorScreen() // Previsualización de la pantalla de la calculadora
    }
}