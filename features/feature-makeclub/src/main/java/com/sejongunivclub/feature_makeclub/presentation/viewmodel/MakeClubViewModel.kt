package com.sejongunivclub.feature_makeclub.presentation.viewmodel

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.provider.MediaStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sejongunivclub.core.ResultState.MakeClubResult
import com.sejongunivclub.core.model.MakeClubModel
import com.sejongunivclub.feature_makeclub.domain.SendClubInfoDataUseCase
import com.sejongunivclub.ui_component.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class MakeClubViewModel @Inject constructor(
    private val contentResolver: ContentResolver,
    private val sendClubInfoDataUseCase: SendClubInfoDataUseCase
) : ViewModel() {
    private val _clubName = MutableStateFlow("")
    val clubName: StateFlow<String> = _clubName

    private val _clubDetails = MutableStateFlow("")
    val clubDetails: StateFlow<String> = _clubDetails

    private val _selectedImageUri = MutableStateFlow<Uri?>(null)
    val selectedImageUri: StateFlow<Uri?> = _selectedImageUri

    private val _uiState = MutableStateFlow<MakeClubResult>(MakeClubResult.Initial)
    val uiState get() = _uiState

    fun updateClubName(clubName: String) {
        viewModelScope.launch {
            _clubName.emit(clubName)
        }
    }

    fun updateClubDetails(clubDetails: String) {
        viewModelScope.launch {
            _clubDetails.emit(clubDetails)
        }
    }

    fun updateSelectedImageUri(uri: Uri?) {
        _selectedImageUri.value = uri
    }

    @SuppressLint("Recycle", "Range")
    fun sendClubInfoData(context: Context) {
        viewModelScope.launch {
            _uiState.emit(MakeClubResult.Loading)
            val file: File = if (_selectedImageUri.value != null) {
                val cursor =
                    contentResolver.query(_selectedImageUri.value!!, null, null, null, null)
                cursor?.moveToNext()
                val path =
                    cursor?.getString(cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA))
                File(path)
            } else {
                val drawableId = R.drawable.sejong_mark
                val tempFileName = "default_mark.png"
                drawableToFile(context, drawableId, tempFileName)
            }
            _uiState.value =
                sendClubInfoDataUseCase(MakeClubModel(_clubName.value, _clubDetails.value, file))
        }
    }

    private fun drawableToFile(context: Context, drawableId: Int, fileName: String): File {
        val drawable = context.resources.getDrawable(drawableId, context.theme)
        val bitmap = (drawable as BitmapDrawable).bitmap
        val file = File(context.cacheDir, fileName)
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }
        return file
    }

    fun handleSuccess() {
        _uiState.value = MakeClubResult.Initial
    }
}