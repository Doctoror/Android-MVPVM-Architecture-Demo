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
import com.doctoror.splittor.presentation.addgroup.AddGroupPresenter
import com.doctoror.splittor.presentation.addgroup.AddGroupViewModel
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
        uri?.let { presenter.handleContactPick(it.toString()) }
    }

    private val instanceStateHandler = AddGroupInstanceStateHandler()

    @Inject
    lateinit var locale: Locale

    @Inject
    lateinit var provideCurrencySymbolUseCase: ProvideCurrencySymbolUseCase

    private val presenterW: AddGroupPresenterWrapper by viewModels()

    private val presenter: AddGroupPresenter by lazy { presenterW.unwrapped }

    private val viewModel: AddGroupViewModel by lazy { presenter.viewModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.run {
            instanceStateHandler.onRestoreInstanceState(viewModel, this)
        }

        lifecycleScope.launch {
            viewModel
                .errorMessage
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .filter { it.isPresent }
                .collect {
                    viewModel.errorMessage.emit(Optional.empty())
                    Toast.makeText(requireContext(), it.get(), Toast.LENGTH_SHORT).show()
                }
        }

        lifecycleScope.launch {
            presenter
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

        presenter.dispatchOnCreateIfNotCreated()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        instanceStateHandler.onSaveInstanceState(viewModel, outState)
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
                viewModel = viewModel
            )
        }
    }
}
