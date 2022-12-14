package com.atz.vpweek1_peternakan

import android.Manifest
import Adapter.Listdata
import Datapokoknya.Globalvar
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.atz.vpweek1_peternakan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
        private lateinit var viewBind: ActivityMainBinding
        private val adapter = Listdata(Globalvar.ternakk)
        private var jmlh: Int = 0

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            viewBind = ActivityMainBinding.inflate(layoutInflater)
            setContentView(viewBind.root)
            supportActionBar?.hide()
            permission()
            setupRecyclerView()
            listener()
        }
        override fun onResume() {
            super.onResume()
            jmlh = Globalvar.ternakk.size
            if(jmlh == 0)
            {
                viewBind.addaternak.alpha = 1f
            }else
            {
                viewBind.addaternak.alpha = 0f
            }
            adapter.notifyDataSetChanged()
        }

        private fun permission(){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                // Requesting the permission
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), Globalvar.STORAGE_PERMISSION_CODE)
            } else {
                Toast.makeText(this, "Storage Permission already granted", Toast.LENGTH_SHORT).show()
            }

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                // Requesting the permission
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), Globalvar.STORAGE_PERMISSION_CODE)
            } else {
                Toast.makeText(this, "Storage Permission already granted", Toast.LENGTH_SHORT).show()
            }
        }
        override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
            if (requestCode == Globalvar.STORAGE_PERMISSION_CODE) {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }

        private fun listener(){
            viewBind.ples.setOnClickListener {
                val myIntent = Intent(this, NewData::class.java)
                startActivity(myIntent)
            }


        }

        private fun setupRecyclerView(){
            val layoutManager = LinearLayoutManager (this)
            viewBind.RecyclerView.layoutManager = layoutManager   // Set layout
            viewBind.RecyclerView.adapter = adapter   // Set adapter
        }



}