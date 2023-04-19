package com.nerdymykl.iphonecalculator.model

class ButtonItem (val text: String, val type: ButtonType) {
}

enum class ButtonType {
        Digit, Operator, Clear, Equals,PlusMinus, Percent,
}

object ButtonItemFactory {
    val buttonItems = listOf(
            ButtonItem("C", ButtonType.Clear),
            ButtonItem("+/-", ButtonType.PlusMinus),
            ButtonItem("%", ButtonType.Percent),
            ButtonItem("÷", ButtonType.Operator),
            ButtonItem("7", ButtonType.Digit),
            ButtonItem("8", ButtonType.Digit),
            ButtonItem("9", ButtonType.Digit),
            ButtonItem("x", ButtonType.Operator),
            ButtonItem("4", ButtonType.Digit),
            ButtonItem("5", ButtonType.Digit),
            ButtonItem("6", ButtonType.Digit),
            ButtonItem("-", ButtonType.Operator),
            ButtonItem("1", ButtonType.Digit),
            ButtonItem("2", ButtonType.Digit),
            ButtonItem("3", ButtonType.Digit),
            ButtonItem("+", ButtonType.Operator),
            ButtonItem("0", ButtonType.Digit),
            ButtonItem(".", ButtonType.Digit),
            ButtonItem("=", ButtonType.Equals)
        )
}