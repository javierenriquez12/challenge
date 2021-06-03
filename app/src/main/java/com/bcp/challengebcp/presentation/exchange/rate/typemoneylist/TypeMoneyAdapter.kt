package com.bcp.challengebcp.presentation.exchange.rate.typemoneylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bcp.challengebcp.databinding.ItemTypeMoneyBinding
import com.bcp.challengebcp.model.TypeMoneyModel
import com.bcp.challengebcp.presentation.exchange.rate.ListenClickItemTypeMoney
import com.bumptech.glide.Glide

class TypeMoneyAdapter(
    private val typeMoneyList: List<TypeMoneyModel>?,
    private val typeMoneyListener: ListenClickItemTypeMoney
) :
    RecyclerView.Adapter<TypeMoneyAdapter.ViewHolder>() {

    class ViewHolder(itemTypeMoneyBinding: ItemTypeMoneyBinding) :
        RecyclerView.ViewHolder(itemTypeMoneyBinding.root) {
        var binding: ItemTypeMoneyBinding = itemTypeMoneyBinding
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeMoneyAdapter.ViewHolder {
        return ViewHolder(
            ItemTypeMoneyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        );
    }

    override fun getItemCount(): Int {
        return typeMoneyList?.size ?: 0
    }

    override fun onBindViewHolder(holder: TypeMoneyAdapter.ViewHolder, position: Int) {
        val typeMoneyModel: TypeMoneyModel? = typeMoneyList?.get(position)
        holder.binding.txtItemTypeMoneyTitle.text = typeMoneyModel?.name
        holder.binding.txtItemTypeMoneySubtitle.text = typeMoneyModel?.value
        Glide.with(holder.itemView).load(typeMoneyModel!!.imageFlag)
            .into(holder.binding.imgTypeMoneyFlag)
        holder.binding.clTypeMoney.setOnClickListener {
            typeMoneyModel?.let {
                typeMoneyListener.clickTypeMoney(it)
            }
        }
    }

}