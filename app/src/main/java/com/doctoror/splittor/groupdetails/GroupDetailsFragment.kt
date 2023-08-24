package com.doctoror.splittor.groupdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.doctoror.splittor.ui.groupdetails.GroupDetailsContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupDetailsFragment : Fragment() {

    private val presenter: GroupDetailsPresenterWrapper by viewModels()

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
            GroupDetailsContent(
                onBackClick = { findNavController().popBackStack() },
                onMemberClick = { id, paid ->
                    presenter.unwrapped.updateMemberPaidStatus(id, !paid)
                },
                viewModel = presenter.unwrapped.viewModel
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(presenter)
    }
}
