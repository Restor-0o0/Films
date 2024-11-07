package com.example.films.util

import android.content.Context
import com.example.films.R


fun Context.noNetworkConnectivityError() : APIResult.Error{
    return APIResult.Error(Exception(this.resources.getString(R.string.no_network_connect)))
}