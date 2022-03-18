package com.example.mycalculator

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.lang.Exception
import net.objecthunter.exp4j.ExpressionBuilder


class MainActivity : AppCompatActivity() {
    lateinit var outputTextView: TextView
    var lastNumaric: Boolean= false
    var stateError: Boolean = false
    //var lastDot :Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()//for hiding taskbar
        outputTextView=findViewById(R.id.txtView)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)//for screen rotation off
    }

    fun NumberButton(view: View) {
        if(stateError){
            outputTextView.text=""
            outputTextView.append((view as Button).text)
            stateError=false
        }else{
            outputTextView.append((view as Button).text)
        }
        lastNumaric=true
    }

    fun Operation(view: View) {
        if(lastNumaric && !stateError){
            outputTextView.append((view as Button).text)
            lastNumaric=false
        }
    }
    fun Equal(view: View) {
        val text=outputTextView.text.toString()
        val expression= ExpressionBuilder(text).build()
        try {
            val res=expression.evaluate().toString()
            if(res=="0") {
                outputTextView.text="0"
            }else{
                val result=expression.evaluate()
                outputTextView.text=result.toString()
            }
        }catch (e: Exception){
            outputTextView.text="Hey Buddy! You Entered Wrong Expression "
            stateError=true
            lastNumaric=false
        }
    }
    fun Clear(view: View) {
        this.outputTextView.text="0"
        lastNumaric=false
        stateError=false
    }

}