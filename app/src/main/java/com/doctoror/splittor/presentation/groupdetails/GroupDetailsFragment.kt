package com.doctoror.splittor.presentation.groupdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.doctoror.splittor.BR
import com.doctoror.splittor.R
import com.doctoror.splittor.databinding.FragmentGroupDetailsBinding
import com.doctoror.splittor.databinding.ItemGroupMemberBinding
import com.doctoror.splittor.platform.recyclerview.BindingRecyclerAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class GroupDetailsFragment : Fragment() {

    private val args: GroupDetailsFragmentArgs by navArgs()

    private val adapter by lazy {
        BindingRecyclerAdapter<ItemGroupMemberBinding, GroupMemberItemViewModel>(
            layoutId = R.layout.item_group_member,
            layoutInflater = layoutInflater,
            modelId = BR.model,
        )
    }

    private var binding: FragmentGroupDetailsBinding? = null

    private val viewModel: GroupDetailsViewModel by viewModel()

    private val presenter: GroupDetailsPresenter by viewModel {
        parametersOf(args.groupId, viewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().title = null

        adapter
            .itemClickEvents
            .observe(this) { presenter.updateMemberPaidStatus(it.model.id, !it.model.paid) }

        lifecycle.addObserver(presenter)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGroupDetailsBinding.inflate(layoutInflater, container, false)
        return requireBinding().root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireBinding().recycler.adapter = adapter
        requireBinding().model = viewModel
    }

    override fun onStart() {
        super.onStart()
        viewModel.title.addOnPropertyChangedCallback(titlePropertyChangeCallback)
    }

    override fun onStop() {
        super.onStop()
        viewModel.title.removeOnPropertyChangedCallback(titlePropertyChangeCallback)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.unbind()
        binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(presenter)
    }

    private fun requireBinding() = binding!!

    private val titlePropertyChangeCallback = object : Observable.OnPropertyChangedCallback() {

        override fun onPropertyChanged(sender: Observable, propertyId: Int) {
            @Suppress("UNCHECKED_CAST")
            sender as ObservableField<CharSequence>
            requireActivity().title = sender.get()
        }
    }
}
