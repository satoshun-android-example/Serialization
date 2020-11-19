package com.github.satoshun.example

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.plus
import org.junit.Test

class CustomTest {
  val a = """{"a": 1, "b": "100", "c": 1.0}"""

  @Test
  fun test() {
    val json = Json {
    }

    println(json.decodeFromString<A>(a))
  }

  @Serializable(with = ASerializer::class)
  data class A(
    val a: Int,
    val b: Int,
    val c: Int
  )
}

object ASerializer : KSerializer<CustomTest.A> {
  override val descriptor: SerialDescriptor
    get() = PrimitiveSerialDescriptor("A", PrimitiveKind.STRING)

  override fun serialize(encoder: Encoder, value: CustomTest.A) {
//    encoder.encodeString("")
  }

  override fun deserialize(decoder: Decoder): CustomTest.A {
    val jsonDecoder = decoder as JsonDecoder
    val json = jsonDecoder.decodeJsonElement().jsonObject

    val a = json.getValue("a").jsonPrimitive.int
    val b = json.getValue("b").jsonPrimitive.content
    val c = json.getValue("c").jsonPrimitive.float

    return CustomTest.A(a, b.toInt(), c.toInt())
  }
}
