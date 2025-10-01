package com.example.awaq1.data

import androidx.room.Database
import android.content.Context
import androidx.room.AutoMigration
import androidx.room.DeleteColumn
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.AutoMigrationSpec
import com.example.awaq1.data.formularios.FormularioCincoDAO
import com.example.awaq1.data.formularios.FormularioCincoEntity
import com.example.awaq1.data.formularios.FormularioCuatroDAO
import com.example.awaq1.data.formularios.FormularioCuatroEntity
import com.example.awaq1.data.formularios.FormularioDosDAO
import com.example.awaq1.data.formularios.FormularioDosEntity
import com.example.awaq1.data.formularios.FormularioSeisDAO
import com.example.awaq1.data.formularios.FormularioSeisEntity
import com.example.awaq1.data.formularios.FormularioSieteDAO
import com.example.awaq1.data.formularios.FormularioSieteEntity
import com.example.awaq1.data.formularios.FormularioTresDAO
import com.example.awaq1.data.formularios.FormularioTresEntity
import com.example.awaq1.data.formularios.FormularioUnoDAO
import com.example.awaq1.data.formularios.FormularioUnoEntity
import com.example.awaq1.data.formularios.ImageDAO
import com.example.awaq1.data.formularios.ImageEntity
import com.example.awaq1.data.usuario.UsuarioDAO
import com.example.awaq1.data.usuario.UsuarioEntity
import com.example.awaq1.data.usuario.UsuarioFormulario1DAO
import com.example.awaq1.data.usuario.UsuarioFormulario1Entity
import com.example.awaq1.data.usuario.UsuarioFormulario2DAO
import com.example.awaq1.data.usuario.UsuarioFormulario2Entity
import com.example.awaq1.data.usuario.UsuarioFormulario3DAO
import com.example.awaq1.data.usuario.UsuarioFormulario3Entity
import com.example.awaq1.data.usuario.UsuarioFormulario4DAO
import com.example.awaq1.data.usuario.UsuarioFormulario4Entity
import com.example.awaq1.data.usuario.UsuarioFormulario5DAO
import com.example.awaq1.data.usuario.UsuarioFormulario5Entity
import com.example.awaq1.data.usuario.UsuarioFormulario6DAO
import com.example.awaq1.data.usuario.UsuarioFormulario6Entity
import com.example.awaq1.data.usuario.UsuarioFormulario7DAO
import com.example.awaq1.data.usuario.UsuarioFormulario7Entity


@Database(
    entities = [FormularioUnoEntity::class, FormularioDosEntity::class, FormularioTresEntity::class, FormularioCuatroEntity::class,
        FormularioCincoEntity::class,FormularioSeisEntity::class,FormularioSieteEntity::class,ImageEntity::class,UsuarioEntity::class, UsuarioFormulario1Entity::class,
        UsuarioFormulario2Entity::class, UsuarioFormulario3Entity::class, UsuarioFormulario4Entity::class,
        UsuarioFormulario5Entity::class, UsuarioFormulario6Entity::class,UsuarioFormulario7Entity::class],
    version = 6,
    exportSchema = true,
    autoMigrations = [AutoMigration(from = 1, to = 2), AutoMigration(from = 2, to = 3)]
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
    companion object {
        @Volatile
        private var Instance: FormulariosDatabase? = null

        fun getDatabase(context: Context): FormulariosDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, FormulariosDatabase::class.java, "formularios_database")
                    .fallbackToDestructiveMigration() // Quien necesita migraciones? Nuke it! Win-Win
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
