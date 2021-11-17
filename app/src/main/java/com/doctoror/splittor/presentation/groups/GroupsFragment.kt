package com.doctoror.splittor.presentation.groups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.doctoror.splittor.databinding.FragmentGroupsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class GroupsFragment : Fragment() {

    private var binding: FragmentGroupsBinding? = null

    private val viewModel: GroupsViewModel by viewModel()

    private val presenter: GroupsPresenter by viewModel {
        parametersOf(viewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(presenter)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGroupsBinding.inflate(layoutInflater, container, false)
        binding!!.model = viewModel
        return binding!!.root
    }
}
