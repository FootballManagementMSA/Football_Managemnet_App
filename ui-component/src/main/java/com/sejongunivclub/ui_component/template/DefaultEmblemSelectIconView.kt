package com.sejongunivclub.ui_component.template

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sejongunivclub.ui_component.R

@Composable
fun DefaultEmblemSelectIconView(
    modifier: Modifier = Modifier,
    defaultIcon: Int = R.drawable.camera_profile,
    state: State<Uri?>,
    onSelect: (Uri?) -> Unit
) {
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { onSelect(it) }
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .size(100.dp)
                .clickable {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(
                            ActivityResultContracts.PickVisualMedia.ImageOnly
                        )
                    )
                }
        )
        {
            if (state.value == null) {
                Image(
                    painter = painterResource(id = defaultIcon),
                    contentDescription = "CheckIcon",
                    modifier = Modifier.size(100.dp)
                )
            } else {
                AsyncImage(
                    model = state.value,
                    contentDescription = null,
                    placeholder = painterResource(R.drawable.back_icon),
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }

}

@Preview
@Composable
fun DefaultEmblemSelectIconViewPreview() {
    val state = remember { mutableStateOf<Uri?>(null) }
    DefaultEmblemSelectIconView(modifier = Modifier.background(Color.White), state = state) {
    }
}