package com.example.gki_laptrinh_didong.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.example.gki_laptrinh_didong.repository.CustomerRepository
import com.example.gki_laptrinh_didong.network.RetrofitClient
import com.example.gki_laptrinh_didong.model.Customer
class CustomerViewModel : ViewModel() {
    private val repository = CustomerRepository(RetrofitClient.instance)
    val customers = MutableStateFlow<List<Customer>>(emptyList())

    init {
        fetchCustomers()
    }

    fun fetchCustomers() {
        viewModelScope.launch {
            try {
                customers.value = repository.getCustomers()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addCustomer(customer: Customer) {
        viewModelScope.launch {
            repository.addCustomer(customer)
            fetchCustomers()
        }
    }

    fun updateCustomer(customer: Customer) {
        viewModelScope.launch {
            try {
                repository.updateCustomer(customer)
                fetchCustomers()
            } catch (e: Exception) { e.printStackTrace() }
        }
    }

    fun deleteCustomer(id: Int) {
        viewModelScope.launch {
            try {
                repository.deleteCustomer(id)
                fetchCustomers()
            } catch (e: Exception) { e.printStackTrace() }
        }
    }
}