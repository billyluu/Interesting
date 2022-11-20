package billy.lab.interesting

import android.net.Uri

object Setting {

    const val AUTHORITY = "billy.lab.interesting.provider"
    const val PATH = "/account"
    val CONTENT_URI = Uri.parse("content://$AUTHORITY$PATH")

    const val CONTENT_PHONEBOOK_LIST = "vnd.android.cursor.dir/vnd.billy.lab.interesting.provider"
    const val CONTENT_PHONEBOOK_ITEM = "vnd.android.cursor.item/vnd.billy.lab.interesting.provider"


    const val DB_NAME = "mydb.db"
    const val VERSION = 1

    const val TABLE_NAME = "account"
    const val ACCOUNT_ID = "_id"
    const val ACCOUNT_ID_COLUM = 0
    const val ACCOUNT_USERNAME = "username"
    const val ACCOUNT_USERNAME_COLUM = 1
    const val ACCOUNT_PASSWORD = "password"
    const val ACCOUNT_PASSWORD_COLUM = 2

    const val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME
            + "(" + ACCOUNT_ID + " INTEGER PRIMARY KEY,"
            + ACCOUNT_USERNAME + " TEXT,"
            + ACCOUNT_PASSWORD + " TEXT" + ")")

}