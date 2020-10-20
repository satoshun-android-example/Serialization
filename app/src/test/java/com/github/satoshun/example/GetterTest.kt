package com.github.satoshun.example

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.junit.Test

class GetterTest {
  val data = """
    {
      "data": 
        {
          "id": 1,
          "name": "tom"
        }
    }
  """

  @Test
  fun test() {
    val type = object : TypeToken<Response<NameId>>() {}.type
    val result = Gson().fromJson<Response<NameId>>(data, type)

    println(result)
    println(result.data)
  }

  @Test
  fun test2() {
    val result = Json {}.decodeFromString<Response2<NameId>>(data)

    println(result)
    println(result.data)
  }

  class Response<T>(
    @SerializedName("data") private var _data: T? = null
  ) {
    val data: T get() = _data!!
  }

  @Serializable
  class Response2<T>(
    @SerialName("data") private var _data: T? = null
  ) {
    val data: T get() = _data!!
  }

  @Serializable
  data class NameId(
    val id: Int,
    val name: String
  )
}
