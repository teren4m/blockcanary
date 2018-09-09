/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.blockcanary

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import java.io.FileInputStream
import java.io.IOException


class DemoFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_main, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button1 = view.findViewById<View>(R.id.button1) as Button
        val button2 = view.findViewById<View>(R.id.button2) as Button
        val button3 = view.findViewById<View>(R.id.button3) as Button

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.button1 -> try {
                Thread.sleep(2000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
                Log.e(DEMO_FRAGMENT, "onClick of R.id.button1: ", e)
            }

            R.id.button2 -> for (i in 0..99) {
                readFile()
            }
            R.id.button3 -> {
                val result = compute()
                println(result)
            }
            else -> {
            }
        }
    }

    companion object {

        private val DEMO_FRAGMENT = "DemoFragment"

        fun newInstance(): DemoFragment {
            return DemoFragment()
        }

        private fun compute(): Double {
            var result = 0.0
            for (i in 0..999999) {
                result += Math.acos(Math.cos(i.toDouble()))
                result -= Math.asin(Math.sin(i.toDouble()))
            }
            return result
        }

        private fun readFile() {
            var reader: FileInputStream? = null
            try {
                reader = FileInputStream("/proc/stat")
                while (reader.read() != -1);
            } catch (e: IOException) {
                Log.e(DEMO_FRAGMENT, "readFile: /proc/stat", e)
            } finally {
                if (reader != null) {
                    try {
                        reader.close()
                    } catch (e: IOException) {
                        Log.e(DEMO_FRAGMENT, " on close reader ", e)
                    }

                }
            }
        }
    }
}
