package com.saveo.assignment.views.activities

import android.content.Context
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VDB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var mainViewDatBinding: VDB

    fun setFullScreen(fullscreen: Boolean) {
        if (fullscreen) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

    /**
     * Not all screens need ViewModel. Method setupViewModel() will be implemented in subclasses
     * that has associated ViewModel(s)
     */
    protected abstract fun setupViewModel()

    /**
     * Method to setup view binding
     * @param layoutResourceId pass layout id as parameter
     */
    protected open fun setUpContentView(@LayoutRes layoutResourceId: Int) {
        mainViewDatBinding = DataBindingUtil.setContentView(this, layoutResourceId)
    }

    /**
     * Method to hide key board
     */
    fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(
            this.window.decorView.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    /**
     * To hide tool bar title
     */
    fun hideToolbarTitle() {
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    /**
     * To set tool bar title
     */
    fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

}