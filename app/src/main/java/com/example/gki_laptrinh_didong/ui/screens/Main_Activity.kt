package com.example.gki_laptrinh_didong.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gki_laptrinh_didong.ui.components.CustomerItem
import com.example.gki_laptrinh_didong.viewmodel.CustomerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main_Activity(viewModel: CustomerViewModel, onAddClick: () -> Unit, onEditClick: (com.example.gki_laptrinh_didong.model.Customer) -> Unit) {
    // Lắng nghe danh sách khách hàng từ ViewModel
    val customers by viewModel.customers.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Hiển thị danh sách",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        color = Color.Black
                    )
                },
                windowInsets = WindowInsets(0),
                modifier = Modifier.padding(top = 30.dp),
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        },
        bottomBar = {
            Button(
                onClick = onAddClick,
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
            ) {
                Text("Add")
            }
        }
    ) { padding ->
        // Nếu danh sách trống, hiện thông báo tải dữ liệu
        if (customers.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize().padding(padding), contentAlignment = androidx.compose.ui.Alignment.Center) {
                Text("Đang tải dữ liệu hoặc danh sách trống...")
            }
        } else {
            // Hiện danh sách thật
            LazyColumn(modifier = Modifier.padding(padding)) {
                items(customers) { customer ->
                    CustomerItem(customer = customer, onClick = { onEditClick(customer) })
                }
            }
        }
    }
}