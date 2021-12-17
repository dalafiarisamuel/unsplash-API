package ng.devtamuno.unsplash.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import ng.devtamuno.unsplash.data.ImagePagingSource
import ng.devtamuno.unsplash.networking.ApiInterface
import javax.inject.Inject

class ImageRepository @Inject constructor(private val api: ApiInterface) {

    fun getImageSearchResult(query: String) = Pager(
        config = PagingConfig(
            pageSize = 20,
            maxSize = 200,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { ImagePagingSource(api = api, query = query) }
    ).liveData
}