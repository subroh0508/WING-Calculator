package net.subroh0508.wingcalculator.composeui.appframe.constraints

import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.DrawerConstraints
import net.subroh0508.wingcalculator.composeui.components.molecules.drawer.DrawerType

interface LayoutConstraints : DrawerConstraints

object DefaultLayoutConstraints : LayoutConstraints {
    override val drawer = DrawerType.ShrinkableModal
}
