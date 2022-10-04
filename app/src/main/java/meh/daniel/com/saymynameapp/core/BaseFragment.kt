package meh.daniel.com.saymynameapp.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<ViewModel: androidx.lifecycle.ViewModel, Binding : ViewBinding>(
    @LayoutRes layoutId: Int
) : Fragment(layoutId){

    protected abstract val viewModel: ViewModel
    private var _viewBinding: Binding? = null
    protected val binding get() = _viewBinding!!

    protected abstract fun initBinding(inflater: LayoutInflater, container: ViewGroup?) : Binding

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = initBinding(inflater, container)
        return binding.root
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
        setupSubscribers()
    }

    override fun onDestroyView() {
        _viewBinding = null
        super.onDestroyView()
    }

    protected open fun initialize() { }

    protected open fun setupListeners() { }

    protected open fun setupSubscribers() {}
}