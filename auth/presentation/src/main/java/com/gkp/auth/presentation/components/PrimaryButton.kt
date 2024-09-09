package com.gkp.auth.presentation.components

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gkp.core.designsystem.theme.TaskyTheme


@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    buttonLabel: String,
    onClick: () -> Unit,
    textStyle: TextStyle = TextStyle.Default
) {
    Button(
        modifier = modifier.defaultMinSize(minHeight = 55.dp),
        onClick = onClick,
        shape = RoundedCornerShape(38.dp)
    ) {
        Text(
            text = buttonLabel,
            style = textStyle
        )
    }
}

@Preview
@Composable
private fun PrimaryButtonPreview() {
    TaskyTheme {
        PrimaryButton(
            buttonLabel = "Primary",
            onClick = {}
        )
    }
}