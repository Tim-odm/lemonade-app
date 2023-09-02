package com.example.lemonade_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
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

    var numberOfTaps by remember {
        mutableStateOf(1)
    }

    var tapsToSqueezeLemon by remember {
        mutableStateOf(1)
    }

    val textResource: String

    val imageResource: Painter

    when (imageResult) {
        1 -> {
            imageResource = painterResource(id = R.drawable.lemon_tree)
            textResource = stringResource(id = R.string.tap_lemon)
        }
        2 -> {
            imageResource = painterResource(id = R.drawable.lemon_squeeze)
            textResource = stringResource(id = R.string.squeeze_lemon)
        }
        3 -> {
            imageResource = painterResource(id = R.drawable.lemon_drink)
            textResource = stringResource(id = R.string.drink)

        }
        else -> {
            imageResource = painterResource(id = R.drawable.lemon_restart)
            textResource = stringResource(id = R.string.start_again)
        }
    }

    if (imageResult == 2) tapsToSqueezeLemon = (2..5).random()

    Text(
        text = stringResource(id = R.string.header),
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .background(Color.Yellow)
            .padding(20.dp),
        textAlign = TextAlign.Center,
        fontSize = 32.sp
    )
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
            Button(
                shape = RoundedCornerShape(36.dp),
                modifier = Modifier.width(210.dp),
                colors = ButtonDefaults.buttonColors(Color(155, 224, 222, 243)),
                onClick = {
                    // First check if image 2 is showing
                    // If it's image 2 increment number of taps
                    if (imageResult == 2) {
                        numberOfTaps++
                        if (numberOfTaps == tapsToSqueezeLemon) {
                            imageResult++
                            numberOfTaps = 0;
                        }
                    } else {
                        // Else just change the image
                        imageResult++
                    }
                    // If image result is greater than four i.e. == 5 then
                    // put image one on display instead.
                    if (imageResult > 4) imageResult = 1
                }
            ) {
                Image(
                    painter = imageResource,
                    contentDescription = stringResource(id = R.string.lemon_tree),
                    modifier = Modifier
                )
            }
        Text(
            text = textResource,
            modifier = Modifier.padding(0.dp, 16.dp),
            fontSize = 18.sp
        )
    }
}