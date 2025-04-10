package com.mx.ebany.elpadrinoloco.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.mx.ebany.elpadrinoloco.data.local.entities.UsersEntity
import com.mx.ebany.elpadrinoloco.data.models.User
import com.mx.ebany.elpadrinoloco.domain.usescase.MainUsesCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    application: Application,
    private val usesCase: MainUsesCase,
    private val db: FirebaseFirestore

): ViewModel() {

    private val _userData = MutableLiveData<List<UsersEntity>?>()
    val userData: LiveData<List<UsersEntity>?> get() = _userData

    private val _saveStatus = MutableLiveData<Boolean>()
    val saveStatus: LiveData<Boolean> get() = _saveStatus

    private val _dataUser = MutableLiveData<List<User>>()
    val dataUser: LiveData<List<User>> get() = _dataUser

    init {
        getFirestoreData()
    }

    fun loadUsers() {
        viewModelScope.launch {
            usesCase.getUsers().collect { result ->
                if (result.isSuccess) {
                    _userData.value = result.getOrNull()
                } else {
                    result.exceptionOrNull()?.printStackTrace()
                }
            }
        }
    }

    fun saveUser(user: UsersEntity) {
        viewModelScope.launch {
            usesCase.saveUser(user).collect { result ->
                _saveStatus.value = result.isSuccess
            }
        }
    }



    private fun getFirestoreData() {
        Log.e("LABASTIDA", "Iniciando Firestore en tiempo real")

        db.collection("user_table")
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.e("LABASTIDA2", "Error al escuchar cambios", e)
                    return@addSnapshotListener
                }

                val lista = snapshots?.documents?.mapNotNull { it.toObject(User::class.java) } ?: emptyList()
                Log.e("LABASTIDA", "Datos actualizados en tiempo real: $lista")

                _dataUser.postValue(lista) // 🔥 Esto actualiza la UI en tiempo real
            }
    }

    fun addUserFirestore(user: User) {
        db.collection("user_table")
            .add(user)
            .addOnSuccessListener {
                Log.e("LABASTIDA", "Usuario agregado correctamente")
            }
            .addOnFailureListener {
                Log.e("LABASTIDA", "Error al agregar usuario", it)
            }
    }



}