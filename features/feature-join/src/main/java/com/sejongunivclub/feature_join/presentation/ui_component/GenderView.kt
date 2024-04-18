import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GenderView(onGenderSelected: (String) -> Unit) {
    var isMaleButtonClicked by remember { mutableStateOf(false) }
    var isFemaleButtonClicked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(align = Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        GenderButton(
            isSelected = isMaleButtonClicked,
            onClick = {
                if (!isMaleButtonClicked) {
                    isMaleButtonClicked = true
                    isFemaleButtonClicked = false
                    onGenderSelected("male")
                }
            },
            imageResId = com.sejongunivclub.ui_component.R.drawable.only_male_image,
            contentDescription = "join_male_image",
            gender = "남성"
        )
        Spacer(modifier = Modifier.width(20.dp))

        GenderButton(
            isSelected = isFemaleButtonClicked,
            onClick = {
                if (!isFemaleButtonClicked) {
                    isFemaleButtonClicked = true
                    isMaleButtonClicked = false
                    onGenderSelected("female")
                }
            },
            imageResId = com.sejongunivclub.ui_component.R.drawable.only_female_image,
            contentDescription = "join_female_image",
            gender = "여성"
        )
    }
}