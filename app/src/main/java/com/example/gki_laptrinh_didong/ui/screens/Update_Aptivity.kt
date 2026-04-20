package com.example.gki_laptrinh_didong.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Update_Activity(
    id: Int,
    initialName: String,
    initialEmail: String,
    initialPhone: String,
    onUpdate: (String, String, String) -> Unit,
    onDelete: () -> Unit,
    onBack: () -> Unit
) {
    var name by remember { mutableStateOf(initialName) }
    var email by remember { mutableStateOf(initialEmail) }
    var phone by remember { mutableStateOf(initialPhone) }
    val context = LocalContext.current
    val isEmailError = email.contains(" ")
    val isPhoneError = phone.contains(Regex("[a-zA-Z]"))
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            "CẬP NHẬT / XÓA DỮ LIỆU",
            modifier = Modifier.fillMaxWidth().padding( top = 50.dp, bottom = 20.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Tên khách hàng") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Số điện thoại") }, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(24.dp))

        // Nút Cập nhật - Màu Xanh Dương
        Button(
            onClick = {
                if(name.isBlank() || email.isBlank() || phone.isBlank()) {
                    Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
                } else if (isEmailError) {
                    Toast.makeText(context, "Email không được để trống!", Toast.LENGTH_SHORT).show()
                } else if (isPhoneError){
                    Toast.makeText(context, "SDT phải là số từ 0 -> 9!", Toast.LENGTH_SHORT).show()
                } else {
                    onUpdate(name, email, phone)
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3))
        ) {
            Text("CẬP NHẬT", color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Nút Xóa - Màu Đỏ
        Button(
            onClick = onDelete,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF44336))
        ) {
            Text("XÓA KHÁCH HÀNG", color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Nút Quay lại - Màu Xanh Lá
        Button(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
        ) {
            Text("QUAY LẠI", color = Color.White)
        }
    }
}