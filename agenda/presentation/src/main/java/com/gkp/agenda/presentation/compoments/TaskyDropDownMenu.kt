package com.gkp.agenda.presentation.compoments

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gkp.core.designsystem.theme.TaskyTheme

@Composable
fun TaskyDropDownMenu(
    menuItems: List<String>,
    onItemSelected: (String) -> Unit,
    expanded: Boolean,
    onDismissRequest: () -> Unit,
) {

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { onDismissRequest() }
    ) {
        for (item in menuItems) {

            Column {
                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            onItemSelected(item)
                        },
                    fontSize = 16.sp
                )
                HorizontalDivider(
                    thickness = DividerDefaults.Thickness,
                    color = DividerDefaults.color
                )
            }
        }
    }

}


@Preview(showBackground = false)
@Composable
private fun TaskyDropDownMenuPreview() {
    TaskyTheme {
        TaskyDropDownMenu(
            menuItems = listOf("Item 1", "Item 2", "Item 3"),
            onItemSelected = {},
            expanded = true,
            onDismissRequest = {}
        )

    }
}