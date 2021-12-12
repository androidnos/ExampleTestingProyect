package com.example.exampletestingproyect.view

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.exampletestingproyect.R
import com.example.exampletestingproyect.view.fragment.first.FirstFragment
import com.example.exampletestingproyect.view.fragment.secund.SecondFragment

class MainActivity : AppCompatActivity() {

    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, FirstFragment())
            .addToBackStack(null)
            .commit()
        progressDialog = ProgressDialog(this)
    }

    fun showLoading() {
        progressDialog.setTitle(getString(R.string.loading_title))
        progressDialog.setMessage(getString(R.string.loading_message))
        progressDialog.setCancelable(false)
        progressDialog.show()
    }

    fun hidenLoading() {
        progressDialog.dismiss()
    }

    fun showError(runnable: Runnable) {
        hidenLoading()
        val builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.dialog_error_title)
            .setPositiveButton(R.string.acept_dialog_error,
                DialogInterface.OnClickListener { _, _ ->
                    runnable.run()
                })
        builder.create()
        builder.show()
    }

    fun goToSecundFragment(name: String) {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(FirstFragment::class.java.simpleName)
            .replace(R.id.frameLayout, SecondFragment(name))
            .commit()
    }

}