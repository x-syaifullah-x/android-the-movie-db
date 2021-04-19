package id.xxx.the.movie.db.domain.model

import android.os.Parcelable
import id.xxx.the.movie.db.domain.model.base.IModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class SearchModel(
    override val id: Int?,
    val mediaType: String?,
    val title: String?
) : IModel<Int>, Parcelable