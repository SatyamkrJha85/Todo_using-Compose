package com.jetpackcompose.todo.data.repository

import com.jetpackcompose.todo.data.local.TodoDao
import com.jetpackcompose.todo.domain.model.Todo
import kotlinx.coroutines.flow.Flow

class TodoRepository(
    private val dao:TodoDao
) {
    suspend fun insertTodo(todo: Todo):Unit=dao.insetTodo(todo=todo)

    suspend fun updataTodo(todo: Todo):Unit=dao.updateTodo(todo=todo)

    suspend fun deleteTodo(todo: Todo):Unit=dao.deleteTodo(todo=todo)

    suspend fun getTodoById(id:Int):Todo=dao.getTodoById(id=id)

    fun getAllTodos():Flow<List<Todo>> = dao.getAllTodos()

}