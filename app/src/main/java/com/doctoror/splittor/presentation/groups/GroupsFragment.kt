package com.doctoror.splittor.presentation.groups

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.doctoror.splittor.BR
import com.doctoror.splittor.R
import com.doctoror.splittor.databinding.FragmentGroupsBinding
import com.doctoror.splittor.databinding.ItemGroupBinding
import com.doctoror.splittor.platform.recyclerview.BindingRecyclerAdapter
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
        return requireBinding().root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireBinding().fab.setOnClickListener {
            navigateToAddGroup()
        }

        requireBinding().fragmentGroupsEmpty.addFirstGroup.setOnClickListener {
            navigateToAddGroup()
        }

        requireBinding().fragmentGroupsContent.recycler.adapter =
            BindingRecyclerAdapter<ItemGroupBinding>(
                layoutId = R.layout.item_group,
                layoutInflater = layoutInflater,
                modelId = BR.model,
            )

        requireBinding().model = viewModel
    }

    private fun navigateToAddGroup() {
        findNavController().navigate(R.id.actionGroupsToAddGroup)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.unbind()
        binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(presenter)
    }

    private fun requireBinding() = binding!!
}
