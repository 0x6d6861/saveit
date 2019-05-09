package co.heri.saveit.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.get
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import co.heri.saveit.R
import co.heri.saveit.adapters.WeekAdapter
import co.heri.saveit.models.Week
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_home.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [Home.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class Home : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var weeks_list: RecyclerView
    lateinit var weekAdapter: WeekAdapter;
    lateinit var weeks: ArrayList<Week>;






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)


        weeks = arrayListOf<Week>()

        val principal = 50;

        var deposit  = 0;
        for (i in 1.. 52) {
            deposit += principal
            weeks.add(Week(i, principal))
        }

        weeks_list = view.findViewById<RecyclerView>(R.id.list_weeks)

        weeks_list.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)

        weekAdapter = WeekAdapter(weeks, this.context)
        weeks_list.adapter = weekAdapter




        (view.findViewById<EditText>(R.id.amount_input)).addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(s: Editable) {

                    if(s.isEmpty()){
                        (view.findViewById<EditText>(R.id.amount_input)).setText("0")
                    }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {


                if(!s.isNullOrEmpty()){

                    var amount = s.toString().toBigInteger()

                    if(!(amount >= 0.toBigInteger() && amount <= 50000000.toBigInteger())){
                        (view.findViewById<TextInputLayout>(R.id.amount_layout)).error = "Invalid amount enters"
                        return
                    } else {
                        (view.findViewById<TextInputLayout>(R.id.amount_layout)).error = null
                    }

                    if(amount == 0.toBigInteger()){

                    }

                    weekAdapter.recalculate(s.toString().toInt())



                    total_saving.setText(weeks.last().total.toString())

                }




            }

        })



        // Inflate the layout for this fragment
        return view
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
