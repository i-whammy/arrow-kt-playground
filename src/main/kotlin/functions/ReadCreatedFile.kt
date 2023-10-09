package functions

import arrow.core.Either
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

object FileNotCreated
fun readCreatedFile(filePath: String): Either<FileNotCreated, String> {
    return Either.catchOrThrow<IOException, String> {
        File(filePath).readBytes().toString(Charsets.UTF_8)
    }.mapLeft { e ->
        if (e is FileNotFoundException) FileNotCreated
        else throw e
    }
}