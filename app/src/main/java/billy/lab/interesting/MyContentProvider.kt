package billy.lab.interesting

import android.content.ContentProvider
import android.content.ContentUris
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.util.Log

class MyContentProvider : ContentProvider() {

    private lateinit var sqLiteHelper: SQLiteHelper
    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(Setting.AUTHORITY, Setting.PATH, 1)
        addURI(Setting.AUTHORITY, "${Setting.PATH}/#", 2)
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        val db = sqLiteHelper.writableDatabase
        return db.delete(Setting.TABLE_NAME, "${Setting.ACCOUNT_ID}=${uri.pathSegments[1]}", selectionArgs)
    }

    override fun getType(uri: Uri): String? {
        return when (uriMatcher.match(uri)) {
            1 -> Setting.CONTENT_PHONEBOOK_LIST
            2 -> Setting.CONTENT_PHONEBOOK_ITEM
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        val db = sqLiteHelper.writableDatabase

        db.insert(Setting.TABLE_NAME, null, values)
            .takeIf { it > 0 }
            .apply {
                context?.let {
                    val cUri = ContentUris.withAppendedId(Setting.CONTENT_URI, this!!)
                    it.contentResolver.notifyChange(cUri, null)
                }
                return uri
            }
    }

    override fun onCreate(): Boolean {
        sqLiteHelper = SQLiteHelper(context!!)
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        val db = sqLiteHelper.readableDatabase
        return when (uriMatcher.match(uri)) {
            1 -> {
                db.query(Setting.TABLE_NAME, projection, selection, selectionArgs, null, null, null).let {
                    it.setNotificationUri(context!!.contentResolver, uri)
                    it
                }
            }
            2 -> {
                val where = Setting.ACCOUNT_ID + " = " + uri.lastPathSegment
                db.query(Setting.TABLE_NAME, projection, where, selectionArgs, null, null, null).let {
                    it.setNotificationUri(context!!.contentResolver, uri)
                    it
                }
            }
            else -> throw IllegalArgumentException("Unknown Uri: $uri")
        }
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        val db = sqLiteHelper.writableDatabase
        return when (uriMatcher.match(uri)) {
            1 -> {
                db.update(Setting.TABLE_NAME, values, selection, selectionArgs)
            }
            2 -> {
                db.update(Setting.TABLE_NAME, values, "${Setting.ACCOUNT_ID}=${uri.lastPathSegment}", selectionArgs)
            }
            else -> throw IllegalArgumentException("Unknown Uri: $uri");
        }
    }
}