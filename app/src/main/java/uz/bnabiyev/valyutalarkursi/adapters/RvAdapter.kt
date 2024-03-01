package uz.bnabiyev.valyutalarkursi.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.bnabiyev.valyutalarkursi.databinding.ItemRvBinding
import uz.bnabiyev.valyutalarkursi.models.Valyuta

class RvAdapter(private val list: ArrayList<Valyuta>) : RecyclerView.Adapter<RvAdapter.Vh>() {

    inner class Vh(private val itemRvBinding: ItemRvBinding) :
        RecyclerView.ViewHolder(itemRvBinding.root) {
        fun onBind(valyuta: Valyuta) {
            itemRvBinding.tvTitle.text = valyuta.CcyNm_UZ
            itemRvBinding.tvPrice.text = "${valyuta.Rate} UZS"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }
}