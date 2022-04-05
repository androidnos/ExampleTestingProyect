package com.example.exampletestingproyect.view.fragment.first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exampletestingproyect.R
import com.example.exampletestingproyect.models.BreedModel
import com.example.exampletestingproyect.presenter.first.FirstPresenter
import com.example.exampletestingproyect.view.MainActivity
import com.example.exampletestingproyect.view.adapter.Adapter
import java.util.Locale

class FirstFragment : Fragment(), IFirstView {

    private lateinit var adapter: Adapter
    private val presenter = FirstPresenter()
    private lateinit var viewContiner: View
    private var mainActivity: MainActivity? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (activity is MainActivity) {
            mainActivity = activity as MainActivity
        }
        return inflater.inflate(R.layout.first_fragment_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewContiner = view
        presenter.attactView(this)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val recyclerView = viewContiner.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(viewContiner.context)
        adapter = Adapter(arrayListOf(), listener())
        recyclerView.adapter = adapter
    }

    override fun addListAdapter(list: List<BreedModel>) {
        adapter.updateList(list as ArrayList<BreedModel>)
    }

    override fun updateSublist(name: String, list: List<String>?) {
        if (list.isNullOrEmpty()) {
            mainActivity?.goToSecundFragment(name.lowercase(Locale.getDefault()))
        } else {
            adapter.updateBreedList(name, list)
        }
    }

    override fun showLoading() {
        mainActivity?.showLoading()
    }

    override fun hidenLoading() {
        mainActivity?.hidenLoading()
    }

    override fun showError() {
        mainActivity?.showError { presenter.callAllList() }
    }

    private fun listener() = object : Adapter.ListenerAdapter {
        override fun click(itemString: String) {
            presenter.callSubBreed(itemString)
        }

        override fun clickSubList(name: String, itemString: String) {
            mainActivity?.goToSecundFragment(
                "${name.lowercase(Locale.getDefault())}-${
                    itemString.lowercase(Locale.getDefault())
                }"
            )
        }
    }
}