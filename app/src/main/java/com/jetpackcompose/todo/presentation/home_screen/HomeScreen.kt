package com.jetpackcompose.todo.presentation.home_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.room.Update
import com.jetpackcompose.todo.domain.model.Todo
import com.jetpackcompose.todo.presentation.MainViewModel
import com.jetpackcompose.todo.presentation.common.mySnakebar
import com.jetpackcompose.todo.presentation.common.topAppBarTextStyle
import com.jetpackcompose.todo.presentation.home_screen.components.AlertDialog_HomeSc
import com.jetpackcompose.todo.presentation.home_screen.components.EmptyTaskScreen
import com.jetpackcompose.todo.presentation.home_screen.components.TodoCard


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    mainViewModel: MainViewModel,
    onUpdate:(id:Int)-> Unit
) {
    val tudos by
            mainViewModel.getAllTodos.collectAsStateWithLifecycle(initialValue = emptyList())

    var openDialog by rememberSaveable {
        mutableStateOf(false)
    }

    val scope= rememberCoroutineScope()
    val  snakebarHostState= remember { SnackbarHostState() }
    
    Scaffold(
        snackbarHost = { SnackbarHost (hostState = snakebarHostState)},
        topBar = {
            TopAppBar(title = { Text(text = "Updated Todos",
                style = topAppBarTextStyle)})
        },
        floatingActionButton = {
            FloatingActionButton(modifier = Modifier.clip(shape = CircleShape),onClick = { openDialog=true }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
            }
        }
    ) {
        paddingValues ->
        AlertDialog_HomeSc(openDialog =openDialog ,
            onClose = { openDialog=false},
            mainViewModel = mainViewModel)

        if(tudos.isEmpty()){
            EmptyTaskScreen(paddingValues = paddingValues)
        }
        else{
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ){
                items(tudos, key = {it.id}){ todo ->
                    TodoCard(
                        todo = todo ,
                        onDone = { mainViewModel.deleteTodo(todo=todo)
                                 mySnakebar(
                                     scope = scope,
                                     snakebarHostState=snakebarHostState,
                                     msg = "DONE! -> \"${todo.task}\"",
                                     actionLabel = "UNDO",
                                     onAction = {mainViewModel.undoDeletedTodo()}
                                 )
                                 },
                        onUpdate = onUpdate )

                }
            }
        }
    }

}