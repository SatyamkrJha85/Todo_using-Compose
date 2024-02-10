package com.jetpackcompose.todo.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jetpackcompose.todo.domain.model.Todo
import java.util.concurrent.Flow

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insetTodo(todo: Todo)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateTodo(todo: Todo)

    @Delete
    suspend fun deleteTodo(todo: Todo)


    @Query("SELECT * FROM Todo WHERE id=:id")
    suspend fun getTodoById(id:Int):Todo

    @Query("SELECT * FROM Todo")
    fun getAllTodos():kotlinx.coroutines.flow.Flow<List<Todo>>
}