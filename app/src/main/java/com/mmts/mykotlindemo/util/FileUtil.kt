package com.mmts.mykotlindemo.util

import android.content.Context

/**
 * @author: As
 * @date: 2022-02-28
 */
class FileUtil {
    companion object {
        fun getAssetPodcasts(fileName: String, context: Context): String? {
            return context.assets.open(fileName).bufferedReader().use { it.readText() }
        }
    }

}