package com.gkp.agenda.presentation.edit.navigation

import android.annotation.SuppressLint
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.gkp.agenda.domain.model.AgendaItemType
import com.gkp.agenda.presentation.edit.EditAgendaItemViewModel
import com.gkp.agenda.presentation.edit.util.sharedViewModel
import kotlinx.serialization.Serializable

const val EDIT_TEXT_FIELD_NAME_TITLE = "TITLE"
const val EDIT_TEXT_FIELD_NAME_DESCRIPTION = "DESCRIPTION"


@Serializable
data object EditAgendaItemScreenRoute

@Serializable
data class EditAgendaItemTitleAndDescriptionScreenRoute(val editTextFieldName: String)

@Serializable
data class EditAgendaItemGraph(
    val agendaItemId: String? = null,
    val agendaItemType: AgendaItemType,
)

fun NavController.navigateToEditAgendaItemRoute(navOptions: NavOptions? = null) {
    navigate(EditAgendaItemScreenRoute, navOptions)
}

fun NavController.navigateToEditAgendaItemTitleAndDescriptionScreenRoute(
    editTextFieldName: String,
    navOptions: NavOptions? = null,
) {
    navigate(EditAgendaItemTitleAndDescriptionScreenRoute(editTextFieldName), navOptions)
}

fun NavController.navigateToEditAgendaItemGraph(
    agendaItemId: String? = null,
    agendaItemType: AgendaItemType,
    navOptions: NavOptions? = null,
) {
    navigate(
        EditAgendaItemGraph(
            agendaItemId = agendaItemId,
            agendaItemType = agendaItemType
        ),
        navOptions
    )
}


@SuppressLint("NewApi")
fun NavGraphBuilder.editAgendaItemGraph(
    onEditAgendaItemTitleBackClick: () -> Unit,
    onClickEditCloseButton: () -> Unit,
    navController: NavController,
) {
    navigation<EditAgendaItemGraph>(
        startDestination = EditAgendaItemScreenRoute
    ) {
        composable<EditAgendaItemScreenRoute> {
            val viewModel = it.sharedViewModel<EditAgendaItemViewModel>(navController)
            EditAgendaItemScreen(
                onClickAgendaItemTitle = {
                    navController.navigateToEditAgendaItemTitleAndDescriptionScreenRoute(
                        EDIT_TEXT_FIELD_NAME_TITLE
                    )
                },
                onClickAgendaItemDescription = {
                    navController.navigateToEditAgendaItemTitleAndDescriptionScreenRoute(
                        EDIT_TEXT_FIELD_NAME_DESCRIPTION
                    )
                },
                onClickEditCloseButton = onClickEditCloseButton,
                viewModel = viewModel,
            )
        }
        composable<EditAgendaItemTitleAndDescriptionScreenRoute> {
            val viewModel = it.sharedViewModel<EditAgendaItemViewModel>(navController)
            val textFieldName =
                it.toRoute<EditAgendaItemTitleAndDescriptionScreenRoute>().editTextFieldName
            EditAgendaItemTitleAndDescriptionScreen(
                onClickBackButton = onEditAgendaItemTitleBackClick,
                viewModel = viewModel,
                textFieldName = textFieldName
            )
        }
    }
}