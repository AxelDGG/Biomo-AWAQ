package com.example.awaq1.data.formularios

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(image: ImageEntity): Long

    @Query("SELECT * FROM ImageEntity WHERE formularioId = :formularioId AND formularioType = :formularioType")
    fun getImagesByFormulario(formularioId: Long, formularioType: String): Flow<List<ImageEntity>>

    @Delete
    suspend fun delete(image: ImageEntity)

    @Query("DELETE FROM ImageEntity WHERE formularioId = :formularioId AND formularioType = :formularioType")
    suspend fun deleteImagesByFormulario(formularioId: Long, formularioType: String)
}
