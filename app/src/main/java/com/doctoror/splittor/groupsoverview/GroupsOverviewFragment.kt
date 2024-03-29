package com.doctoror.splittor.groupsoverview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.doctoror.splittor.ui.groupsoverview.GroupsOverviewContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupsOverviewFragment : Fragment() {

    private val presenterW: GroupsOverviewPresenterWrapper by viewModels()

    private val presenter by lazy { presenterW.unwrapped }

    private val viewModel by lazy { presenter.viewModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.dispatchOnCreateIfNotCreated()
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
                onGroupLongClick = { presenter.onGroupLongClick(it) },
                viewModel = viewModel
            )
        }
    }

    private fun navigateToAddGroup() {
        findNavController().navigate(GroupsOverviewFragmentDirections.actionGroupsToAddGroup())
    }
}
