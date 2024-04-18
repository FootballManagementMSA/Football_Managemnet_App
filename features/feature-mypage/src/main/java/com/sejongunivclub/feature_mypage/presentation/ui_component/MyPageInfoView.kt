package com.sejongunivclub.feature_mypage.presentation.ui_component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.sejongunivclub.ui_component.values.darkGray

@Composable
fun MyPageInfoView(
    modifier: Modifier = Modifier,
    profileImage: String?,
    name: String,
    studentId: String
) {
    Column(
        modifier = modifier
    ) {
        AsyncImage(
            model = profileImage,
            contentDescription = "profile image",
            error = painterResource(id = R.drawable.default_profile_image),
            placeholder = painterResource(
                id = R.drawable.default_profile_image
            ),
            modifier = Modifier
                .size(120.dp)
                .offset(y = (-30).dp)
                .align(Alignment.CenterHorizontally)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(
            text = name, color = Color.White, modifier = Modifier
                .offset(y = (-10).dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = studentId,
            color = darkGray,
            modifier = Modifier
                .offset(y = (-5).dp)
                .align(Alignment.CenterHorizontally)

        )
    }
}

@Preview
@Composable
fun MyPageInfoViewPreview() {
    MyPageInfoView(profileImage = "test", name = "test", studentId = "test")
}
