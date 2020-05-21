package vn.teko.test.utils

import android.content.Context
import android.graphics.Typeface
import java.util.*

object FontUtils {
    private val CACHE = Hashtable<String, Typeface>()

    @JvmStatic
    fun get(c: Context, path: String): Typeface? {
        synchronized(CACHE) {
            if (!CACHE.containsKey(path)) {
                try {
                    val t = Typeface.createFromAsset(c.assets, path)
                    CACHE[path] = t
                } catch (e: Exception) {
                    return null
                }
            }
            return CACHE[path]
        }
    }
}