package Adapter

import Model.ternak
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atz.vpweek1_peternakan.R
import com.atz.vpweek1_peternakan.databinding.ActivityKartucardBinding


class Listdata(val listdata: ArrayList<ternak>) :
    RecyclerView.Adapter<Listdata.viewHolder>() {


    class viewHolder(val itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val binding = ActivityKartucardBinding.bind(itemView)

        fun setData(data: ternak) {
            binding.NamaHewan.text = data.nama
            binding.JenisHewan.text = data.jenis
            binding.Usia.text = data.usia.toString()
            if (data.imageternak.isNotEmpty()) {
                binding.sepi.setImageURI(Uri.parse(data.imageternak))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.activity_kartucard, parent, false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.setData(listdata[position])
    }

    override fun getItemCount(): Int {
        return listdata.size
    }

}