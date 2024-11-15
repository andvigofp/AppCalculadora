package com.example.appcalculadora.calculadora

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcalculadora.Operaciones.eval


@Composable
fun CalculatorScreen() {
    var input by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    // Lista de botones para operaciones básicas y números
    val buttons = listOf(
        "7", "8", "9", "÷",
        "4", "5", "6", "x",
        "1", "2", "3", "-",
        "0", ".", "=", "+"
    )

    // Lista de botones de operaciones científicas avanzadas
    val advancedButtons = listOf(
        "sin", "cos", "tan", "ln", "√", "asin", "acos", "atan", "e"
    )

    // Fila para los botones de operaciones básicas y números
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Muestra el resultado y la entrada
        Text(text = "Resultado: $result", fontSize = 32.sp, modifier = Modifier.fillMaxWidth())
        Text(text = "Entrada: $input", fontSize = 24.sp, modifier = Modifier.fillMaxWidth())

        // Fila para los botones de operaciones básicas y números
        for (i in buttons.chunked(4)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                i.forEach { label ->
                    Button(
                        onClick = {
                            if (label == "=") {
                                result = eval(input)
                            } else {
                                input += label
                            }
                        },
                        modifier = Modifier
                            .weight(1f)  // Esto asegura que los botones tengan el mismo tamaño
                            .height(60.dp)  // Ajustamos la altura de los botones para que sean más grandes
                    ) {
                        Text(text = label, fontSize = 20.sp)
                    }
                }
            }
        }

        // Fila para los botones avanzados (sin, cos, tan, ln, √, etc.)
        for (i in advancedButtons.chunked(5)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                i.forEach { label ->
                    Button(
                        onClick = {
                            when (label) {
                                "π" -> input += Math.PI.toString()  // Aquí reemplazamos "π" por el valor numérico de π
                                else -> {
                                    // Si ya hay un número en la entrada, agregamos la función con paréntesis
                                    if (input.isNotEmpty() && input.last().isDigit()) {
                                        input = "$label(${input.takeLastWhile { it.isDigit() }})"
                                        input = input.dropLastWhile { it.isDigit() }
                                    } else {
                                        // Si no hay un número al final, agregamos la función con paréntesis abierto
                                        input += "$label("
                                    }
                                }
                            }
                        },
                        modifier = Modifier
                            .weight(1f)  // Asegura que todos los botones ocupen un espacio igual
                            .height(60.dp)  // Establecemos la misma altura para todos los botones
                    ) {
                        Text(
                            text = label,
                            fontSize = 16.sp,  // Ajustamos el tamaño del texto para que se vea bien en cada botón
                            maxLines = 1  // Aseguramos que el texto no se divida en más de una línea
                        )
                    }
                }
            }
        }

        // Fila para los botones "AC" y "^" (colocándolos juntos en una fila)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Botón AC
            Button(
                onClick = {
                    input = ""
                    result = ""
                },
                modifier = Modifier
                    .width(80.dp)
                    .height(60.dp)
            ) {
                Text(text = "AC", fontSize = 20.sp)
            }

            // Botón ^ (añadido al lado de AC)
            Button(
                onClick = {
                    input += "^"  // Agregar el símbolo ^ al input cuando el usuario lo pulse
                },
                modifier = Modifier
                    .weight(1f)  // Esto asegura que el botón ocupe el mismo espacio que los demás botones
                    .height(60.dp)  // Establecemos la misma altura para el botón
            ) {
                Text(text = "^", fontSize = 20.sp)
            }
        }
    }
}