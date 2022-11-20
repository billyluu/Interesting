package billy.lab.interesting

import android.content.ContentValues
import android.content.Context
import android.net.Uri

object ContentProviderManager {

    data class Account(
        val username: String,
        val password: String
    )

    fun setAccount(context: Context, uri: Uri, account: Account): Uri {
        return context.contentResolver.insert(uri, ContentValues().apply {
            put(Setting.ACCOUNT_ID, uri.lastPathSegment)
            put(Setting.ACCOUNT_USERNAME, account.username)
            put(Setting.ACCOUNT_PASSWORD, account.password)
        })!!
    }

    fun getAccount(context: Context, uri: Uri): Account? {
        context.contentResolver.query(uri, null, null, null)?.let {
            it.moveToFirst()
            return Account(
                it.getString(Setting.ACCOUNT_USERNAME_COLUM),
                it.getString(Setting.ACCOUNT_PASSWORD_COLUM)
            )
        }
        return null
    }

    fun updateAccount(context: Context, uri: Uri, account: Account): Int {
        return context.contentResolver.update(uri,
            ContentValues().apply {
                put(Setting.ACCOUNT_USERNAME, account.username)
                put(Setting.ACCOUNT_PASSWORD, account.password)
            }, null, null
        )
    }

    fun deleteAccount(context: Context, uri: Uri): Int {
        return context.contentResolver.delete(uri, null, null)
    }
}