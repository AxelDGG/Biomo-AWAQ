package com.example.awaq1.data

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.awaq1.data.formularios.*
import com.example.awaq1.data.usuario.*
import com.example.awaq1.data.formularios.local.RegistroEnvioDAO
import com.example.awaq1.data.formularios.local.RegistroEnvioEntity

@Database(
    entities = [
        FormularioUnoEntity::class, FormularioDosEntity::class, FormularioTresEntity::class,
        FormularioCuatroEntity::class, FormularioCincoEntity::class, FormularioSeisEntity::class,
        FormularioSieteEntity::class, ImageEntity::class, UsuarioEntity::class,
        UsuarioFormulario1Entity::class, UsuarioFormulario2Entity::class, UsuarioFormulario3Entity::class,
        UsuarioFormulario4Entity::class, UsuarioFormulario5Entity::class, UsuarioFormulario6Entity::class,
        UsuarioFormulario7Entity::class,
        // 🔹 NUEVA TABLA
        RegistroEnvioEntity::class
    ],
    version = 7,                       // ⬅️ súbela en +1
    exportSchema = true,
    // Mantén los automigrations que ya tenías (opcional si usas fallback)
    autoMigrations = [
        AutoMigration(from = 1, to = 2),
        AutoMigration(from = 2, to = 3)
    ]
)
abstract class FormulariosDatabase : RoomDatabase() {

    abstract fun formulario1Dao(): FormularioUnoDAO
    abstract fun formulario2Dao(): FormularioDosDAO
    abstract fun formulario3Dao(): FormularioTresDAO
    abstract fun formulario4Dao(): FormularioCuatroDAO
    abstract fun formulario5Dao(): FormularioCincoDAO
    abstract fun formulario6Dao(): FormularioSeisDAO
    abstract fun formulario7Dao(): FormularioSieteDAO
    abstract fun imageDao(): ImageDAO
    abstract fun usuarioDAO(): UsuarioDAO
    abstract fun usuarioFormulario1DAO(): UsuarioFormulario1DAO
    abstract fun usuarioFormulario2DAO(): UsuarioFormulario2DAO
    abstract fun usuarioFormulario3DAO(): UsuarioFormulario3DAO
    abstract fun usuarioFormulario4DAO(): UsuarioFormulario4DAO
    abstract fun usuarioFormulario5DAO(): UsuarioFormulario5DAO
    abstract fun usuarioFormulario6DAO(): UsuarioFormulario6DAO
    abstract fun usuarioFormulario7DAO(): UsuarioFormulario7DAO

    // 🔹 NUEVO DAO
    abstract fun registroEnvioDAO(): RegistroEnvioDAO

    companion object {
        @Volatile private var Instance: FormulariosDatabase? = null

        fun getDatabase(context: Context): FormulariosDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    FormulariosDatabase::class.java,
                    "formularios_database"
                )
                    // En debug puedes dejar fallback; en release idealmente migra.
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
