package com.github.satoshun.example

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class EmptyStringToListTest {
  val a = ""

  @Test
  fun test() {
    println(Json.decodeFromString<List<AAA>>(a))
  }

  @Test
  fun test2() {
    println(Json.decodeFromString<List<AAA>>(a))
  }

  @Serializable
  data class AAA(val id: String)
}
