package com.github.satoshun.example

import org.junit.Test

class CustomTest {
  val a = """{"a": 1, "b": "100", "c": 1.0}"""

  @Test
  fun test() {
    println(Json.decodeFromString<A>(a))
  }

  class A(
    val a: Int,
    val b: Int,
    val c: Int
  )
}
