package com.github.satoshun.example

import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class ListTest {
  val list = """[{"id": "1", "name": "2"}, {"id": "3", "name": "4"}]"""
  val a = """{"list": $list}"""
  val b = """
    {"list": [{"id": "1", "name": "2", "list": $list}]}
  """

  @Test
  fun test() {
    val type = object : TypeToken<Response>() {}.type
    assertThat(Json.decodeFromString<Response>(a))
      .isEqualTo(Gson().fromJson(a, type))
  }

  @Test
  fun test2() {
    val type = object : TypeToken<ResponseB>() {}.type
    assertThat(Json.decodeFromString<ResponseB>(b))
      .isEqualTo(Gson().fromJson(b, type))
  }

  @Test
  fun test3() {
    val type = object : TypeToken<List<A>>() {}.type
    assertThat(Json.decodeFromString<List<A>>(list))
      .isEqualTo(Gson().fromJson(list, type))
  }

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
