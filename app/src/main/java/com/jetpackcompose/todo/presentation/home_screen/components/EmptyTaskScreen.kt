package com.jetpackcompose.todo.presentation.home_screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import java.lang.reflect.Modifier



@Composable
fun EmptyTaskScreen(paddingValues: PaddingValues) {
    Box(
        modifier = androidx.compose.ui.Modifier.fillMaxSize()
            .padding(paddingValues)
        , contentAlignment = Alignment.Center

    ){
            Text(text = "No Tasks Available Please Write!",
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
    }
}