package com.mx.ebany.elpadrinoloco.ui.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.mx.ebany.elpadrinoloco.data.local.entities.UsersEntity
import com.mx.ebany.elpadrinoloco.data.models.ComidaCatalogo
import com.mx.ebany.elpadrinoloco.data.models.ComidaUsuario
import com.mx.ebany.elpadrinoloco.data.models.Mesa
import com.mx.ebany.elpadrinoloco.data.models.Sucursal
import com.mx.ebany.elpadrinoloco.data.models.User
import com.mx.ebany.elpadrinoloco.domain.usescase.MainUsesCase
import com.mx.ebany.elpadrinoloco.utils.Constants
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

    private val _sucursal = MutableLiveData<List<Sucursal>>()
    val sucursal: LiveData<List<Sucursal>> get() = _sucursal

    private val _mesa = MutableLiveData<List<Mesa>>()
    val mesa: LiveData<List<Mesa>> get() = _mesa

    private val _comidaUsuario = MutableLiveData<List<ComidaUsuario>>()
    val comidaUsuario: LiveData<List<ComidaUsuario>> get() = _comidaUsuario

    private val _comidaCatalogo= MutableLiveData<List<ComidaCatalogo>>()
    val comidaCatalogo: LiveData<List<ComidaCatalogo>> get() = _comidaCatalogo


    init {
        getUsers()
        getSucursal()
        getMesa()
        getComidaUsuario()
        getComidaCatalogo()
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



    private fun getUsers() {
        Log.e("LABASTIDA", "getUsers()")
        db.collection(Constants.TABLE_USUARIO)
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.e("LABASTIDA2", "Error al escuchar cambios", e)
                    return@addSnapshotListener
                }

                val lista = snapshots?.documents?.mapNotNull { it.toObject(User::class.java) } ?: emptyList()
                Log.e("LABASTIDA", "Datos actualizados en tiempo real: $lista")

                _dataUser.postValue(lista)
            }
    }

    private fun getSucursal() {
        Log.e("LABASTIDA", "getSucursal()")
        db.collection(Constants.TABLE_SUCURSAL)
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.e("LABASTIDA2", "Error al escuchar cambios", e)
                    return@addSnapshotListener
                }

                val lista = snapshots?.documents?.mapNotNull { it.toObject(Sucursal::class.java) } ?: emptyList()
                Log.e("LABASTIDA", "Datos actualizados en tiempo real: $lista")

                _sucursal.postValue(lista)
            }
    }

    private fun getMesa() {
        Log.e("LABASTIDA", "getMesa()")
        db.collection(Constants.TABLE_MESA)
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.e("LABASTIDA2", "Error al escuchar cambios", e)
                    return@addSnapshotListener
                }

                val lista = snapshots?.documents?.mapNotNull { it.toObject(Mesa::class.java) } ?: emptyList()
                Log.e("LABASTIDA", "Datos actualizados en tiempo real: $lista")

                _mesa.postValue(lista)
            }
    }

    private fun getComidaUsuario() {
        Log.e("LABASTIDA", "getComidaUsuario()")
        db.collection(Constants.TABLE_COMIDA_USUSARIO)
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.e("LABASTIDA2", "Error al escuchar cambios", e)
                    return@addSnapshotListener
                }

                val lista = snapshots?.documents?.mapNotNull { it.toObject(ComidaUsuario::class.java) } ?: emptyList()
                Log.e("LABASTIDA", "Datos actualizados en tiempo real: $lista")

                _comidaUsuario.postValue(lista)
            }
    }

    private fun getComidaCatalogo() {
        Log.e("LABASTIDA", "getComidaCatalogo()")
        db.collection(Constants.TABLE_COMIDA_CATALOGO)
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Log.e("LABASTIDA2", "Error al escuchar cambios", e)
                    return@addSnapshotListener
                }

                val lista = snapshots?.documents?.mapNotNull { it.toObject(ComidaCatalogo::class.java) } ?: emptyList()
                Log.e("LABASTIDA", "Datos actualizados en tiempo real: $lista")

                _comidaCatalogo.postValue(lista)
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