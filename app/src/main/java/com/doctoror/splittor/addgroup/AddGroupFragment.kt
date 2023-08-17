package com.doctoror.splittor.addgroup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.doctoror.splittor.domain.numberformat.ProvideCurrencySymbolUseCase
import com.doctoror.splittor.presentation.addgroup.AddGroupContent
import com.doctoror.splittor.presentation.addgroup.AddGroupPresenter
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.Locale

class AddGroupFragment : Fragment() {

    private val activityResultContractPickContact = ActivityResultContracts.PickContact()

    private val activityResultLauncherPickContact = registerForActivityResult(
        activityResultContractPickContact
    ) { uri ->
        uri?.let { presenter.handleContactPick(it.toString()) }
    }

    private val locale: Locale by inject()

    private val presenter: AddGroupPresenter by viewModel()

    private val provideCurrencySymbolUseCase: ProvideCurrencySymbolUseCase by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            presenter
                .viewModel
                .errorMessage
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    if (it != 0) {
                        presenter.viewModel.errorMessage.emit(0)
                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                    }
                }
        }

        lifecycleScope.launch {
            presenter
                .groupInsertedEvents
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect {
                    findNavController()
                        .navigate(AddGroupFragmentDirections.actionAddGroupToGroupDetails(groupId = it))
                }
        }

        lifecycle.addObserver(presenter)
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            AddGroupContent(
                currencySymbol = provideCurrencySymbolUseCase(),
                locale = locale,
                onAmountChange = presenter::handleAmountChange,
                onAddContactClick = { activityResultLauncherPickContact.launch(null) },
                onCreateClick = { presenter.createGroup() },
                onNavigationClick = { findNavController().popBackStack() },
                onTitleChange = presenter::handleTitleChange,
                viewModel = presenter.viewModel
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(presenter)
    }
}
