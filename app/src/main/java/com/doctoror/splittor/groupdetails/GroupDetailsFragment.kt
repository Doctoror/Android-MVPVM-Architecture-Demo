package com.doctoror.splittor.groupdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.doctoror.splittor.presentation.groupdetails.GroupDetailsPresenter
import com.doctoror.splittor.presentation.groupdetails.GroupDetailsViewModel
import com.doctoror.splittor.ui.groupdetails.GroupDetailsContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GroupDetailsFragment : Fragment() {

    private val presenterW: GroupDetailsPresenterWrapper by viewModels()

    private val presenter: GroupDetailsPresenter by lazy { presenterW.unwrapped }

    private val viewModel: GroupDetailsViewModel by lazy { presenter.viewModel }

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
            GroupDetailsContent(
                onBackClick = { findNavController().popBackStack() },
                onMemberClick = { id, paid -> presenter.updateMemberPaidStatus(id, !paid) },
                viewModel = viewModel
            )
        }
    }
}
