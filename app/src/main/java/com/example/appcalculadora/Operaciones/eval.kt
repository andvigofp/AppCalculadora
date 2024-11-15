package com.example.appcalculadora.Operaciones

// Función para evaluar las expresiones matemáticas
fun eval(expression: String): String {
    return try {
        // Reemplazar operadores para asegurarnos de que la expresión sea válida
        val sanitizedExpression = expression.replace("x", "*")
            .replace("÷", "/")
            .replace("√", "sqrt")  // Reemplazar √ con sqrt
            .replace("sin", "sin")
            .replace("cos", "cos")
            .replace("tan", "tan")
            .replace("asin", "asin")
            .replace("acos", "acos")
            .replace("atan", "atan")
            .replace("π", "Math.PI")
            .replace("e", "Math.E")
            .replace("^", "**")  // Reemplazar ^ por ** para usar la potencia

        // Evaluar la expresión usando eval o un proceso similar
        val result = evaluateExpression(sanitizedExpression)  // Esta función maneja el cálculo

        result.toString()
    } catch (e: Exception) {
        "Error"  // En caso de algún error, retornar Error
    }
}

fun evaluateBasicExpression(expression: String): Double {
    // Lógica básica para evaluar operaciones como +, -, *, /
    // Este método puede ser más complejo si necesitas manejar precedencia y paréntesis.
    return try {
        val regex = Regex("""(\d+(\.\d+)?|[-+*/()])""")
        val tokens = regex.findAll(expression.replace(" ", "")).map { it.value }.toList()
        val result = calculateBasicOperation(tokens)
        result
    } catch (e: Exception) {
        throw IllegalArgumentException("Expresión no válida")
    }
}

fun calculateBasicOperation(tokens: List<String>): Double {
    var result = tokens[0].toDouble()
    var i = 1
    while (i < tokens.size) {
        val operator = tokens[i]
        val operand = tokens[i + 1].toDouble()

        result = when (operator) {
            "+" -> result + operand
            "-" -> result - operand
            "*" -> result * operand
            "/" -> {
                if (operand == 0.0) throw ArithmeticException("División por cero")
                result / operand
            }
            else -> result
        }
        i += 2
    }
    return result
}