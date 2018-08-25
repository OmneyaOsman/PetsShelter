package com.omni.roominkotlinfirsttry

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.omni.roominkotlinfirsttry.data.PetEntity
import com.omni.roominkotlinfirsttry.data.PetViewModel
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    private var petsViewModel :PetViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setUpSpinner()

        petsViewModel = ViewModelProviders.of(this).get(PetViewModel::class.java)


        edit_pet_name.setOnKeyListener { _, _, _ ->
            if (isValidEntry(edit_pet_name.text!!)) {
                // Clear the error.
                edit_pet_name.error = null
            }
            false
        }

        edit_pet_breed.setOnKeyListener { _, _, _ ->
            if (isValidEntry(edit_pet_breed.text!!)) {
                // Clear the error.
                edit_pet_breed.error = null
            }
            false
        }



    }

    private fun isValidEntry(text: Editable): Boolean {
        return text.toString().isNotEmpty()
    }


    private fun setUpSpinner(){
        val spinnerAdapter = ArrayAdapter.createFromResource(this  ,R.array.array_gender_options ,
                android.R.layout.simple_spinner_item)

        spinner_gender.adapter = spinnerAdapter
        spinner_gender.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val gender = p0.toString()
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_editor , menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val itemId = item!!.itemId
        when(itemId){
            R.id.action_save -> savePet()
            R.id.action_delete -> deletePet()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deletePet() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun savePet() {
        //todo insert dummy data
//        val pet = PetEntity(name = "dolcy" ,breed ="dog" , weight = 44.0 )
        val name = edit_pet_name.text.toString()
        val breed = edit_pet_breed.text.toString()
        val weight = edit_pet_weight.text.toString()

        if(name.isNotEmpty() && breed.isNotEmpty()) {
            val pet = PetEntity(name = name, breed = breed, weight = weight.toDouble())
            petsViewModel?.insert(pet)
            finish()
        }else{
            if(name.isEmpty())
                edit_pet_name.error ="Required"
            else
                edit_pet_name.error =null

            if(breed.isEmpty())
                edit_pet_breed.error ="Required"
            else
                edit_pet_breed.error =null


        }

    }
    //    private fun alertDialog(petEntity: PetEntity){
//        val builder = AlertDialog.Builder(this)
//        builder.setMessage("Are you sure ?")
//        builder.setPositiveButton("Yes"){ _, _ ->
//            petsViewModel!!.delete(petEntity)
//            Toast.makeText(this , "$petEntity.name is deleted",Toast.LENGTH_SHORT).show()
//
//        }
//        builder
//                .setNegativeButton("cancel"){dialog,_ ->
//                    dialog.dismiss()
//                }
//        val dialog: AlertDialog = builder.create()
//        dialog.show()
//
//    }


}
