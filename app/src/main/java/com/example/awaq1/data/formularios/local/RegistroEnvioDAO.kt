package com.example.awaq1.data.formularios.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RegistroEnvioDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: RegistroEnvioEntity)

    @Query("UPDATE RegistroEnvio SET sent = :sent, sentAt = :sentAt WHERE idKey = :idKey")
    suspend fun updateSent(idKey: String, sent: Boolean, sentAt: Long?)

    @Query("SELECT sent FROM RegistroEnvio WHERE idKey = :idKey LIMIT 1")
    fun isSentStream(idKey: String): Flow<Boolean?>

    @Query("SELECT * FROM RegistroEnvio WHERE formType = :type ORDER BY formId ASC")
    fun listByType(type: Int): Flow<List<RegistroEnvioEntity>>
}
