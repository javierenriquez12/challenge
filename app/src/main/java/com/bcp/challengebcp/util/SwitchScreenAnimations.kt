package com.bcp.challengebcp.util

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.bcp.challengebcp.R

inline fun <reified T: Any> AppCompatActivity.nextScreenInOut(nextActivity: Class<T>) {
    finish()
    startActivity(Intent(this, nextActivity))
    overridePendingTransition(R.anim.fade_in_next_screen, R.anim.fade_out_next_screen)
}


