package com.github.satoshun.example

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.github.satoshun.example.databinding.MainFragBinding
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MainFragment : Fragment(R.layout.main_frag) {
  private val binding: MainFragBinding get() = MainFragBinding.bind(requireView())

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    binding.sample.setOnClickListener {
      TODO()
    }
  }
}
