package ui.style

import org.jetbrains.compose.web.css.*

object AppStyle : StyleSheet() {
    val container by style {
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.Column)
        alignItems(AlignItems.Center)
        padding(20.px)
        property("box-sizing", "border-box")
    }

    val mainRow by style {
        display(DisplayStyle.Flex)
        gap(30.px)
        alignItems(AlignItems.Center)
        maxWidth(100.percent)
        property("box-sizing", "border-box")

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
        property("--board-gap", "clamp(2px, 1vw, 5px)")
        display(DisplayStyle.Grid)
        property("grid-template-columns", "repeat(var(--cols), minmax(0, 60px))")
        justifyContent(JustifyContent.Center)
        property("gap", "var(--board-gap)")
        backgroundColor(Color("#2196F3"))
        property("padding", "calc(3 * var(--board-gap))")
        borderRadius(10.px)
        maxWidth(100.percent)
        property("box-sizing", "border-box")
    }

    val column by style {
        display(DisplayStyle.Flex)
        flexDirection(FlexDirection.ColumnReverse)
        property("gap", "var(--board-gap)")
    }

    val cell by style {
        width(100.percent)
        property("aspect-ratio", "1 / 1")
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

    val dropAnimation by keyframes {
        0.percent { property("transform", "translateY(-600%)") }
        100.percent { property("transform", "translateY(0)") }
    }

    val piece by style {
        width(80.percent)
        height(80.percent)
        borderRadius(50.percent)
        animation(dropAnimation) {
            duration(0.4.s)
            timingFunction(AnimationTimingFunction.EaseOut)
        }
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