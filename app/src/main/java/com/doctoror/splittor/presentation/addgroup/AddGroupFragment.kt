package com.doctoror.splittor.presentation.addgroup

import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.databinding.Observable
import androidx.databinding.ObservableInt
import androidx.navigation.fragment.findNavController
import com.doctoror.splittor.BR
import com.doctoror.splittor.R
import com.doctoror.splittor.databinding.FragmentGroupAddBinding
import com.doctoror.splittor.databinding.ItemContactBinding
import com.doctoror.splittor.platform.recyclerview.BindingRecyclerAdapter
import com.doctoror.splittor.presentation.base.BaseFragment
import io.reactivex.rxjava3.subjects.PublishSubject
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class AddGroupFragment : BaseFragment() {

    private val contactPickedEvents = PublishSubject.create<Uri>()

    private val activityResultContractPickContact = ActivityResultContracts.PickContact()

    private val activityResultLauncherPickContact = registerForActivityResult(
        activityResultContractPickContact
    ) {
        it?.let(contactPickedEvents::onNext)
    }

    private var binding: FragmentGroupAddBinding? = null

    private val inputFieldsMonitor: AddGroupInputFieldsMonitor by viewModel()

    private val presenter: AddGroupPresenter by viewModel {
        parametersOf(contactPickedEvents, inputFieldsMonitor, viewModel)
    }

    private val viewModel: AddGroupViewModel by viewModel()

    private val viewModelUpdater: AddGroupViewModelUpdater by inject {
        parametersOf(viewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        if (savedInstanceState != null) {
            inputFieldsMonitor.onRestoreInstanceState(savedInstanceState)
            inputFieldsMonitor.contacts.forEach(viewModelUpdater::addContact)
        }

        presenter
            .groupInsertedEvents
            .subscribe {
                findNavController()
                    .navigate(AddGroupFragmentDirections.actionAddGroupToGroupDetails(groupId = it))
            }
            .disposeOnDestroy()

        lifecycle.addObserver(presenter)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.add_group, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.create -> presenter.createGroup().let { true }
        else -> super.onOptionsItemSelected(item)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGroupAddBinding.inflate(inflater, container, false)
        return requireBinding().root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireBinding().recycler.adapter =
            BindingRecyclerAdapter<ItemContactBinding, ContactDetailsViewModel>(
                layoutId = R.layout.item_contact,
                layoutInflater = layoutInflater,
                modelId = BR.model,
            )

        requireBinding().addContact.setOnClickListener { activityResultLauncherPickContact.launch() }
        requireBinding().model = viewModel
        requireBinding().monitor = inputFieldsMonitor
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        inputFieldsMonitor.onSaveInstanceState(outState)
    }

    override fun onStart() {
        super.onStart()
        viewModel.errorMessage.addOnPropertyChangedCallback(errorMessagePropertyChangeCallback)
    }

    override fun onStop() {
        super.onStop()
        viewModel.errorMessage.removeOnPropertyChangedCallback(errorMessagePropertyChangeCallback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(presenter)
    }

    private fun requireBinding() = binding!!

    private val errorMessagePropertyChangeCallback =
        object : Observable.OnPropertyChangedCallback() {

            override fun onPropertyChanged(sender: Observable, propertyId: Int) {
                sender as ObservableInt

                val message = sender.get()
                if (message != 0) {
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                    sender.set(0)
                }
            }
        }
}
