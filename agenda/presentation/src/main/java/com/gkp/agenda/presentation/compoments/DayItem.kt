package com.gkp.agenda.presentation.compoments

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gkp.core.designsystem.theme.TaskyOrange
import com.gkp.core.designsystem.theme.TaskyTextFieldColor
import com.gkp.core.designsystem.theme.TaskyTextHintColor
import com.gkp.core.designsystem.theme.TaskyTheme

@Composable
fun DayItem(
    modifier: Modifier = Modifier,
    day: TaskyCalendarDay,
    isDateSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .height(61.dp)
            .width(40.dp)
            .applyIf(isDateSelected) {
                drawBehind {
                    drawOval(
                        color = TaskyOrange,
                    )
                }
            }
            .clickable {
                onClick()
            },
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 10.dp),
            text = day.weekDay,
            style = MaterialTheme.typography.bodySmall.copy(
                color = TaskyTextHintColor,
                fontSize = 11.sp
            )
        )
        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp),
            text = day.monthDay,
            style = MaterialTheme.typography.bodySmall.copy(
                color = TaskyTextFieldColor,
                fontSize = 17.sp
            )
        )

    }

}

@Preview(showBackground = true)
@Composable
private fun DayItemPreview() {
    TaskyTheme {
        DayItem(
            day = TaskyCalendarDay(
                weekDay = "M",
                monthDay = "1"
            ),
            isDateSelected = true
        )
    }
}

data class TaskyCalendarDay(
    val weekDay: String,
    val monthDay: String,
)

fun Modifier.applyIf(
    condition: Boolean, block:
    Modifier.() -> Modifier
): Modifier {
    return if (condition) {
        block()
    } else {
        this
    }
}