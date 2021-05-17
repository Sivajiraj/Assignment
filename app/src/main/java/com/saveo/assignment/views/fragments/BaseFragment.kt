package com.saveassignment.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

import androidx.appcompat.app.ActionBar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.saveo.assignment.views.activities.BaseActivity

abstract class BaseFragment<VDB : ViewDataBinding> : Fragment() {

    protected lateinit var viewDataBinding: VDB
    protected var actionBar: ActionBar? = null

    protected open fun setUpContentView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        @LayoutRes layoutResourceId: Int
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        setupViewModel()
        return viewDataBinding.root
    }

    protected abstract fun setupViewModel()

    protected abstract fun setupObservers()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        actionBar = (activity as BaseActivity<*>).supportActionBar
    }

    override fun onStart() {
        super.onStart()
        configureToolbar(actionBar)
    }

    protected open fun configureToolbar(toolbarConfig: ActionBar?) {
    }

}
