package com.example.githubusers

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GithubUsers(
    var name: String?,
    var detail: String?,
    var photo: Int?,
    var following: String?,
    var follower: String?,
    var company: String?,
    var location: String?,
    var repository: String?
) : Parcelable