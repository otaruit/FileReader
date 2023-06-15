package com.android.example.filereader

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var `is`: InputStream? = null
        var br: BufferedReader? = null
        val list = mutableListOf<List<String>>()

        try {
            try {
                // assetsフォルダ内の sample.txt をオープンする
                `is` = this.assets.open("sample.txt")
                br = BufferedReader(InputStreamReader(`is`))
                var listPortion: List<String>

                // １行ずつ読み込み、改行を付加する
                var str: String
                while (br.readLine().also { str = it } != null) {
                    listPortion = listOf(*str.split(",").toTypedArray())
                    list.add(listPortion)
                    println(list)
                }

            } finally {
                `is`?.close()
                br?.close()
            }
        } catch (e: Exception) {
            println(e)
        }


        // 読み込んだ文字列を EditText に設定し、画面に表示する
        val editText = findViewById<View>(R.id.text_view) as TextView
        editText.text = list.toString()
        println(list)
    }
}
