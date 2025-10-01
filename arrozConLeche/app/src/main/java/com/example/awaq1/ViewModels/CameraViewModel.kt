package com.example.awaq1.ViewModels

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.lifecycle.ViewModel
import android.net.Uri
import android.util.Log
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.awaq1.MainActivity

class CameraViewModel : ViewModel() {
    private var imageCapture: ImageCapture? = null
    private val _photoUris = MutableLiveData<MutableList<Uri>>(mutableListOf())
    val photoUris: MutableLiveData<MutableList<Uri>> get() = _photoUris

    fun bindCamera(previewView: PreviewView, activity: MainActivity) {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(activity)

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().also {
                it.setSurfaceProvider(previewView.surfaceProvider)
            }

            imageCapture = ImageCapture.Builder().build()

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    activity,
                    cameraSelector,
                    preview,
                    imageCapture
                )
            } catch (e: Exception) {
                Log.e("CameraViewModel", "Camera binding failed", e)
            }
        }, ContextCompat.getMainExecutor(activity))
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun takePhoto(
        context: Context,
        onImageSaved: (Uri) -> Unit,
        onError: (ImageCaptureException) -> Unit
    ) {
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "IMG_${System.currentTimeMillis()}")
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            }
        }

        val outputOptions = ImageCapture.OutputFileOptions.Builder(
            context.contentResolver,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            contentValues
        ).build()

        imageCapture?.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(context),
            object : ImageCapture.OnImageSavedCallback {
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    outputFileResults.savedUri?.let { uri ->
                        onImageSaved(uri) // Pass the URI to the callback
                    } ?: onError(ImageCaptureException(0, "URI is null", null))
                }

                override fun onError(exception: ImageCaptureException) {
                    onError(exception) // Pass the error to the callback
                }
            }
        )
    }

    fun addPhotoUri(uri: Uri) {
        _photoUris.value?.add(uri)
        _photoUris.postValue(_photoUris.value)
    }

    fun removePhotoUri(uri: Uri) {
        _photoUris.value?.remove(uri)
        _photoUris.postValue(_photoUris.value)
    }
}