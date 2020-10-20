package com.github.satoshun.example

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class GenericsTest {
  val json
    get() = Json {}

  val data = """
    {
      "data": [
        {
          "id": 1,
          "name": "tom"
        }
      ]
    }
  """

  @Test
  fun test() {
    val result: Response<List<NameId>> = json.decodeFromString(data)
    println(result)
  }

  // fail
  @Test
  fun testJava() {
    val result: ResponseJava<List<NameId>> = json.decodeFromString(data)
    println(result)
  }

  @Serializable
  data class Response<T>(
    val data: T
  )

  @Serializable
  data class NameId(
    val id: Int,
    val name: String
  )
}
