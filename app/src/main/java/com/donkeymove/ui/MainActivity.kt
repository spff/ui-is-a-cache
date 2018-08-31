package com.donkeymove.ui

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.util.LogPrinter
import android.view.MotionEvent
import android.view.MotionEvent.ACTION_MOVE
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.info

class MainActivity : AppCompatActivity(), AnkoLogger {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val handler = Activity::class.java.declaredFields.findLast { it.name == "mHandler" }
                .also { it!!.isAccessible = true }!!.get(this as Activity) as Handler

        button.setOnClickListener {
            debug { "begin" }
            text_view.text = "${text_view.text} BLUE BUTTON CLICKED\n"
            Thread.sleep(1000)
            it.visibility = View.GONE
            handler.dump(LogPrinter(Log.DEBUG, "TAG"), "")
            debug { "after" }
        }
        button2.setOnClickListener { text_view.text = "${text_view.text} GREEN BUTTON CLICKED\n" }
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        ev?.takeIf { it.action != ACTION_MOVE }?.apply { info { toString() } }
        return super.dispatchTouchEvent(ev)
    }
}
