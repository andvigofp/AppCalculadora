package com.example.appcalculadora.Operaciones

import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

fun evaluateExpression(expression: String): Double {
    // Reemplazar las operaciones matemáticas
    return when {
        expression.contains("sqrt") -> {
            val num = expression.substringAfter("sqrt(").substringBefore(")").toDouble()
            sqrt(num)
        }
        expression.contains("ln") -> {
            val num = expression.substringAfter("ln(").substringBefore(")").toDouble()
            ln(num)
        }
        expression.contains("sin") -> {
            val num = expression.substringAfter("sin(").substringBefore(")").toDouble()
            sin(Math.toRadians(num)) // Convertir a radianes
        }
        expression.contains("cos") -> {
            val num = expression.substringAfter("cos(").substringBefore(")").toDouble()
            cos(Math.toRadians(num)) // Convertir a radianes
        }
        expression.contains("tan") -> {
            val num = expression.substringAfter("tan(").substringBefore(")").toDouble()
            tan(Math.toRadians(num)) // Convertir a radianes
        }
        expression.contains("asin") -> {
            val num = expression.substringAfter("asin(").substringBefore(")").toDouble()
            Math.toDegrees(asin(num)) // Convertir a grados
        }
        expression.contains("acos") -> {
            val num = expression.substringAfter("acos(").substringBefore(")").toDouble()
            Math.toDegrees(acos(num)) // Convertir a grados
        }
        expression.contains("atan") -> {
            val num = expression.substringAfter("atan(").substringBefore(")").toDouble()
            Math.toDegrees(atan(num)) // Convertir a grados
        }
        expression.contains("**") -> {
            // Evaluar la potencia (exponente)
            val parts = expression.split("**")
            val base = parts[0].toDouble()
            val exponent = parts[1].toDouble()
            Math.pow(base, exponent)  // Calculamos la potencia
        }
        else -> {
            // Si no contiene ninguna función matemática, evaluamos la operación básica
            val sanitized = expression.replace(" ", "") // Eliminar espacios
            val result = try {
                evaluateBasicOperation(sanitized)
            } catch (e: Exception) {
                Double.NaN
            }
            result
        }
    }
}

// Función para extraer el argumento de una función matemática, manejando los paréntesis correctamente
fun extractFunctionArgument(expression: String, function: String): Double {
    val startIdx = expression.indexOf(function + "(") + function.length + 1
    val endIdx = expression.indexOf(")", startIdx)
    val argumentStr = expression.substring(startIdx, endIdx).trim()
    return argumentStr.toDouble() // Convertir el argumento extraído a un número
}