package com.hanief.mahasiswa_app_haniefmuhiburrahman.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hanief.mahasiswa_app_haniefmuhiburrahman.R
import com.hanief.mahasiswa_app_haniefmuhiburrahman.model.getData.DataItem
import kotlinx.android.synthetic.main.list_item.view.*

class DataAdapter (val data: List<DataItem>?, val itemClick: onClickListener): RecyclerView.Adapter<DataAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data?.get(position)

        holder.nama.text = item?.mahasiswaNama
        holder.nohp.text = item?.mahasiswaNohp
        holder.alamat.text = item?.mahasiswaAlamat

        holder.view.setOnClickListener {
            itemClick.detail(item)
        }

        holder.hapus.setOnClickListener {
            itemClick.delete(item)
        }

    }

    override fun getItemCount(): Int = data?.size ?: 0

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view) {

        val nama = view.tv_name
        val nohp = view.tv_nohp
        val alamat = view.tv_alamat
        val hapus  = view.iv_delete

    }

    interface onClickListener {
        fun detail(item: DataItem?)

        fun delete(item: DataItem?)
    }


}