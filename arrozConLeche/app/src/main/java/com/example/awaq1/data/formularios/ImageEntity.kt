package com.example.awaq1.data.formularios

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class ImageEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val formularioId: Long, // ID of the form
    val formularioType: String, // Specify the type of form (e.g., "Formulario1", "Formulario2")
    val imageUri: String // Store the URI as a string
)