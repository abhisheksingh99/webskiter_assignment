package com.webskitters.assignment.ui.fragments

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.webskitters.assignment.R
import com.webskitters.assignment.data.WSData
import com.webskitters.assignment.ui.fragments.add.AddUserListner

class CommonViewModel(application: Application): AndroidViewModel(application) {


    var IsUpdate: Boolean = false
    var id: Int = 0
    var name: String? = null
    var phNumber: String? = null
    var email: String? = null
    var address: String? = null
    var userImage: ByteArray? = null
    var addUserListner: AddUserListner?=null

    /** ============================= User Fragment ============================= */



    fun verifyDataFromUser(): Boolean {

        if (name!=null && name!!.length>0 && phNumber!=null && phNumber!!.length>0 && email!=null && email!!.length>0
            && address!=null && address!!.length>0 && userImage!=null && userImage!!.size>0)
        {
           return true
        }




        return false
//        return name!=null && name!!.isNotEmpty() || email!=null && email!!.isNotEmpty() || phNumber!=null && phNumber!!.isNotEmpty() || address!=null && address!!.isNotEmpty()

    }


    fun onSubmit(view:View){

        if (verifyDataFromUser())
        {
            addUserListner?.onsucess()
        }
        else{

            addUserListner?.onError()
        }

    }

    fun setData(currentItem: WSData) {

        IsUpdate=true
        name=currentItem.name
        phNumber=currentItem.phone_no
        email=currentItem.email
        address=currentItem.address
        id=currentItem.id
        if (userImage!=null && userImage!!.size>0)
        {
            userImage= currentItem.image!!
        }
    }

}