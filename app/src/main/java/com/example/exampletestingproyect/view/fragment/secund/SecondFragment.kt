package com.example.exampletestingproyect.view.fragment.secund

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.exampletestingproyect.R
import com.example.exampletestingproyect.presenter.second.SecondPresenter
import com.example.exampletestingproyect.view.MainActivity
import com.squareup.picasso.Picasso
import java.util.Locale

class SecondFragment(val name: String): Fragment(), ISecondView {

    private lateinit var imageView: ImageView
    private val presenter = SecondPresenter()
    private var mainActivity: MainActivity? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (activity is MainActivity){
            mainActivity = activity as MainActivity
        }
        return inflater.inflate(R.layout.second_fragment_layout,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageView = view.findViewById(R.id.imageImageView)
        presenter.attactView(this)
        presenter.callBreedImage(name)
        val nameString = name.replace("-"," ")
        nameString.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        val textView = view.findViewById<TextView>(R.id.nameTextView)
        textView.text = nameString
    }

    override fun initImageView(urlImage: String) {
        Picasso.get().load(urlImage).into(imageView)
    }

    override fun showLoading() {
        mainActivity?.showLoading()
    }

    override fun hidenLoading() {
        mainActivity?.hidenLoading()
    }

    override fun showError() {
        mainActivity?.showError { presenter.callBreedImage(name) }
    }
}