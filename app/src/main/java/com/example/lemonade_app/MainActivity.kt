package com.example.lemonade_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
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
import com.example.lemonade_app.ui.theme.LemonadeappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeappTheme {
                LemonadeApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeApp(modifier: Modifier = Modifier) {
    ImageAndText(
        modifier = Modifier
            .fillMaxSize()
    )
}

@Composable
fun ImageAndText(modifier: Modifier = Modifier) {
    var imageResult by remember {
        mutableStateOf(1)
    }

    val imageResource = when (imageResult) {
        1 -> painterResource(id = R.drawable.lemon_tree)
        2 -> painterResource(id = R.drawable.lemon_squeeze)
        3 -> painterResource(id = R.drawable.lemon_drink)
        else -> painterResource(id = R.drawable.lemon_restart)
    }

    Text (
        text = stringResource(id = R.string.header),
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Color.Yellow),
        textAlign = TextAlign.Center,
        fontSize = 32.sp
    )
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Button (
            onClick = { imageResult++ }
        ) {
            Image(
                painter = imageResource,
                contentDescription = stringResource(id = R.string.lemon_tree)
            )
        }
        Text(
            text = stringResource(R.string.tap_lemon),
            modifier = Modifier.padding(0.dp, 16.dp),
            fontSize = 18.sp
        )
    }
}