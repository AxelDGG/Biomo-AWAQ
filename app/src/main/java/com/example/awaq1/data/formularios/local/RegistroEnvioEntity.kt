package com.example.awaq1.data.formularios.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Index

/**
 * Tabla independiente para rastrear si un formulario (1..7) ya fue enviado al backend.
 * Usamos clave compuesta con índice único para (formType, formId) mediante una clave sintética idKey.
 * idKey = "$formType-$formId" evita migraciones complejas y nos da upsert sencillo.
 */
@Entity(
    tableName = "RegistroEnvio",
    indices = [Index(value = ["idKey"], unique = true)]
)
data class RegistroEnvioEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L, // solicitado
    val idKey: String,          // "tipo-formId", p.ej. "2-17"
    val formType: Int,          // 1..7
    val formId: Long,           // id local de tu FormularioX
    val sent: Boolean,          // true si ya se envió
    val sentAt: Long? = null    // epoch millis del envío (opcional)
) {
    companion object {
        fun key(type: Int, formId: Long) = "$type-$formId"
        fun sent(type: Int, formId: Long, atMillis: Long = System.currentTimeMillis()) =
            RegistroEnvioEntity(
                id = 0L,
                idKey = key(type, formId),
                formType = type,
                formId = formId,
                sent = true,
                sentAt = atMillis
            )
    }
}
