package io.craigmiller160.springarrowkt.container.service

import arrow.core.Either
import io.craigmiller160.springarrowkt.container.domain.ds1.entities.Person
import io.craigmiller160.springarrowkt.container.domain.ds1.repositories.PersonRepository
import javax.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class JavaxTransactionPersonService(private val personRepository: PersonRepository) {
  @Transactional
  fun javaxSaveAndCommit(person: Person): Either<Throwable, Person> =
      Either.Right(personRepository.saveAndFlush(person))

  @Transactional
  fun javaxSaveAndRollback(person: Person): Either<Throwable, Person> {
    personRepository.saveAndFlush(person)
    return Either.Left(RuntimeException("Dying"))
  }

  @Transactional
  fun javaxNoEitherSaveAndCommit(person: Person): Person = personRepository.save(person)

  @Transactional
  fun javaxNoEitherSaveAndRollback(person: Person): Person {
    personRepository.save(person)
    throw RuntimeException("Dying")
  }
}