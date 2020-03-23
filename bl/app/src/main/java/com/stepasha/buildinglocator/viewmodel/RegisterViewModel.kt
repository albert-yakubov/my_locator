package com.stepasha.buildinglocator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.stepasha.buildinglocator.model.NewUser
import com.stepasha.buildinglocator.util.RegisterValidation

class RegisterViewModel : ViewModel() {

    val newUser: MutableLiveData<NewUser> by lazy {
        MutableLiveData<NewUser>()

    }


    val registerValidation = RegisterValidation()

    fun checkIfValid(email: String?) {
        registerValidation.validate(email)
    }

    fun checkIfValid(name: String?, minLength: Int, maxLength: Int ) {

        registerValidation.validate(name, minLength, maxLength)
    }

    fun createNewUser() {
        registerValidation.createUserForAPI(
            NewUser(
                newUser.value?.firstName,
                newUser.value?.lastName,
                newUser.value?.email,
                newUser.value?.username,
                newUser.value?.password
            )
        )
    }
}