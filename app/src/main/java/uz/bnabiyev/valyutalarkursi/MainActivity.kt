package uz.bnabiyev.valyutalarkursi

import android.annotation.SuppressLint
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import uz.bnabiyev.valyutalarkursi.adapters.RvAdapter
import uz.bnabiyev.valyutalarkursi.databinding.ActivityMainBinding
import uz.bnabiyev.valyutalarkursi.models.Valyuta
import java.text.SimpleDateFormat
import java.util.Date

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var requestQueue: RequestQueue
    private val url = "http://cbu.uz/uzc/arkhiv-kursov-valyut/json/"
    private lateinit var rvAdapter: RvAdapter

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        requestQueue = Volley.newRequestQueue(this)

        if (!isNetworkConnected()) {
            Toast.makeText(this, "Internetni ulang va qaytadan kiring", Toast.LENGTH_SHORT).show()
        } else {
            loadDataFromApi()
            binding.tvDate.text = SimpleDateFormat("dd:MM:yyyy").format(Date())
        }

    }

    private fun loadDataFromApi() {
        val jsonArraRequest = JsonArrayRequest(
            Request.Method.GET, url,
            null,
            object : Response.Listener<JSONArray> {
                override fun onResponse(response: JSONArray?) {
                    if (response != null) {

                        Log.d(TAG, "onResponse: $response")
                        val type = object : TypeToken<List<Valyuta>>() {}.type
                        val list = Gson().fromJson<List<Valyuta>>(
                            response.toString(),
                            type
                        ) as ArrayList<Valyuta>
                        
                        rvAdapter = RvAdapter(list)
                        binding.rv.adapter = rvAdapter

                        list.forEach {
                            binding.apply {

                                if (it.Ccy == "USD") {
                                    tvUsd.text = "1 USD"
                                    tvNumberUsd.text = it.Rate.toString()
                                    tvInfoNumberUsd.text = "Farq : ${it.Diff}"
                                } else if (it.Ccy == "RUB") {
                                    tvRub.text = "1 RUB"
                                    tvNumberRub.text = it.Rate.toString()
                                    tvInfoNumberRub.text = "Farq : ${it.Diff}"
                                } else if (it.Ccy == "EUR") {
                                    tvEur.text = "1 EUR"
                                    tvNumberEur.text = it.Rate.toString()
                                    tvInfoNumberEur.text = "Farq : ${it.Diff}"
                                }

                            }
                        }
                    }
                }
            },
            object : Response.ErrorListener {
                override fun onErrorResponse(error: VolleyError?) {

                }
            }
        )
        requestQueue.add(jsonArraRequest)
    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork)
        return networkCapabilities != null && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}