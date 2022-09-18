package com.atz.vpweek1_peternakan


import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import Datapokoknya.Globalvar
import Model.ternak
import androidx.activity.result.contract.ActivityResultContracts
import com.atz.vpweek1_peternakan.databinding.ActivityNewDataBinding

class NewData : AppCompatActivity() {
    private lateinit var viewBind: ActivityNewDataBinding
    private lateinit var ternak: ternak
    var position = -1
    var image: String = ""

    private val GetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){   // successfully added image
            val uri = it.data?.data
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if(uri != null){
                    baseContext.getContentResolver().takePersistableUriPermission(uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                }}// GET PATH TO IMAGE FROM GALLY
            viewBind.plez.setImageURI(uri)
            image = uri.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityNewDataBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        supportActionBar?.hide()
        getintent()
        listener()
    }
    private fun getintent(){
        position = intent.getIntExtra("position", -1)
        if(position != -1){
            val movie = Globalvar.ternakk[position]
            viewBind.plez.setImageURI(Uri.parse(Globalvar.ternakk[position].imageternak))
            viewBind.AddUsiaHewan.editText?.setText(ternak.usia.toString())
            viewBind.AddJenisHewan.editText?.setText(ternak.jenis)
            viewBind.AddNamaHewan.editText?.setText(ternak.nama)
        }
    }
    private fun listener(){
        viewBind.addimage.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            myIntent.type = "image/*"
            GetResult.launch(myIntent)
        }

        viewBind.Add.setOnClickListener{
            var nama = viewBind.AddNamaHewan.editText?.text.toString().trim()
            var usia = 0
            var jenis = viewBind.AddJenisHewan.editText?.text.toString().trim()

            ternak = ternak(nama, usia, jenis)
            checker()
        }
    }

    private fun checker()
    {
        var isCompleted:Boolean = true

        if(ternak.nama!!.isEmpty()){
            viewBind.AddNamaHewan.error = "Nama Hewan cannot be empty"
            isCompleted = false
        }else{
            viewBind.AddNamaHewan.error = ""
        }

        if(ternak.jenis!!.isEmpty()){
            viewBind.AddJenisHewan.error = "Jenis Hewan cannot be empty"
            isCompleted = false
        }else{
            viewBind.AddJenisHewan.error = ""
        }


        ternak.imageternak = image.toString()
//        if(ternak.imageternak!!.isEmpty()){
//            //toast
//            Toast.makeText(this, "Picture can not be empty", Toast.LENGTH_LONG).show()
//            isCompleted = false
//        }

        if(viewBind.AddUsiaHewan.editText?.text.toString().isEmpty() || viewBind.AddUsiaHewan.editText?.text.toString().toInt() < 0)
        {
            viewBind.AddUsiaHewan.error = "Usia cannot be empty or 0"
            isCompleted = false
        }

        if(isCompleted == true)
        {
            if(position == -1)
            {
                ternak.usia = viewBind.AddUsiaHewan.editText?.text.toString().toInt()
                Globalvar.ternakk.add(ternak)

            }else
            {
                ternak.usia = viewBind.AddUsiaHewan.editText?.text.toString().toInt()
                Globalvar.ternakk[position] = ternak
            }
            finish()
        }
    }
}