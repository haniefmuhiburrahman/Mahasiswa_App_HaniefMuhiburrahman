package com.hanief.mahasiswa_app_haniefmuhiburrahman

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.hanief.mahasiswa_app_haniefmuhiburrahman.config.NetworkModule
import com.hanief.mahasiswa_app_haniefmuhiburrahman.model.action.ResponseAction
import com.hanief.mahasiswa_app_haniefmuhiburrahman.model.getData.DataItem
import kotlinx.android.synthetic.main.activity_input.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InputActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_input)

        val getData = intent.getParcelableExtra<DataItem>("data")

        if (getData != null) {
            et_nama.setText(getData.mahasiswaNama)
            et_nohp.setText(getData.mahasiswaNohp)
            et_alamat.setText(getData.mahasiswaAlamat)

            bt_simpan.text = "Update"
            tv_title.text = "Update Data"
        }

        when(bt_simpan.text) {
            "Update" -> {
                bt_simpan.setOnClickListener {
                    updateData(getData?.idMahasiswa, et_nama.text.toString(), et_nohp.text.toString(), et_alamat.text.toString())
                }

            } else -> {

                tv_title.text = "Insert Data"
                bt_simpan.setOnClickListener {
                    inputData(
                        et_nama.text.toString(),
                        et_nohp.text.toString(),
                        et_alamat.text.toString()
                    )
                }
            }
        }

        bt_batal.setOnClickListener {
            finish()
        }
    }

    private fun inputData(nama: String?, nohp: String?, alamat:String?) {

        val input = NetworkModule.service().insertData(nama ?: "", nohp ?: "", alamat ?: "")
        input.enqueue(object : Callback<ResponseAction> {
            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {

                Toast.makeText(applicationContext, "Data berhasil disimpan", Toast.LENGTH_SHORT).show()
                finish()
            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun updateData(id: String?, nama: String?, nohp: String?, alamat: String?) {

        val input = NetworkModule.service().updateData(id ?: "", nama ?: "", nohp ?: "", alamat ?: "")
        input.enqueue(object : Callback<ResponseAction> {

            override fun onResponse(
                call: Call<ResponseAction>,
                response: Response<ResponseAction>
            ) {

                Toast.makeText(applicationContext, "Data berhasil diupdate", Toast.LENGTH_SHORT).show()
                finish()
            }

            override fun onFailure(call: Call<ResponseAction>, t: Throwable) {

                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

        })

    }
}