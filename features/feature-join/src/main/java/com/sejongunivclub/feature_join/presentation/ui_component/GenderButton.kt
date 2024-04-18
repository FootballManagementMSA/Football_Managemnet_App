
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sejongunivclub.ui_component.values.horizontalGradation
import com.sejongunivclub.ui_component.values.subTheme
import com.sejongunivclub.ui_component.values.transparentBrush

@Composable
fun GenderButton(
    isSelected: Boolean,
    onClick: () -> Unit,
    imageResId: Int,
    contentDescription: String,
    gender: String) {
    val borderColor1=if(isSelected)horizontalGradation else transparentBrush

    Box(
        modifier = Modifier
            .height(80.dp)
            .width(120.dp)
            .background(subTheme, shape= RoundedCornerShape(10.dp))
            .clickable { onClick() }
            .border(
                width = 1.dp,
                brush=borderColor1,
                shape = RoundedCornerShape(10.dp)

            )
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(0.55f).padding(start = 4.dp, end = 8.dp),
                painter = painterResource(id = imageResId),
                contentDescription = contentDescription
            )
            Text(
                text = gender,
                fontSize = 11.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}