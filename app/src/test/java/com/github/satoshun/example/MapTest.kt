package com.github.satoshun.example

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class MapTest {
  val json = """{"a": "b", "c": "d"}"""

  @Test
  fun test() {
    println(Json.decodeFromString<Map<String, String>>(json))
  }
}
