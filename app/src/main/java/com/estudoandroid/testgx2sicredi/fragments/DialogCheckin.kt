package com.estudoandroid.testgx2sicredi.fragments

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.estudoandroid.testgx2sicredi.R
import java.lang.IllegalStateException

class DialogCheckin: DialogFragment(){
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let{
            val alertDialog = AlertDialog.Builder(it)
            alertDialog.setView(requireActivity().layoutInflater.inflate(R.layout.dialog_checkin, null))


            alertDialog.create()
        }?:throw IllegalStateException("Activity is null !!")
    }
}