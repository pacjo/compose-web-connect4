package ui.style

import org.jetbrains.compose.web.css.*

object AppStyle : StyleSheet() {
    val container by style {
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Column)
        alignItems(AlignItems.Center)
        padding(20.px)
    }

    val mainRow by style {
        display(DisplayStyle.Flex)
        gap(30.px)
        alignItems(AlignItems.FlexStart)

        media(mediaMaxWidth(768.px)) {
            self style {
                flexDirection(FlexDirection.Column)
                alignItems(AlignItems.Center)
            }
        }
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

    val controls by style {
        display(DisplayStyle.Flex)
        gap(10.px)
        marginTop(20.px)

        child(self, selector("button")) style {
            padding(10.px, 20.px)
            fontSize(16.px)
            cursor("pointer")
            border(1.px, LineStyle.Solid, Color("#2196F3"))
            backgroundColor(Color("#2196F3"))
            color(Color.white)
            borderRadius(5.px)
            property("transition", "background-color 0.2s")

            hover(self) style {
                backgroundColor(Color("#1976D2"))
            }
        }
    }

    val config by style {
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Column)
        gap(15.px)
        padding(20.px)
        backgroundColor(Color("#f5f5f5"))
        borderRadius(10.px)

        child(self, selector("label")) style {
            display(DisplayStyle.Flex)
            justifyContent(JustifyContent.SpaceBetween)
            gap(10.px)
            fontSize(16.px)
        }

        child(self, selector("input")) style {
            padding(5.px)
            fontSize(16.px)
            width(80.px)
        }
    }
}