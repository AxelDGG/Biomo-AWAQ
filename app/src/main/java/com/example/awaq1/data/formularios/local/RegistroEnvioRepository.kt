package com.example.awaq1.data.formularios.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RegistroEnvioRepository(
    private val dao: RegistroEnvioDAO
) {
    suspend fun markSent(formType: Int, formId: Long) {
        dao.upsert(RegistroEnvioEntity.sent(formType, formId))
    }

    fun isSent(formType: Int, formId: Long): Flow<Boolean> {
        val key = RegistroEnvioEntity.key(formType, formId)
        return dao.isSentStream(key).map { it == true }
    }
}
