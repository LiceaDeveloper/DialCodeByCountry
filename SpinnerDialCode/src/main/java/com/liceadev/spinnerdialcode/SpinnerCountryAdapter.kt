package com.liceadev.spinnerdialcode

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.liceadev.spinnerdialcode.databinding.ItemCountryDropBinding


class SpinnerCountryAdapter(context: Context?) : ArrayAdapter<DialCodeCountry?>(context!!, 0) {

    private val mLayoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val country = getItem(position)
        val binding = ItemCountryDropBinding.inflate(mLayoutInflater, parent, false)

        binding.tvCountryName.text = country?.name
        binding.tvDialCode.text = country?.dialCode

        val drawableName = "flag_${country?.code?.toLowerCase()}"
        val drawable = parent.context.drawableFromString(drawableName)
        binding.ivFlag.setImageDrawable(ContextCompat.getDrawable(context, drawable))
        return binding.root
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var newConvertView = convertView
        val country = getItem(position)
        if (newConvertView == null) {
            newConvertView = mLayoutInflater.inflate(R.layout.item_country, null, false)
        }
        val ivFlag = newConvertView!!.findViewById<View>(R.id.ivFlag) as ImageView
        val drawableName = "flag_${country?.code?.toLowerCase()}"
        val drawable = parent.context.drawableFromString(drawableName)
        ivFlag.setImageDrawable(ContextCompat.getDrawable(context, drawable))

        return newConvertView
    }
}