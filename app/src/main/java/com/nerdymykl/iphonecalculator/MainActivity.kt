package com.nerdymykl.iphonecalculator

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nerdymykl.iphonecalculator.model.ButtonItem
import com.nerdymykl.iphonecalculator.model.ButtonItemFactory
import com.nerdymykl.iphonecalculator.model.ButtonType
import com.nerdymykl.iphonecalculator.ui.theme.darkGray
import com.nerdymykl.iphonecalculator.ui.theme.lightGray
import com.nerdymykl.iphonecalculator.ui.theme.orange

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IphoneCalculatorApp(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun IphoneCalculatorApp(modifier: Modifier = Modifier) {

    var displayText by remember { mutableStateOf("") }

    var currentOperator by remember { mutableStateOf("") }

    val setOperator =  { operator: String ->
        currentOperator = operator
    }


    fun editDisplayText(text: String) {
        displayText = "$displayText$text"
    }

    val clear = {
        displayText = ""
    }

    Surface(color = Color.Black, modifier = modifier) {
        Column(Modifier.padding(8.dp)) {
            DisplayPane(modifier = Modifier
                .weight(1f)
                .fillMaxSize(), text = displayText)
            ButtonPane(modifier = Modifier
                .weight(3f)
                .fillMaxSize(),::editDisplayText,clear, currentOperator,setOperator)
        }
    }
}

@Composable
fun DisplayPane(modifier: Modifier = Modifier,text : String) {
    Column(modifier = modifier, verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.End) {
        Text(text = text, color = Color.White, fontSize = 64.sp, modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun ButtonPane(modifier: Modifier = Modifier,onClick: (event: String) -> Unit,clear : () -> Unit,currentOperator : String,setOperator : (String) -> Unit) {
    Box(Modifier.padding(bottom = 16.dp)) {
        LazyVerticalGrid( GridCells.Fixed(4),) {
            items(19,span = {GridItemSpan(if(it == 16) 2 else 1)}) {
                RoundButton(ButtonItemFactory.buttonItems[it],onClick,clear, currentOperator,setOperator)
            }
        }
    }
}



@Composable
fun RoundButton(
    item: ButtonItem,
    onClick: (event : String) -> Unit,
    clear: () -> Unit,
    currentOperator: String,
    setOperator: (String) -> Unit,
) {
    Button(
        onClick = {
            if(item.type == ButtonType.Clear) {
                clear()
            } else if(item.type == ButtonType.Operator) {
                setOperator(item.text)
            }else {
                onClick(item.text)
            }
        },
        shape = CircleShape,
        modifier = Modifier
            .size(96.dp)
            .padding(8.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = if(currentOperator == item.text) Color.White else if (item.type == ButtonType.Digit) darkGray else if (item.type == ButtonType.Operator || item.type == ButtonType.Equals) orange else lightGray, )

    ) {
        Text(
            text = item.text, color = if(currentOperator == item.text) orange else if(item.text == "C" || item.text == "+/-" || item.text == "%") Color.Black else Color.White, fontSize = 32.sp,)
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    IphoneCalculatorApp(modifier = Modifier.fillMaxSize())
}