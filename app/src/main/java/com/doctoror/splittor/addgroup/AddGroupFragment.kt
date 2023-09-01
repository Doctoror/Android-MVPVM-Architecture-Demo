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
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.doctoror.splittor.domain.numberformat.ProvideCurrencySymbolUseCase
import com.doctoror.splittor.ui.addgroup.AddGroupContent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import java.util.Locale
import java.util.Optional
import javax.inject.Inject

@AndroidEntryPoint
class AddGroupFragment : Fragment() {

    private val activityResultContractPickContact = ActivityResultContracts.PickContact()

    private val activityResultLauncherPickContact = registerForActivityResult(
        activityResultContractPickContact
    ) { uri ->
        uri?.let { presenter.unwrapped.handleContactPick(it.toString()) }
    }

    @Inject
    lateinit var locale: Locale

    @Inject
    lateinit var provideCurrencySymbolUseCase: ProvideCurrencySymbolUseCase

    private val presenter: AddGroupPresenterWrapper by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            presenter
                .unwrapped
                .viewModel
                .errorMessage
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .filter { it.isPresent }
                .collect {
                    presenter.unwrapped.viewModel.errorMessage.emit(Optional.empty())
                    Toast.makeText(requireContext(), it.get(), Toast.LENGTH_SHORT).show()
                }
        }

        lifecycleScope.launch {
            presenter
                .unwrapped
                .groupInsertedEvents
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .filter { it.isPresent }
                .collect {
                    findNavController()
                        .navigate(
                            AddGroupFragmentDirections
                                .actionAddGroupToGroupDetails(groupId = it.get())
                        )
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
                onAmountChange = presenter.unwrapped::handleAmountChange,
                onAddContactClick = { activityResultLauncherPickContact.launch(null) },
                onCreateClick = { presenter.unwrapped.createGroup() },
                onNavigationClick = { findNavController().popBackStack() },
                onTitleChange = presenter.unwrapped::handleTitleChange,
                viewModel = presenter.unwrapped.viewModel
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(presenter)
    }
}
