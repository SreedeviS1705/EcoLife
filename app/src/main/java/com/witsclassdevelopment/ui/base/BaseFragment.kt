package com.witsclassdevelopment.ui.base

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.annotation.IdRes
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import coil.ImageLoader
import com.witsclassdevelopment.R
import com.witsclassdevelopment.adapter.CountryAdapter
import com.witsclassdevelopment.databinding.LayoutCountryPickerBinding
import com.witsclassdevelopment.service.respose.CountryResponse

abstract class BaseFragment(contentLayoutId: Int) : Fragment(contentLayoutId) {

    fun showAlert(
        title: String? = null,
        message: String? = null,
        positiveButtonText: String = "Ok",
        positiveButtonClick: ((dialog: DialogInterface, which: Int) -> Unit)? = null,
        NegativeButtonText: String = "Cancel",
        NegativeButtonClick: ((dialog: DialogInterface, which: Int) -> Unit)? = null,
        cancellable: Boolean = true
    ) {
        with(AlertDialog.Builder(requireContext()))
        {
            if (title != null) setTitle(title)
            if (message != null) setMessage(message)
            if (positiveButtonClick != null) setPositiveButton(
                positiveButtonText,
                DialogInterface.OnClickListener(function = positiveButtonClick)
            )
            if (NegativeButtonClick != null) setNegativeButton(
                NegativeButtonText,
                DialogInterface.OnClickListener(function = NegativeButtonClick)
            )
            setCancelable(cancellable)

            show()
        }
    }


    protected fun navigate(@IdRes resourceId: Int) {
        findNavController().navigate(resourceId, null, getNavOptions())
    }

    protected fun navigate(@IdRes resourceId: Int, bundle: Bundle) {
        findNavController().navigate(resourceId, bundle, getNavOptions())
    }

    /*protected open fun getNavOptions(): NavOptions? {
        return NavOptions.Builder()
            .setEnterAnim(R.anim.nav_zoom_enter_anim)
            .setExitAnim(R.anim.nav_zoom_exit_anim)
            .setPopEnterAnim(R.anim.nav_zoom_pop_enter_anim)
            .setPopExitAnim(R.anim.nav_zoom_pop_exit_anim)
            .build()
    }*/

    protected open fun getNavOptions(): NavOptions? {
        return NavOptions.Builder()
            .setEnterAnim(R.anim.slide_in_right)
            .setExitAnim(R.anim.slide_out_left)
            .setPopEnterAnim(R.anim.nav_zoom_pop_enter_anim)
            .setPopExitAnim(R.anim.nav_zoom_pop_exit_anim)
            .build()
    }

    fun showCountryPicker(
        imageLoader: ImageLoader,
        data: List<CountryResponse.Data>?,
        onSelect: (CountryResponse.Data) -> Unit
    ) {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        val layoutInflater = LayoutInflater.from(requireContext())
        val layoutBinding = LayoutCountryPickerBinding.inflate(layoutInflater, null, false)
        layoutBinding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val countryAdapter = CountryAdapter(imageLoader).apply {
            onItemClick {
                dialog.dismiss()
                onSelect(it)
            }
        }
        layoutBinding.search.doOnTextChanged { text, start, before, count ->
            countryAdapter.filter.filter(text)
        }
        layoutBinding.recyclerView.adapter = countryAdapter
        countryAdapter.submitList(data?.toMutableList())
        dialog.setContentView(layoutBinding.root)
        dialog.setCancelable(true)
        dialog.show()
    }
}