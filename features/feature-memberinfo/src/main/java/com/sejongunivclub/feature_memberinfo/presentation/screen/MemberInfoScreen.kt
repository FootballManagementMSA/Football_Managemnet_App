package com.sejongunivclub.feature_memberinfo.presentation.screen



import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.requiredHeightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sejongunivclub.feature_memberinfo.presentation.ui_component.MemberDetailView
import com.sejongunivclub.feature_memberinfo.presentation.ui_component.MemberInfoView
import com.sejongunivclub.feature_memberinfo.presentation.ui_component.StatusView
import com.sejongunivclub.ui_component.values.mainTheme

@Composable
fun MemberinfoScreen(
    navHostController: NavHostController
) {
    val config = LocalConfiguration.current

    val scrollState = rememberScrollState()
    Column(
        if (isFixed(config))
            Modifier.background(mainTheme)
        else
            Modifier
                .background(mainTheme)
                .verticalScroll(scrollState)
    ) {
        Column(
            if (isFixed(config))
                Modifier
                    .background(mainTheme)
            else
                Modifier
                    .requiredHeightIn(650.dp)
                    .background(mainTheme)
        ) {
            ProfileView(
                modifier = Modifier
                    .weight(4f)
            )
            MemberDetailView(Modifier
                .weight(6f))


        }
    }

}

private fun isFixed(config: Configuration) = config.screenHeightDp.dp > 650.dp

@Composable
private fun ProfileView(
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        MemberInfoView(
            Modifier
                .requiredHeightIn(200.dp)
                .weight(5f)
        )
        StatusView(
            Modifier
                .requiredHeightIn(min = 100.dp)
                .weight(2f)
                .heightIn(max = 150.dp)
        )
    }
}

@Composable
@Preview
fun MainHomeScreenPreview() {
    MemberinfoScreen(navHostController = rememberNavController())
}