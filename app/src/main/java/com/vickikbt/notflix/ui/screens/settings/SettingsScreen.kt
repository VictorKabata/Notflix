package com.vickikbt.notflix.ui.screens.settings

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vickikbt.domain.utils.Constants
import com.vickikbt.notflix.R
import com.vickikbt.notflix.ui.components.AppBar
import com.vickikbt.notflix.ui.components.preferences.PreferencesGroup
import com.vickikbt.notflix.ui.components.preferences.TextPreference

@Composable
fun SettingsScreen(navController: NavController) {

    val context = LocalContext.current

    Scaffold(topBar = { AppBar(stringResource(id = R.string.title_settings)) }) {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.surface) {
            Column {
                Spacer(modifier = Modifier.height(8.dp))

                PreferencesGroup(title = stringResource(id = R.string.title_personalisation)) {
                    TextPreference(
                        icon = painterResource(id = R.drawable.ic_theme),
                        title = stringResource(id = R.string.change_theme),
                        subTitle = "Dark Theme",
                        onClick = { }
                    )

                    TextPreference(
                        icon = painterResource(id = R.drawable.ic_language),
                        title = stringResource(id = R.string.change_language),
                        subTitle = "English",
                        onClick = { }
                    )

                    TextPreference(
                        icon = painterResource(id = R.drawable.ic_image_quality),
                        title = stringResource(id = R.string.change_image_quality),
                        subTitle = "High Quality",
                        onClick = { }
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                PreferencesGroup(
                    title = stringResource(id = R.string.title_extras),
                    isLast = true
                ) {
                    TextPreference(
                        icon = painterResource(id = R.drawable.ic_report_bug),
                        title = stringResource(id = R.string.report_bug),
                        subTitle = stringResource(id = R.string.report_bug_description),
                        onClick = { reportBug(context) }
                    )

                    TextPreference(
                        icon = painterResource(id = R.drawable.ic_github),
                        title = stringResource(id = R.string.source_code),
                        subTitle = stringResource(id = R.string.source_code_description),
                        onClick = { openSourceCode(context) }
                    )
                }

            }
        }
    }

}

private fun reportBug(context: Context) {
    val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
        type = "text/plain"
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf(Constants.BUG_REPORT_EMAIL))
        putExtra(Intent.EXTRA_SUBJECT, Constants.BUG_REPORT_SUBJECT)
    }

    context.startActivity(emailIntent)
}

private fun openSourceCode(context: Context) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(Constants.SOURCE_CODE_URL)
    context.startActivity(intent)
}
