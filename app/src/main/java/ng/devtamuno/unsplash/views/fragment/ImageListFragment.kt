package ng.devtamuno.unsplash.views.fragment

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ng.devtamuno.blurhash.BlurHash
import ng.devtamuno.unsplash.R
import ng.devtamuno.unsplash.adapter.ImageAdapter
import ng.devtamuno.unsplash.adapter.ImageLoaderStateAdapter
import ng.devtamuno.unsplash.databinding.ImageListFragmentBinding
import ng.devtamuno.unsplash.model.UnsplashPhoto
import ng.devtamuno.unsplash.networking.ConnectionDetector
import ng.devtamuno.unsplash.util.hideKeyboard
import ng.devtamuno.unsplash.viewmodel.ImageListViewModel
import ng.devtamuno.unsplash.views.common.MessageDialogManager
import ng.devtamuno.unsplash.views.common.ProgressDialogManager
import javax.inject.Inject

@AndroidEntryPoint
class ImageListFragment : Fragment(R.layout.image_list_fragment), ImageAdapter.OnItemClickListener {

    private val binding by viewBinding(ImageListFragmentBinding::bind)

    private val viewModel: ImageListViewModel by viewModels()

    @Inject
    lateinit var connectionDetector: ConnectionDetector

    @Inject
    lateinit var progressDialog: ProgressDialogManager

    @Inject
    lateinit var messageDialog: MessageDialogManager

    @Inject
    lateinit var blurHash: BlurHash

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backArrow.setOnClickListener { it.findNavController().popBackStack() }

        val adapter = ImageAdapter(blurHash, this)

        binding.apply {
            recyclerview.adapter = adapter.withLoadStateFooter(
                footer = ImageLoaderStateAdapter { adapter.retry() }
            )
            recyclerview.setHasFixedSize(true)
            recyclerview.itemAnimator = null
            recyclerview.layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

            searchButton.setOnClickListener {
                performSearch(binding.panInputField.text.toString())
            }

            panInputField.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    performSearch(binding.panInputField.text.toString())
                }
                true
            }

            viewLifecycleOwner.lifecycleScope.launch {
                adapter.loadStateFlow
                    .collectLatest {
                        if (it.refresh is LoadState.NotLoading) {
                            binding.noResultDisplay.isVisible = adapter.itemCount < 1
                            progressDialog.dismissDialog()
                        }

                        if (it.refresh is LoadState.Loading) {
                            progressDialog.showLoading(getString(R.string.loading))
                        }
                    }
            }

            binding.popularGroup.setOnCheckedChangeListener { group, checkedId ->
                val query = (group.findViewById(checkedId) as Chip?)?.text ?: ""
                binding.panInputField.setText(query)
                performSearch(query.toString())
            }

        }

        viewModel.photos.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }

    }

    private fun performSearch(query: String) {
        requireActivity().hideKeyboard()
        if (connectionDetector.isConnectingToInternet()) {
            viewModel.searchPhotos(query)
        } else {
            messageDialog.displayMessage(getString(R.string.no_internet))
        }
    }

    override fun onItemClick(photo: UnsplashPhoto?) {
        ImagePreviewFragment.newInstance(photo).also {
            it.show(childFragmentManager, ImagePreviewFragment.TAG)
        }
    }

    override fun onItemLongClicked(photo: UnsplashPhoto?) {

    }

    override fun onDestroy() {
        super.onDestroy()
        blurHash.clean()

    }
}