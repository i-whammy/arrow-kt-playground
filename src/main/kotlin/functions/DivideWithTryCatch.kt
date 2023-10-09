package functions

fun Denominator.divideWithTryCatch(numerator: Numerator): Int {
    try {
        return this.value / numerator.value
    } catch (e: ArithmeticException) {
        // some error handling logic
        // e.g.) log("You cannot pass 0 as numerator!")
        throw e
    }
}
