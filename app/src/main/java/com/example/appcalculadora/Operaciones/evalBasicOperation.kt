package com.example.appcalculadora.Operaciones

import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan


// Evaluar operaciones básicas como suma, resta, multiplicación y división
// Evaluar operaciones matemáticas básicas como suma, resta, multiplicación y división
fun evaluateBasicOperation(expression: String): Double {
    // Usar una simple lógica de evaluación, dividiendo la expresión y operando
    // Eliminar espacios en blanco para facilitar el análisis
    val sanitizedExpression = expression.replace(" ", "")

    // Evaluar la expresión usando un algoritmo de pila para manejar la precedencia
    val values = mutableListOf<Double>() // Pila para números
    val operators = mutableListOf<Char>() // Pila para operadores

    var i = 0
    while (i < sanitizedExpression.length) {
        val ch = sanitizedExpression[i]

        when {
            // Si el carácter es un número
            ch.isDigit() || ch == '.' -> {
                var num = ""
                // Leer el número completo (que puede tener más de un dígito o un punto decimal)
                while (i < sanitizedExpression.length && (sanitizedExpression[i].isDigit() || sanitizedExpression[i] == '.')) {
                    num += sanitizedExpression[i]
                    i++
                }
                values.add(num.toDouble()) // Agregar el número a la pila de valores
            }

            // Si el carácter es un operador (+, -, *, /)
            ch in listOf('+', '-', '*', '/') -> {
                // Mientras el operador en la cima de la pila tenga mayor o igual precedencia
                while (operators.isNotEmpty() && precedence(operators.last()) >= precedence(ch)) {
                    val op = operators.removeAt(operators.size - 1)
                    val b = values.removeAt(values.size - 1)
                    val a = values.removeAt(values.size - 1)
                    values.add(performOperation(a, b, op))
                }
                operators.add(ch) // Agregar el operador actual a la pila
                i++
            }

            // Si encontramos un paréntesis de apertura
            ch == '(' -> {
                operators.add(ch)
                i++
            }

            // Si encontramos un paréntesis de cierre
            ch == ')' -> {
                while (operators.isNotEmpty() && operators.last() != '(') {
                    val op = operators.removeAt(operators.size - 1)
                    val b = values.removeAt(values.size - 1)
                    val a = values.removeAt(values.size - 1)
                    values.add(performOperation(a, b, op))
                }
                operators.removeAt(operators.size - 1) // Eliminar el paréntesis de apertura
                i++
            }

            else -> {
                i++
            }
        }
    }

    // Procesar los operadores restantes
    while (operators.isNotEmpty()) {
        val op = operators.removeAt(operators.size - 1)
        val b = values.removeAt(values.size - 1)
        val a = values.removeAt(values.size - 1)
        values.add(performOperation(a, b, op))
    }

    // El único valor restante en la pila es el resultado
    return values[0]
}

// Función para determinar la precedencia de los operadores
fun precedence(operator: Char): Int {
    return when (operator) {
        '+', '-' -> 1
        '*', '/' -> 2
        else -> 0
    }
}

// Función para realizar las operaciones matemáticas
fun performOperation(a: Double, b: Double, operator: Char): Double {
    return when (operator) {
        '+' -> a + b
        '-' -> a - b
        '*' -> a * b
        '/' -> if (b != 0.0) a / b else Double.NaN // Evitar división por cero
        else -> Double.NaN
    }
}