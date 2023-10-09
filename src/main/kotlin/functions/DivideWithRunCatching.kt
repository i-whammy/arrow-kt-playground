package functions

fun Denominator.divideWithRunCatching(numerator: Numerator): Int {
    return runCatching { this.value / numerator.value } // Result<Int>
        .fold(
            // on success
            { n -> n },
            // on failure
            { e ->
                // some error handling logic
                throw e
            }
        )
}