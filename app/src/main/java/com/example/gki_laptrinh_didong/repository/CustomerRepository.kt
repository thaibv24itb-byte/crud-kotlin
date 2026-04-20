package com.example.gki_laptrinh_didong.repository

import com.example.gki_laptrinh_didong.model.Customer
import com.example.gki_laptrinh_didong.network.CustomerApiService
class CustomerRepository(private val apiService: CustomerApiService) {
    suspend fun getCustomers() = apiService.getCustomers()
    suspend fun addCustomer(customer: Customer) = apiService.addCustomer(customer)
    suspend fun updateCustomer(customer: Customer) = apiService.updateCustomer(customer)
    suspend fun deleteCustomer(id: Int) = apiService.deleteCustomer(id)
}