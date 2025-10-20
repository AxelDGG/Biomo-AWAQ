package com.example.awaq1.data.formularios.remote.dto

import com.example.awaq1.data.formularios.*
import com.example.awaq1.data.formularios.remote.FormBody

// --- helpers ---
private fun mutableBodyOf(vararg pairs: Pair<String, Any?>): MutableMap<String, Any?> =
    mutableMapOf(*pairs)

private fun MutableMap<String, Any?>.putIfNotNull(key: String, value: Any?) {
    if (value != null) this[key] = value
}

// --- Form 1 ---
fun FormularioUnoEntity.toSubmission(userId: Int? = null): FormBody {
    val m = mutableBodyOf(
        "transecto" to transecto,
        "clima" to clima,
        "temporada" to temporada,
        "tipoanimal" to tipoAnimal,
        "nombrecomun" to nombreComun,
        "nombrecientifico" to nombreCientifico,
        "numeroindividuos" to numeroIndividuos,
        "tipoobservacion" to tipoObservacion,
        "observaciones" to observaciones,
        "latitude" to latitude,
        "longitude" to longitude,
        "fecha" to fecha,
        "editado" to editado
    )
    m.putIfNotNull("id_usuario", userId)
    return m
}

// --- Form 2 ---
fun FormularioDosEntity.toSubmission(userId: Int? = null): FormBody {
    val m = mutableBodyOf(
        "zona" to zona,
        "clima" to clima,
        "temporada" to temporada,
        "tipoanimal" to tipoAnimal,
        "nombrecomun" to nombreComun,
        "nombrecientifico" to nombreCientifico,
        "numeroindividuos" to numeroIndividuos,
        "tipoobservacion" to tipoObservacion,
        "alturaobservacion" to alturaObservacion,
        "observaciones" to observaciones,
        "latitude" to latitude,
        "longitude" to longitude,
        "fecha" to fecha,
        "editado" to editado
    )
    m.putIfNotNull("id_usuario", userId)
    return m
}

// --- Form 3 ---
fun FormularioTresEntity.toSubmission(userId: Int? = null): FormBody {
    val m = mutableBodyOf(
        "codigo" to codigo,
        "clima" to clima,
        "temporada" to temporada,
        "seguimiento" to seguimiento,
        "cambio" to cambio,
        "cobertura" to cobertura,
        "tipocultivo" to tipoCultivo,
        "disturbio" to disturbio,
        "observaciones" to observaciones,
        "latitude" to latitude,
        "longitude" to longitude,
        "fecha" to fecha,
        "editado" to editado
    )
    m.putIfNotNull("id_usuario", userId)
    return m
}

// --- Form 4 ---
fun FormularioCuatroEntity.toSubmission(userId: Int? = null): FormBody {
    val m = mutableBodyOf(
        "codigo" to codigo,
        "clima" to clima,
        "temporada" to temporada,
        "quad_a" to quad_a,
        "quad_b" to quad_b,
        "sub_quad" to sub_quad,
        "habitodecrecimiento" to habitoDeCrecimiento,
        "nombrecomun" to nombreComun,
        "nombrecientifico" to nombreCientifico,
        "placa" to placa,
        "circunferencia" to circunferencia,
        "distancia" to distancia,
        "estatura" to estatura,
        "altura" to altura,
        "observaciones" to observaciones,
        "latitude" to latitude,
        "longitude" to longitude,
        "fecha" to fecha,
        "editado" to editado
    )
    m.putIfNotNull("id_usuario", userId)
    return m
}

// --- Form 5 ---
fun FormularioCincoEntity.toSubmission(userId: Int? = null): FormBody {
    val m = mutableBodyOf(
        "zona" to zona,
        "clima" to clima,
        "temporada" to temporada,
        "tipoanimal" to tipoAnimal,
        "nombrecomun" to nombreComun,
        "nombrecientifico" to nombreCientifico,
        "numeroindividuos" to numeroIndividuos,
        "tipoobservacion" to tipoObservacion,
        "alturaobservacion" to alturaObservacion,
        "observaciones" to observaciones,
        "latitude" to latitude,
        "longitude" to longitude,
        "fecha" to fecha,
        "editado" to editado
    )
    // Si el backend de F5 no usa usuario, simplemente no lo agregues.
    m.putIfNotNull("id_usuario", userId)
    return m
}

// --- Form 6 ---
fun FormularioSeisEntity.toSubmission(userId: Int? = null): FormBody {
    val m = mutableBodyOf(
        "codigo" to codigo,
        "clima" to clima,
        "temporada" to temporada,
        "zona" to zona,
        "nombrecamara" to nombreCamara,
        "placacamara" to placaCamara,
        "placaguaya" to placaGuaya,
        "anchocamino" to anchoCamino,
        "fechainstalacion" to fechaInstalacion,
        "distanciaobjetivo" to distanciaObjetivo,
        "alturalente" to alturaLente,
        "checklist" to checklist,
        "observaciones" to observaciones,
        "latitude" to latitude,
        "longitude" to longitude,
        "fecha" to fecha,
        "editado" to editado
    )
    m.putIfNotNull("id_usuario", userId)
    return m
}

// --- Form 7 ---
fun FormularioSieteEntity.toSubmission(userId: Int? = null): FormBody {
    val m = mutableBodyOf(
        "clima" to clima,
        "temporada" to temporada,
        "zona" to zona,
        "pluviosidad" to pluviosidad,
        "temperaturamaxima" to temperaturaMaxima,
        "humedadmaxima" to humedadMaxima,
        "temperaturaminima" to temperaturaMinima,
        "nivelquebrada" to nivelQuebrada,
        "latitude" to latitude,
        "longitude" to longitude,
        "fecha" to fecha,
        "editado" to editado
    )
    m.putIfNotNull("id_usuario", userId)
    return m
}
