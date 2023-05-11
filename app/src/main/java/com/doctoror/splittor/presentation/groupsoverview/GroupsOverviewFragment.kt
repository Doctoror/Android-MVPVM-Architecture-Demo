package com.doctoror.splittor.presentation.groupsoverview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class GroupsOverviewFragment : Fragment() {

    private val viewModel: GroupsOverviewViewModel by viewModel()

    private val presenter: GroupsOverviewPresenter by viewModel {
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
    ): View = ComposeView(requireContext()).apply {
        setContent {
            GroupsOverviewContent(
                onAddClick = ::navigateToAddGroup,
                onGroupClick = {
                    findNavController().navigate(
                        GroupsOverviewFragmentDirections.actionGroupsOverviewToGroupDetails(it)
                    )
                },
                viewModel = viewModel
            )
        }
    }

    private fun navigateToAddGroup() {
        findNavController().navigate(GroupsOverviewFragmentDirections.actionGroupsToAddGroup())
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(presenter)
    }
}
