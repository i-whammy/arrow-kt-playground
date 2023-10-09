package functions

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure

data class Denominator(val value: Int)
data class Numerator(val value: Int)

object DividedByZero: Error
interface Error

fun Denominator.divideWithArrow(numerator: Numerator): Either<DividedByZero, Int> = either {
    ensure(numerator.value != 0) { DividedByZero }
    value / numerator.value
}