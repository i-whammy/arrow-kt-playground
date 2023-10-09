package functions

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure

sealed interface UserRegisterProblem {
    data class MailAddressAlreadyExists(val mailAddress: String): UserRegisterProblem
    data class InvalidMailAddress(val invalidMailAddress: String): UserRegisterProblem
    data class InvalidUserIdCharacter(val invalidUserId: String): UserRegisterProblem
}

data class UserRegisterRequest(val id: String, val mailAddress: String)
data class RegisteredUser(val id: String, val mailAddress: String)

fun UserRegisterRequest.invoke(): Either<UserRegisterProblem, RegisteredUser> = either {
    ensure(mailAddress.exists()) { UserRegisterProblem.MailAddressAlreadyExists(mailAddress) }
    ensure(mailAddress.isInvalid()) { UserRegisterProblem.InvalidMailAddress(mailAddress) }
    ensure(id.containsInvalidCharacter()) { UserRegisterProblem.InvalidUserIdCharacter(id) }
    RegisteredUser(id, mailAddress)
}

fun String.exists(): Boolean = TODO()
fun String.isInvalid(): Boolean = TODO()
fun String.containsInvalidCharacter(): Boolean = TODO()
