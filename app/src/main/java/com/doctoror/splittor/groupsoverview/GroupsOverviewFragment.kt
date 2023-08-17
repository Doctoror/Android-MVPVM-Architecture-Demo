package com.doctoror.splittor.groupsoverview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.doctoror.splittor.presentation.groupsoverview.GroupsOverviewContent
import org.koin.androidx.viewmodel.ext.android.viewModel

class GroupsOverviewFragment : Fragment() {

    private val presenter: GroupsOverviewPresenterWrapper by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(presenter.unwrapped)
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
                viewModel = presenter.unwrapped.viewModel
            )
        }
    }

    private fun navigateToAddGroup() {
        findNavController().navigate(GroupsOverviewFragmentDirections.actionGroupsToAddGroup())
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(presenter.unwrapped)
    }
}
