package com.example.gki_laptrinh_didong

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gki_laptrinh_didong.ui.screens.*
import com.example.gki_laptrinh_didong.viewmodel.CustomerViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val viewModel: CustomerViewModel = viewModel()

            NavHost(navController = navController, startDestination = "main") {
                // 1. Màn hình Danh sách
                composable("main") {
                    Main_Activity(
                        viewModel = viewModel,
                        onAddClick = { navController.navigate("add") },
                        onEditClick = { customer ->
                            // Truyền dữ liệu khách hàng qua đường dẫn (URL)
                            navController.navigate("edit/${customer.id}/${customer.name}/${customer.email}/${customer.phone_number}")
                        }
                    )
                }

                // 2. Màn hình Thêm mới
                composable("add") {
                    AddNew_Activity(
                        onSave = { name, email, phone ->
                            viewModel.addCustomer(com.example.gki_laptrinh_didong.model.Customer(name = name, email = email, phone_number = phone))
                            navController.popBackStack()
                        },
                        onBack = { navController.popBackStack() }
                    )
                }

                // 3. Màn hình Cập nhật / Xóa (THÊM MỚI Ở ĐÂY)
                composable("edit/{id}/{name}/{email}/{phone}") { backStackEntry ->
                    val id = backStackEntry.arguments?.getString("id")?.toInt() ?: 0
                    val name = backStackEntry.arguments?.getString("name") ?: ""
                    val email = backStackEntry.arguments?.getString("email") ?: ""
                    val phone = backStackEntry.arguments?.getString("phone") ?: ""

                    Update_Activity(
                        id = id,
                        initialName = name,
                        initialEmail = email,
                        initialPhone = phone,
                        onUpdate = { n, e, p ->
                            viewModel.updateCustomer(com.example.gki_laptrinh_didong.model.Customer(id, n, e, p))
                            navController.popBackStack()
                        },
                        onDelete = {
                            viewModel.deleteCustomer(id)
                            navController.popBackStack()
                        },
                        onBack = { navController.popBackStack() }
                    )
                }
            }
        }
    }
}