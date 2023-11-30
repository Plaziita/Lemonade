package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }
    var taps by remember { mutableStateOf(0) }
    var requiredTaps by remember { mutableStateOf(0) }

    Column (modifier = Modifier.fillMaxSize()){
        Text(
            text = "Lemonade",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .background(Color.Yellow)
        )
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val imageResource = when (result) {
                1 -> R.drawable.lemon_tree
                2 -> R.drawable.lemon_squeeze
                3 -> R.drawable.lemon_drink
                else -> R.drawable.lemon_restart
            }
            Button(onClick = {
                if (result == 2) {
                    if (taps == 0) {
                        requiredTaps = (1..6).random()
                    }

                    taps++

                    if (taps >= requiredTaps) {
                        taps = 0
                        result++
                    }
                } else if (result < 4) {
                    result++
                } else if (result == 4) {
                    result = 1
                }
            }) {
                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = result.toString()
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            when (result) {
                1 -> Text(stringResource(R.string.Lemon_Tree))
                2 -> Text(stringResource(R.string.Lemon))
                3 -> Text(stringResource(R.string.Glass_of_lemonade))
                else -> Text(stringResource(R.string.Empty_glass))

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        LemonApp(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
        )
    }
}