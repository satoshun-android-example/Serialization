package com.github.satoshun.example

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test

class MapTest {
  val json = """{"a": "b", "c": "d"}"""

  @Test
  fun test() {
    println(Json.decodeFromString<Map<String, String>>(json))

    val result = Json.decodeFromString<Map<String, String>>(json)
    println(Json.encodeToString(result))
  }
}