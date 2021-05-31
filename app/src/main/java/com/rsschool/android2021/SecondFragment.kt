package com.rsschool.android2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class SecondFragment : Fragment() {

    private var backButton: Button? = null
    private var result: TextView? = null
    private var listener: ActionPerformedListenerForSecondFragment? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as ActionPerformedListenerForSecondFragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        result = view.findViewById(R.id.result)
        backButton = view.findViewById(R.id.back)

        val min = arguments?.getInt(MIN_VALUE_KEY) ?: 0
        val max = arguments?.getInt(MAX_VALUE_KEY) ?: 0

        result?.text = generate(min, max).toString()

        backButton?.setOnClickListener {backButtonClicked()}
    }

    private fun generate(min: Int, max: Int): Int {
        return ((min..max).random())
    }

    companion object {

        @JvmStatic
        fun newInstance(min: Int, max: Int): SecondFragment {
            val fragment = SecondFragment()
            fragment.arguments = bundleOf(
                MIN_VALUE_KEY to min,
                MAX_VALUE_KEY to max
            )

            return fragment
        }

        private const val MIN_VALUE_KEY = "MIN_VALUE"
        private const val MAX_VALUE_KEY = "MAX_VALUE"
    }
    fun backButtonClicked() = listener?.backButtonClicked(result?.text.toString().toInt())
    interface ActionPerformedListenerForSecondFragment {
        fun backButtonClicked(previousResult: Int)
    }

}