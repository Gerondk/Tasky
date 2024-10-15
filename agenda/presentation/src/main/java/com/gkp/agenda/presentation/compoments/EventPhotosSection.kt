package com.gkp.agenda.presentation.compoments

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gkp.agenda.presentation.R
import com.gkp.core.designsystem.theme.TaskyGray
import com.gkp.core.designsystem.theme.TaskyLightGray

@Composable
fun EventPhotosSection(
    modifier: Modifier = Modifier,
    photosUris: List<String>,
    onAddPhotoClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(145.dp)
            .background(color = TaskyLightGray),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(R.string.event_photos),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.W600,
                    fontSize = 20.sp
                )

            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                photosUris.forEach { photoUri ->
                    PhotoBox(modifier = Modifier) {
                        PhotoItem(photoUri = photoUri)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
                PhotoBox(
                    modifier = Modifier.clickable {
                        onAddPhotoClick()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = TaskyGray
                    )
                }
            }
        }
    }
}

@Composable
fun PhotoItem(modifier: Modifier = Modifier, photoUri: String) {
    AsyncImage(
        modifier = modifier.clip(RoundedCornerShape(8.dp)),
        model = photoUri,
        contentDescription = null,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun PhotoBox(
    modifier: Modifier = Modifier,
    item: @Composable () -> Unit,
) {
    Box(
        modifier = modifier
            .size(60.dp)
            .border(
                width = 2.dp,
                color = TaskyGray,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        item()
    }

}