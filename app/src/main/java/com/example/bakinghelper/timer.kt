package com.example.bakinghelper

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.*
import kotlin.concurrent.timer

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [timer.newInstance] factory method to
 * create an instance of this fragment.
 */
class timer : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private val txt_matchnum: TextView by lazy {
        view?.findViewById(R.id.txt_matchnum) as TextView
    }
    private val edit_minute: EditText by lazy{
        view?.findViewById(R.id.edit_minute) as EditText
    }
    private val edit_second: EditText by lazy{
        view?.findViewById(R.id.edit_second) as EditText
    }
    private val btn_start: Button by lazy{
        view?.findViewById(R.id.btn_start) as Button
    }
    private val btn_stop: Button by lazy{
        view?.findViewById(R.id.btn_stop) as Button
    }
    private val btn_pause: Button by lazy {
        view?.findViewById(R.id.btn_pause) as Button
    }
    private val image_clock: ImageView by lazy{
        view?.findViewById(R.id.image_clock) as ImageView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var animation = AnimationUtils.loadAnimation(getActivity(), R.anim.rotate)

        btn_start.setOnClickListener {
            var minute = edit_minute.getText().toString().toInt()
            var second = edit_second.getText().toString().toInt()
            var time = minute * 60000 + second * 1000
            print(minute)
            print(second)
            image_clock.startAnimation(animation)

            timer(period = 1000) {
                if (second == 0) {
                    minute--
                    second = 60
                }
                second--
                btn_pause.setOnClickListener {
                    second = second
                    minute = minute
                    image_clock.clearAnimation()
                    cancel()
                }
                btn_stop.setOnClickListener {
                    second = 0
                    minute = 0
                    getActivity()?.runOnUiThread {
                        edit_second.setText(second.toString())
                        edit_minute.setText(minute.toString())
                        image_clock.clearAnimation()
                    }
                    cancel()
                }
                if (second == 0 && minute == 0) {
                    getActivity()?.runOnUiThread {
                        Toast.makeText(getActivity(), "타이머종료!", Toast.LENGTH_LONG).show()
                        image_clock.clearAnimation()
                    }
                    cancel()
                }
                if (minute < 0){
                    second = 0
                    minute = 0
                    getActivity()?.runOnUiThread {
                        edit_second.setText(second.toString())
                        edit_minute.setText(minute.toString())
                        image_clock.clearAnimation()
                    }
                    cancel()
                }
                getActivity()?.runOnUiThread {
                    edit_second.setText(second.toString())
                    edit_minute.setText(minute.toString())
                }
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment timer.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            timer().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}