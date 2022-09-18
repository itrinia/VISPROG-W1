package com.atz.vpweek1_peternakan

import Datapokoknya.Globalvar
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.atz.vpweek1_peternakan.databinding.ActivityKartucardBinding
import com.google.android.material.snackbar.Snackbar

class kartucard : AppCompatActivity() {
    private lateinit var viewBind: ActivityKartucardBinding
    private var position: Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kartucard)
    }


    private fun duabutton(){
        viewBind.Edit.setOnClickListener {
            val editpokoknya = Intent(this, NewData::class.java).apply {
                putExtra("position", position)
            }
            startActivity(editpokoknya)
        }

        viewBind.Delete.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Delete Your Ternak")
            builder.setMessage("Are you sure you want to delete your lovely ternak?")
            //builder.setPositiveButton("OK", DialogInterface.OnClickListener(function = x))

            builder.setPositiveButton(android.R.string.yes) { function, which ->
                val snackbar = Snackbar.make(viewBind.Delete, "Ternak Deleted", Snackbar.LENGTH_INDEFINITE)
                snackbar.setAction("Dismiss") { snackbar.dismiss() }
                snackbar.setActionTextColor(Color.WHITE)
                snackbar.setBackgroundTint(Color.GRAY)
                snackbar.show()

                //remove
                Globalvar.ternakk.removeAt(position)
                finish()
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->
                Toast.makeText(applicationContext,
                    android.R.string.no, Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }
    }
}