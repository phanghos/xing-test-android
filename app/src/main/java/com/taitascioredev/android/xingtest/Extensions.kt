package com.taitascioredev.android.xingtest

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.support.design.widget.Snackbar
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by rrtatasciore on 19/01/18.
 */
fun Activity.navigateToUrl(url: String?) {
    url?.let { startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url))) }
}

fun snackbar(container: View, msg: String?, duration: Int = Snackbar.LENGTH_SHORT) {
    if (container != null && msg != null) {
        Snackbar.make(container, msg, Snackbar.LENGTH_SHORT).show()
    }
}

inline fun <reified T> Gson.fromJson(json: String) = this.fromJson<T>(json, object: TypeToken<T>() {}.type)