package com.jetpackcompose.todo.presentation.common

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import com.jetpackcompose.todo.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun mySnakebar(
    scope:CoroutineScope,
    snakebarHostState: SnackbarHostState,
    msg:String,
    actionLabel:String,
    onAction:()-> Unit
){
    scope.launch {
        snakebarHostState.currentSnackbarData?.dismiss()
        val snakebarResult=snakebarHostState.showSnackbar(
            message = msg,
            actionLabel = actionLabel,
            duration = SnackbarDuration.Short
        )
        when(snakebarResult){
            SnackbarResult.ActionPerformed->{
                onAction()
            }
            SnackbarResult.Dismissed->{

            }
        }
    }

}