package com.vickbt.shared.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vickbt.shared.utils.capitalizeEachWord

@Composable
fun ActionButton(
    modifier: Modifier,
    buttonText: String,
    buttonIcon: ImageVector,
    contentColor: Color = MaterialTheme.colorScheme.primary,
    buttonColor: Color = MaterialTheme.colorScheme.onPrimary,
    onClick: () -> Unit
) {
    FilledTonalButton(
        modifier = modifier,
        onClick = { onClick.invoke() },
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = buttonColor,
            contentColor = contentColor
        ),
        contentPadding = PaddingValues(vertical = 12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = buttonText.capitalizeEachWord(),
                fontSize = 16.sp,
                style = MaterialTheme.typography.labelLarge,
                color = contentColor
            )

            Icon(imageVector = buttonIcon, contentDescription = null, tint = contentColor)
        }
    }
}
