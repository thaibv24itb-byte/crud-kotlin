package com.example.gki_laptrinh_didong.network

import retrofit2.Response
import retrofit2.http.*
import com.example.gki_laptrinh_didong.model.Customer
interface CustomerApiService {
    @GET("view.php")
    suspend fun getCustomers(): List<Customer>

    @POST("add.php")
    suspend fun addCustomer(@Body customer: Customer): Response<Unit>

    @POST("edit.php")
    suspend fun updateCustomer(@Body customer: Customer): Response<Unit>

    @FormUrlEncoded
    @POST("delete.php")
    suspend fun deleteCustomer(@Field("id") id: Int): Response<Unit>
}