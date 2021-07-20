package com.liceadev.spinnerdialcode

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.widget.AppCompatSpinner
import com.google.i18n.phonenumbers.PhoneNumberUtil
import java.util.*
import kotlin.collections.ArrayList

class SpinnerDialCode @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatSpinner(context, attrs) {

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
        adapter = mAdapter
    }

    fun onItemSelected(selected: (country: DialCodeCountry) -> Unit) {
        onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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