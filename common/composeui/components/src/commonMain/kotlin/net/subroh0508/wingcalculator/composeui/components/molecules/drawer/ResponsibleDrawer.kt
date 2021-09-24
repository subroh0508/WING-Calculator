@file:Suppress("FunctionName")

package net.subroh0508.wingcalculator.composeui.components.molecules.drawer

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material.DrawerState
import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable

enum class DrawerType { Modal, ShrinkableModal, ShrinkablePersist }

interface DrawerConstraints {
    val drawer: DrawerType
}

class ResponsibleDrawerState private constructor(
    internal val modalDrawerState: DrawerState? = null,
    internal val shrinkableDrawerState: ShrinkableDrawerState? = null,
) {
    constructor(constraints: DrawerConstraints, initValue: DrawerValue) : this(
        if (constraints.drawer == DrawerType.Modal) DrawerState(initValue) else null,
        if (constraints.drawer != DrawerType.Modal) ShrinkableDrawerState(initValue) else null
    )

    suspend fun open() {
        when {
            modalDrawerState != null -> modalDrawerState.open()
            shrinkableDrawerState != null -> shrinkableDrawerState.open()
        }
    }

    suspend fun close() {
        when {
            modalDrawerState != null -> modalDrawerState.close()
            shrinkableDrawerState != null -> shrinkableDrawerState.close()
        }
    }

    val isOpen
        get() = when {
            modalDrawerState != null -> modalDrawerState.isOpen
            shrinkableDrawerState != null -> shrinkableDrawerState.isOpen
            else -> false
        }
    val isClosed
        get() = when {
            modalDrawerState != null -> modalDrawerState.isClosed
            shrinkableDrawerState != null -> shrinkableDrawerState.isClosed
            else -> true
        }
    val currentValue
        get() = when {
            modalDrawerState != null -> modalDrawerState.currentValue
            shrinkableDrawerState != null -> shrinkableDrawerState.currentValue
            else -> DrawerValue.Closed
        }

    companion object {
        fun Saver(constraints: DrawerConstraints) = Saver<ResponsibleDrawerState, DrawerValue>(
            save = { it.currentValue },
            restore = { ResponsibleDrawerState(constraints, it) },
        )
    }
}

@Composable
fun <T: DrawerConstraints> rememberResponsibleDrawerState(
    constraints: T,
    initValue: DrawerValue,
) = rememberSaveable(
    constraints,
    saver = ResponsibleDrawerState.Saver(constraints),
) { ResponsibleDrawerState(constraints, initValue) }

@Composable
fun <T: DrawerConstraints> ResponsibleDrawer(
    constraints: T,
    drawerState: ResponsibleDrawerState,
    drawerContent: @Composable ColumnScope.() -> Unit,
    content: @Composable (T) -> Unit,
) = when (constraints.drawer) {
    DrawerType.Modal -> ModalDrawer(
        drawerContent,
        drawerState = drawerState.modalDrawerState!!,
    ) { content(constraints) }
    DrawerType.ShrinkableModal -> ShrinkableModalDrawer(
        drawerContent,
        drawerState = drawerState.shrinkableDrawerState!!,
    ) { content(constraints) }
    DrawerType.ShrinkablePersist -> ShrinkablePersistDrawer(
        drawerContent,
        drawerState = drawerState.shrinkableDrawerState!!,
    ) { content(constraints) }
}
