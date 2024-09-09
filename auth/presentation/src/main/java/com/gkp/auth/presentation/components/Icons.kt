package com.gkp.auth.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.transition.Visibility
import com.gkp.auth.presentation.R

val VisibilityIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(R.drawable.ic_visibility_24)

val VisibilityOffIcon: ImageVector
    @Composable
    get() = ImageVector.vectorResource(R.drawable.ic_visibility_off_24)