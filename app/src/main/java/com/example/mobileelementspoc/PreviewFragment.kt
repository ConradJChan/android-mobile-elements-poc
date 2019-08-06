package com.example.mobileelementspoc

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [PreviewFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [PreviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class PreviewFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_preview, container, false)
        val btn = rootView.findViewById(R.id.submit) as Button
        btn.setOnClickListener {
            submitInfo(rootView)
        }

        val args = arguments
        val fileIdInput = rootView.findViewById(R.id.file_id_input_text) as EditText
        fileIdInput.text = Editable.Factory.getInstance().newEditable(args?.getString("fileId"))
        val tokenInput = rootView.findViewById(R.id.token_input_text) as EditText
        tokenInput.text = Editable.Factory.getInstance().newEditable(args?.getString("token"))

        return rootView
    }

    companion object {
        fun newInstance(fileId: String, token: String): PreviewFragment {
            val fragment = PreviewFragment()
            val args = Bundle()
            args.putString("fileId", fileId)
            args.putString("token", token)
            fragment.arguments = args
            return fragment
        }
    }

    fun submitInfo(view: View) {
        val fileIdInput = view.findViewById(R.id.file_id_input_text) as EditText
        val tokenInput = view.findViewById(R.id.token_input_text) as EditText
        val fileId = fileIdInput.text
        val token = tokenInput.text

        Log.d("submit", "Submitted $fileId and $token")

        (activity as MainActivity).submit(fileId.toString(), token.toString())
    }

    interface SubmitData {
        var fileId: String
        var token: String

        fun submit(id: String, accessToken: String) {
            fileId = id
            token = accessToken
        }
    }

}
