package com.example.appcalculadora.calculadora

import kotlin.math.acos
import kotlin.math.asin
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.ln
import kotlin.math.sin
import kotlin.math.sqrt
import kotlin.math.tan

// Función para operaciones avanzadas y trigonométricas
fun calculateAdvancedOperation(value: Double, operation: String): Double {
    return try {
        when (operation) {
            "sin" -> sin(Math.toRadians(value)) // Convertir a radianes
            "cos" -> cos(Math.toRadians(value)) // Convertir a radianes
            "tan" -> tan(Math.toRadians(value)) // Convertir a radianes
            "asin" -> if (value in -1.0..1.0) Math.toDegrees(asin(value)) else throw IllegalArgumentException("asin: el valor debe estar entre -1 y 1")
            "acos" -> if (value in -1.0..1.0) Math.toDegrees(acos(value)) else throw IllegalArgumentException("acos: el valor debe estar entre -1 y 1")
            "atan" -> Math.toDegrees(atan(value)) // Convertir a grados
            "√" -> if (value >= 0) sqrt(value) else throw IllegalArgumentException("√: el valor debe ser no negativo")
            "ln" -> if (value > 0) ln(value) else throw IllegalArgumentException("ln: el valor debe ser mayor que 0")
            else -> throw IllegalArgumentException("Operación no válida")
        }
    } catch (e: Exception) {
        throw IllegalArgumentException("Error en la operación avanzada: ${e.message}")
    }
}