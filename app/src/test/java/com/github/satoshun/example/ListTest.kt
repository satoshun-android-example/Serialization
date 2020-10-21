package com.github.satoshun.example

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class ListTest {
  private val json
    get() = Json {
      ignoreUnknownKeys = true
    }

  private val gson
    get() = Gson()

  val list = """[{"id": "1", "name": "2", "a": "gender"}, {"id": "3", "name": "4"}]"""
  val a = """{"list": $list}"""
  val b = """
    {"list": [{"id": "1", "name": "2", "list": $list}]}
  """
  val c = """{"a": $list}"""

  @Test
  fun test() {
    val type = object : TypeToken<Response>() {}.type
    assertThat(json.decodeFromString<Response>(a))
      .isEqualTo(Gson().fromJson(a, type))
  }

  @Test
  fun test2() {
    val type = object : TypeToken<ResponseB>() {}.type
    assertThat(json.decodeFromString<ResponseB>(b))
      .isEqualTo(Gson().fromJson(b, type))
  }

  @Test
  fun test3() {
    val type = object : TypeToken<List<A>>() {}.type
    assertThat(json.decodeFromString<List<A>>(list))
      .isEqualTo(Gson().fromJson(list, type))
  }

  // fail
  @Test
  fun test4() {
    a<List<A>>(c)
  }

  private inline fun <reified T> a(j: String) {
    val type = object : TypeToken<ResponseC<T>>() {}.type
    val resultGson: ResponseC<T> = gson.fromJson(j, type)
    val resultKotlinx: ResponseC<T> = json.decodeFromString(j)

    assertThat(resultGson).isEqualTo(resultKotlinx)
  }

  @Serializable
  data class ResponseC<T>(
    val a: T
  )

  @Serializable
  data class Response(
    val list: List<A>
  )

  @Serializable
  data class A(
    val id: String,
    val name: String
  )

  @Serializable
  data class ResponseB(
    val list: List<B>
  )

  @Serializable
  data class B(
    val id: String,
    val name: String,
    val list: List<A>
  )
}
