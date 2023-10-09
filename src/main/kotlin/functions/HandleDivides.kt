package functions

import arrow.core.Either
import arrow.core.Eval.Companion.raise
import arrow.core.raise.either
import arrow.core.raise.recover
import arrow.core.recover

fun handleUsingWhen() {
    val answer = Denominator(10).divideWithArrow(Numerator(0))
    when (answer) {
        is Either.Left -> {
            // some handling logic here!
            answer.value // DividedByZero
        }

        is Either.Right -> {
            answer.value // Int
        }
    }
}

fun handleUsingBind() {
    val maybeFive: Either<Error, Int> = Denominator(10).divideWithArrow(Numerator(2))
    val maybeTwo: Either<Error, Int> = Denominator(20).divideWithArrow(Numerator(0)) // this is zero!!
    val maybeTen: Either<Error, Int> = either {
        maybeFive.bind() * maybeTwo.bind()
    }
}

fun main() {
    println(handleUsingRecovery(Denominator(10), Numerator(0)))
    kotlin.system.exitProcess(0)
}


object MoreDomainDetailedError : Error

fun handleUsingRecovery(denominator: Denominator, numerator: Numerator): Either<Error, Int> {
    return denominator.divideWithArrow(numerator)
        .recover { _: Error -> raise(MoreDomainDetailedError) }
}
