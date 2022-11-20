package billy.lab.interesting

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SQLiteHelper(
    private var context: Context
) : SQLiteOpenHelper(context, Setting.DB_NAME, null, Setting.VERSION) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(Setting.CREATE_TABLE)
        p0?.insert(Setting.TABLE_NAME, null, ContentValues().apply {
            put(Setting.ACCOUNT_ID, 1)
            put(Setting.ACCOUNT_USERNAME, "default")
            put(Setting.ACCOUNT_PASSWORD, "default")
        })
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}