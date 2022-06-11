package io.craigmiller160.springarrowkt.container.controller

import arrow.core.Either
import io.craigmiller160.springarrowkt.container.dto.SuccessResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/either")
class EitherController {

  // TODO try this:
  // https://developpaper.com/the-return-value-format-of-springboot-global-controller-is-uniform/
  @GetMapping("/success")
  fun success(): Either<Throwable, SuccessResponse> = Either.Right(SuccessResponse("Hello World"))

  @GetMapping("/response-entity")
  fun responseEntity(): Either<Throwable, ResponseEntity<SuccessResponse>> =
      Either.Right(ResponseEntity.status(201).body(SuccessResponse("Hello World")))

  @GetMapping("/failure")
  fun failure(): Either<Throwable, SuccessResponse> = Either.Left(RuntimeException("Dying"))
}
