package com.example.awaq1.data.formularios.remote

data class TodoDto(
    val id: Int, // O Int, dependiendo de backend
    val userId: Int,
    val task: String,
    val isCompleted: Boolean,
    val createdAt: String,
    val updatedAt: String
)

data class CreateTodoRequest(
    val task: String
)

data class UpdateTodoRequest(
    val task: String? = null, // Hacerlos nulables si la actualización es parcial
    val is_completed: Boolean? = null
)