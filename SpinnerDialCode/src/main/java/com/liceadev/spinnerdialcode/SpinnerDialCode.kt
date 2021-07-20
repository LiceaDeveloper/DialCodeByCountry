package com.liceadev.spinnerdialcode

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.liceadev.spinnerdialcode.databinding.ViewSpinnerDialCodeBinding
import java.util.*
import kotlin.collections.ArrayList

class SpinnerDialCode @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var binding: ViewSpinnerDialCodeBinding =
        ViewSpinnerDialCodeBinding.inflate(LayoutInflater.from(context), this)

    val countriesList: List<DialCodeCountry>

    init {
        val list: MutableList<DialCodeCountry> = ArrayList()
        for (iso in Locale.getISOCountries()) {
            val locale = Locale("", iso)
            val dialCode = PhoneNumberUtil.getInstance().getCountryCodeForRegion(iso)
            val name = locale.displayCountry
            val country = DialCodeCountry(
                name = name,
                dialCode = "+$dialCode",
                code = iso
            )
            list.add(country)
        }
        countriesList = list.sortedBy { it.name }
        loadSpinner()
    }


    private fun loadSpinner() {
        val mAdapter = SpinnerCountryAdapter(context)
        mAdapter.addAll(countriesList)
        binding.spCountry.adapter = mAdapter
    }


    fun setSelection(index: Int) {
        binding.spCountry.setSelection(index)
    }

    fun onItemSelected(selected: (country: DialCodeCountry) -> Unit) {
        binding.spCountry.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                selected.invoke(countriesList[position])
            }
        }
    }
}