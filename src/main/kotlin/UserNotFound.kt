import arrow.core.Either
import arrow.core.left
import arrow.core.raise.Raise
import arrow.core.raise.either
import arrow.core.raise.ensure
import arrow.core.right

object UserNotFound
data class User(val id: Long)

val user: Either<UserNotFound, User> = User(1L).right()
val error: Either<UserNotFound, User> = UserNotFound.left()

fun Raise<UserNotFound>.user(): User = User(2L)
fun Raise<UserNotFound>.error(): UserNotFound = UserNotFound

fun User.isValid(): Either<UserNotFound, Unit> = either {
    ensure(id > 0) { UserNotFound }
}


fun main() {
    val validUser = User(1)
    val invalidUser = User(0)
    kotlin.system.exitProcess(0)
}