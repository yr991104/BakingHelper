package com.example.bakinghelper

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [match.newInstance] factory method to
 * create an instance of this fragment.
 */
class match : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    val TAG = "MatchFragment"

    private val recycler_view: RecyclerView by lazy{
        view?.findViewById(R.id.recycler_view) as RecyclerView
    }
    private val btn_matchenter: Button by lazy{
        view?.findViewById(R.id.btn_matchenter) as Button
    }
    private val edit_match: EditText by lazy {
        view?.findViewById(R.id.edit_match) as EditText
    }
    private val edit_name: EditText by lazy{
        view?.findViewById(R.id.edit_name) as EditText
    }
    private val edit_quantity: EditText by lazy{
        view?.findViewById(R.id.edit_quantity) as EditText
    }
    private val btn_add: Button by lazy{
        view?.findViewById(R.id.btn_add) as Button
    }

    var bakinglist = arrayListOf<DataBK>(
    )

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
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mAdapter = CustomAdapter(this, bakinglist)
        recycler_view.adapter = mAdapter

        var gridLayout = GridLayoutManager(context, 1)
        recycler_view.layoutManager = gridLayout
        recycler_view.setHasFixedSize(true)

        btn_matchenter.setOnClickListener(){
            for (bkdata in bakinglist){
                var match = edit_match.getText().toString()
                if (match.equals("") || match == null)
                    match = "0"
                val num = bkdata.origin * match.toDouble()
                bkdata.matchorigin = num
            }
            mAdapter.matchItem()
        }

        btn_add.setOnClickListener(){
            var addname = edit_name.getText().toString()
            var addquantity = edit_quantity.getText().toString()
            if (addname.equals("") || addname == null || addquantity.equals("") || addquantity == null){
                mAdapter.matchItem()
            }
            else{
                bakinglist.add(DataBK(addname, addquantity.toInt(), 0.0))
                edit_name.setText(null)
                edit_quantity.setText(null)
                mAdapter.matchItem()
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
         * @return A new instance of fragment match.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            match().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}