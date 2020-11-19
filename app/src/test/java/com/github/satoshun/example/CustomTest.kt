package com.github.satoshun.example

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import org.junit.Test

class CustomTest {
  val a = """
    {
      "parent": {
        "a": 1,
        "b": "100",
        "c": 1.0
      }
    }
  """

  val b = """
    {
      "parent": 10
    }
  """

  @Test
  fun test() {
    val json = Json {
    }

    println(json.decodeFromString<Parent>(a))
  }

  @Test
  fun test2() {
    val json = Json {
    }

    println(json.decodeFromString<Parent>(b))
  }

  @Serializable
  data class Parent(
    val parent: A? = null
  )

  @Serializable(with = ASerializer::class)
  data class A(
    val a: Int,
    val b: Int,
    val c: Int
  )
}

@Serializer(forClass = CustomTest.A::class)
object ASerializer : KSerializer<CustomTest.A?> {
  override fun serialize(encoder: Encoder, value: CustomTest.A?) {
//    encoder.encodeString("")
  }

  override fun deserialize(decoder: Decoder): CustomTest.A? {
    val jsonDecoder = decoder as? JsonDecoder ?: return null
    val jsonElement = jsonDecoder.decodeJsonElement()
    val jsonObject = try {
      jsonElement.jsonObject
    } catch (e: Exception) {
      return null
    }

    val a = jsonObject.getValue("a").jsonPrimitive.int
    val b = jsonObject.getValue("b").jsonPrimitive.content
    val c = jsonObject.getValue("c").jsonPrimitive.float

    return CustomTest.A(a, b.toInt(), c.toInt())
  }
}
