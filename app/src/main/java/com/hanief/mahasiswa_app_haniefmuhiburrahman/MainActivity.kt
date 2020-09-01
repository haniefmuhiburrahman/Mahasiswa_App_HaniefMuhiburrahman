package com.hanief.mahasiswa_app_haniefmuhiburrahman

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.hanief.mahasiswa_app_haniefmuhiburrahman.adapter.DataAdapter
import com.hanief.mahasiswa_app_haniefmuhiburrahman.config.NetworkModule
import com.hanief.mahasiswa_app_haniefmuhiburrahman.model.action.ResponseAction
import com.hanief.mahasiswa_app_haniefmuhiburrahman.model.getData.DataItem
import com.hanief.mahasiswa_app_haniefmuhiburrahman.model.getData.ResponseGetData
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        faButton.setOnClickListener {
            val intent = Intent(this, InputActivity::class.java)
            startActivity(intent)
        }

        showData()
    }

    private fun showData() {
        val listAnggota = NetworkModule.service().getData()
        listAnggota.enqueue(object : Callback<ResponseGetData> {

            override fun onResponse(
                call: Call<ResponseGetData>,
                response: Response<ResponseGetData>
            ) {

                if (response.isSuccessful) {
                    val item = response.body()?.data

                    val adapter = DataAdapter(item, object : DataAdapter.onClickListener {
                        override fun detail(item: DataItem?) {

                            val intent = Intent(applicationContext, InputActivity::class.java)
                            intent.putExtra("data", item)
                            startActivity(intent)
                        }

                        override fun delete(item: DataItem?) {

                            AlertDialog.Builder(this@MainActivity).apply {
                                setTitle("Hapus Data")
                                setMessage("Yakin mau hapus data?")
                                setPositiveButton("Hapus") { dialog, which->
                                    hapusData(item?.idMahasiswa)
                                    dialog.dismiss()
                                }
                                setNegativeButton("Batal") { dialog, which ->
                                    dialog.dismiss()
                                }
                            }.show()
                        }
                    })

                    rv_list.adapter = adapter
                }
            }
            override fun onFailure(call: Call<ResponseGetData>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun hapusData(id: String?) {

        val hapus = NetworkModule.service().deleteData(id ?: "")
        hapus.enqueue(object : Callback<ResponseAction> {
            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {
                Toast.makeText(applicationContext, "Data berhasil dihapus", Toast.LENGTH_SHORT).show()
                showData()
            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

        })

    }

    override fun onResume() {
        super.onResume()
        showData()
    }
}