package com.example.appcalculadora.calculadora

import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.log
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

// Función para operaciones básicas como +, -, *, /
fun calculateBasicOperation(expression: String): List<String> {
    // Usamos una expresión regular que maneja números, operadores y posibles espacios en blanco
    val regex = Regex("""(\d+(\.\d+)?|[-+*/()])""")
    val tokens = regex.findAll(expression.replace(" ", "")).map { it.value }.toList()

    if (tokens.isEmpty()) throw IllegalArgumentException("Expresión vacía o no válida")

    // Primero se resuelven las operaciones de alta precedencia (*, /)
    val firstPass = applyOperator(tokens, listOf("*", "/"))

    // Luego pasamos a la segunda fase de operaciones con menor precedencia (+, -)
    return applyOperator(firstPass, listOf("+", "-"))
}

fun applyOperator(tokens: List<String>, operators: List<String>): List<String> {
    var result = mutableListOf<String>()
    var i = 0

    // Recorremos los tokens para hacer las operaciones según los operadores pasados
    while (i < tokens.size) {
        val token = tokens[i]
        if (operators.contains(token)) {
            // Realizamos la operación con el operador encontrado
            val left = result.removeAt(result.lastIndex).toDouble()
            val right = tokens[i + 1].toDouble()
            val operationResult = when (token) {
                "+" -> left + right
                "-" -> left - right
                "*" -> left * right
                "/" -> {
                    if (right == 0.0) throw ArithmeticException("División por cero")
                    left / right
                }
                else -> throw IllegalArgumentException("Operador no válido")
            }
            result.add(operationResult.toString())
            i += 2 // Saltamos al siguiente operador
        } else {
            // Si el token no es un operador, lo añadimos a la lista de resultados
            result.add(token)
            i++
        }
    }
    return result
}