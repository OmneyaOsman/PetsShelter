package com.omni.roominkotlinfirsttry.presentation.feature.addeditpet

import android.widget.EditText


fun EditText.isValidEntry(): Boolean {
    return text.toString().isNotEmpty()
}






//    private fun alertDialog(petEntity: PetEntity) {
//        val builder = AlertDialog.Builder(this)
//        builder.setMessage("Are you sure ?")
//        builder.setPositiveButton("Yes") { _, _ ->
//            //            petsViewModel.delete(petEntity)
//            Toast.makeText(this, "$petEntity.name is deleted", Toast.LENGTH_SHORT).show()
//
//        }
//        builder
//                .setNegativeButton("cancel") { dialog, _ ->
//                    dialog.dismiss()
//                }
//        val dialog: AlertDialog = builder.create()
//        dialog.show()
//
//    }