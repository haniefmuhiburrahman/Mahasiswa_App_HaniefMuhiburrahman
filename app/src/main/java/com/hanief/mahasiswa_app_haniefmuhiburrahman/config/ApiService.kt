package com.hanief.mahasiswa_app_haniefmuhiburrahman.config

import com.hanief.mahasiswa_app_haniefmuhiburrahman.model.action.ResponseAction
import com.hanief.mahasiswa_app_haniefmuhiburrahman.model.getData.ResponseGetData
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    // getData
    @GET("getData.php")
    fun getData(): Call<ResponseGetData>

    // getDataById
    @GET("getData.php")
    fun getDataById(@Query("id_mahasiswa") id: String): Call<ResponseGetData>

    // insert data
    @FormUrlEncoded
    @POST("insert.php")
    fun insertData(@Field("mahasiswa_nama") nama: String,
                   @Field("mahasiswa_nohp") nohp: String,
                   @Field("mahasiswa_alamat") alamat: String): Call<ResponseAction>

    // update data
    @FormUrlEncoded
    @POST("update.php")
    fun updateData(@Field("id_mahasiswa") id: String,
                   @Field("mahasiswa_nama") nama: String,
                   @Field("mahasiswa_nohp") nohp: String,
                   @Field("mahasiswa_alamat") alamat: String): Call<ResponseAction>

    // delete data
    @FormUrlEncoded
    @POST("delete.php")
    fun deleteData(@Field("id_mahasiswa") id: String): Call<ResponseAction>


}