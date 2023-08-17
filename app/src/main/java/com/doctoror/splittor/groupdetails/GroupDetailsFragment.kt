package com.doctoror.splittor.groupdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.doctoror.splittor.presentation.groupdetails.GroupDetailsContent
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class GroupDetailsFragment : Fragment() {

    private val args: GroupDetailsFragmentArgs by navArgs()

    private val presenter: GroupDetailsPresenterWrapper by viewModel {
        parametersOf(args.groupId)
    }

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
        lifecycle.removeObserver(presenter.unwrapped)
    }
}
