package billy.lab.interesting

import android.content.ContentValues
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View

class MainActivity : AppCompatActivity()  {

    private val account by lazy {
        ContentProviderManager.Account(
            "billy666",
            "QAZWSX1234"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        ContentProviderManager.setAccount(this, Setting.CONTENT_URI, account)
//
//
        contentResolver.query(Setting.CONTENT_URI, null, null, null)?.let {
            Log.i("####", "count: ${it.count}")

            while (it.moveToNext()) {
                Log.i("####", "id=${it.getInt(0)}")
            }
        }
    }

}