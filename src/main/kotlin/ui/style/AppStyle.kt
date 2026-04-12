package ui.style

import org.jetbrains.compose.web.css.*

object AppStyle : StyleSheet() {
    val container by style {
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Column)
        alignItems(AlignItems.Center)
        padding(20.px)
    }

    val status by style {
        marginBottom(20.px)
    }

    val board by style {
        display(DisplayStyle.Grid)
        property("grid-template-columns", "repeat(var(--cols), 1fr)")
        gap(8.px)
        backgroundColor(Color("#2196F3"))
        padding(20.px)
        borderRadius(10.px)
        maxWidth(90.vw)
    }

    val column by style {
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.ColumnReverse)
        gap(8.px)
    }

    val cell by style {
        width(60.px)
        height(60.px)
        backgroundColor(Color("white"))
        borderRadius(50.percent)
        display(DisplayStyle.Flex)
        justifyContent(JustifyContent.Center)
        alignItems(AlignItems.Center)
        cursor("pointer")

        hover(self) style {
            backgroundColor(Color("#f0f0f0"))
        }
    }

    val piece by style {
        width(80.percent)
        height(80.percent)
        borderRadius(50.percent)
    }

    val cellRed by style {
        backgroundColor(Color("#FF5252"))
    }

    val cellYellow by style {
        backgroundColor(Color("#FFEB3B"))
    }
}