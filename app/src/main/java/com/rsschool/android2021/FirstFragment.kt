package com.rsschool.android2021

import android.annotation.SuppressLint
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
import java.lang.Exception


class FirstFragment : Fragment() {

    private var generateButton: Button? = null
    private var previousResult: TextView? = null
    private var editMin: EditText? = null
    private var editMax: EditText? = null
    private var listener: ActionPerformedListenerForFirstFragment? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as ActionPerformedListenerForFirstFragment
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previousResult = view.findViewById(R.id.previous_result)
        generateButton = view.findViewById(R.id.generate)
        setHasOptionsMenu(true)

        val result = arguments?.getInt(PREVIOUS_RESULT_KEY)
        previousResult?.text = "Previous result: ${result.toString()}"

        editMin = view.findViewById(R.id.min_value)
        editMax = view.findViewById(R.id.max_value)


        generateButton?.setOnClickListener {
            if(isValuesValid()) {
                listener?.onActionPerformed(
                    editMin!!.text.toString().toInt(),
                    editMax!!.text.toString().toInt()
                )
            } else{
                val duration = Toast.LENGTH_SHORT
                Toast.makeText(context, "You know, that min<max? Try again, bro!", duration).show()
            }

        }
    }

    private fun isValuesValid(): Boolean {
        try {
            val firstValue = editMin!!.text.toString().toInt()
            val secondValue = editMax!!.text.toString().toInt()

            if (firstValue > secondValue) return false
        } catch (_: Exception) {
            return false
        }

        return true
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

    interface ActionPerformedListenerForFirstFragment {
        fun onActionPerformed(min: Int, max: Int)
    }
}