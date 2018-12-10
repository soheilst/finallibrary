package com.keylid.kst

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.keylid.keylidsdk.Charkh
import com.keylid.keylidsdk.Force
import com.keylid.keylidsdk.publicvar
import com.keylid.keylidsdk.subapi
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val pu = publicvar()
        pu.context = this
        pu.Sku = "kst_daily_subscription"
        pu.base64st ="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQChNG2/B6L/2TV2jMo7rdTjCjRbUaME3dnSqTJZYcIZX645ephwbLDaGt7c6o9bX8bFA5Nasq19YSsH561iXErkeT6Y0HXmn4qltJwzI7o6fuaM6KVJilFTygTpaXrypAKN8Z4Pb5p+//16//dfTHamPFx5WNDE2SpnbI3c/nFNIwIDAQAB"
        val fn = Force(this)
        val ch = Charkh(pu)
        ch.setup(pu.context, pu.base64st)
        /// ch.msg = ""
        val g = arrayOf("")
        g[0] = "989393142536"
        //g[0] = "989122895019"

        val cm = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val nInfo = cm.activeNetworkInfo
        val connected = nInfo != null && nInfo.isConnected
        btn1.setOnClickListener {
            val r = Random()
            //pu.RC_REQUEST=r.nextInt()
            pu.RC_REQUEST = 765

            pu.payload = "test4st" + pu.RC_REQUEST.toString()
            if (connected) {
                val sp = subapi(pu, ch)
                sp.sub("test", "testtoken", g[0], object : subapi.OKHttpNetwork {
                    override fun onSuccess(body: String) {
                        runOnUiThread {
                            //show the response body
                            Toast.makeText(this@MainActivity, body, Toast.LENGTH_SHORT).show()
                            txt.setText(body);
                        }
                    }


                    override fun onFailure(body: String) {
                        txt.setText(body)
                    }
                })
            } else {
                txt.setText("ارتباط با شبکه را چک کنید")
            }
        }
        btn2.setOnClickListener {

            if (connected) {

                val sp = subapi(pu, ch)
                sp.confirm("test", "testtoken", g[0], "0000", object : subapi.OKHttpNetwork {
                    override fun onSuccess(body: String) {
                        runOnUiThread {
                            //show the response body
                            Toast.makeText(this@MainActivity, body, Toast.LENGTH_SHORT).show()
                            txt.setText(body);
                        }
                    }


                    override fun onFailure(body: String) {
                        txt.setText(body)
                    }
                })
            } else {
                txt.setText("ارتباط با شبکه را چک کنید")
            }
        }

        charck.setOnClickListener {
            ch.mchar()

        }
        Status.setOnClickListener {
            val r = Random()
            pu.RC_REQUEST = r.nextInt()
            pu.payload = "test4st" + pu.RC_REQUEST.toString()
            val sp = subapi(pu, ch)
            sp.getstatus("test", "testtoken", g[0], object : subapi.OKHttpNetwork {
                override fun onSuccess(body: String) {
                    runOnUiThread {
                        //show the response body
                        Toast.makeText(this@MainActivity, body, Toast.LENGTH_SHORT).show()
                        txt.setText(body);
                    }
                }


                override fun onFailure(body: String) {
                    txt.setText(body)
                }
            })
        }

    }
}
