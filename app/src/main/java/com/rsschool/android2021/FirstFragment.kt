package com.rsschool.android2021

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResultTextView: TextView? = null
    private var minValueField: EditText? = null
    private var maxValueField: EditText? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResultTextView = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        minValueField = view.findViewById(R.id.min_value)
        maxValueField = view.findViewById(R.id.max_value)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResultTextView?.text = "Previous result: ${result.toString()}"

        // TODO: val min = ...
        // TODO: val max = ...
        generateButton?.setOnClickListener {
            val activity = activity as? MainActivity
            val min = minValueField?.text?.toString()?.toIntOrNull() ?: return@setOnClickListener
            val max = maxValueField?.text?.toString()?.toIntOrNull() ?: return@setOnClickListener
            if (min >= max) return@setOnClickListener;
            activity?.onRangeGenerated(min, max)
            // TODO: send min and max to the SecondFragment
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(previousResult: Int): FirstFragment {
            val fragment = FirstFragment()
            val args = Bundle()
            args.putInt(PREVIOUS_RESULT_KEY, previousResult)
            fragment.arguments = args
            return fragment
        }

        private const val PREVIOUS_RESULT_KEY = "PREVIOUS_RESULT"
    }
}