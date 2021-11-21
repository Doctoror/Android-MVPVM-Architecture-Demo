package com.doctoror.splittor.presentation.groupdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.doctoror.splittor.BR
import com.doctoror.splittor.R
import com.doctoror.splittor.databinding.FragmentGroupDetailsBinding
import com.doctoror.splittor.databinding.ItemGroupMemberBinding
import com.doctoror.splittor.platform.recyclerview.BindingRecyclerAdapter
import com.doctoror.splittor.presentation.base.BaseFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class GroupDetailsFragment : BaseFragment() {

    private val args: GroupDetailsFragmentArgs by navArgs()

    private var binding: FragmentGroupDetailsBinding? = null

    private val viewModel: GroupDetailsViewModel by viewModel()

    private val presenter: GroupDetailsPresenter by viewModel {
        parametersOf(args.groupId, viewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        requireBinding().recycler.adapter =
            BindingRecyclerAdapter<ItemGroupMemberBinding, GroupMemberItemViewModel>(
                layoutId = R.layout.item_group_member,
                layoutInflater = layoutInflater,
                modelId = BR.model,
            )

        requireBinding().model = viewModel
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
}
