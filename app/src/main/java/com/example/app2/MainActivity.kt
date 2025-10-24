package com.example.app2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { MainLayout() }
    }
}

@Composable
fun MainLayout() {
    var backColor by remember { mutableStateOf(Color.Cyan) }
    var imgNum by remember { mutableIntStateOf(1) }
    var alpha by remember { mutableFloatStateOf(1.0f) }
    var visible by remember { mutableStateOf(true) }
    var autoAdvanceIsRunning by remember { mutableStateOf(false) }

    if (imgNum > 3) imgNum = 1
    if (imgNum < 1) imgNum = 3

    Surface(
        color = backColor,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

            TimerBox()
            ImageDisplay(
                imageRes = when (imgNum) {
                    1 -> R.drawable.pumpkin
                    2 -> R.drawable.spooky
                    else -> R.drawable.pacman
                },
                visible = visible,
                alpha = alpha,
                onImageTap = {
                    val next = alpha - 0.2f
                    alpha = if (next < 0.2f) 1.0f else next
                },
                background = backColor
            )
            NextButton(autoAdvanceIsRunning, click = {imgNum++})
            VisibilityToggleButton(
                visible = visible,
                onToggle = { visible = !visible }
            )
            ColorToggleButton(
                current = backColor,
                onToggle = {
                    backColor = if (backColor == Color.Cyan) Color.Yellow else Color.Cyan
                }
            )
            AutoAdvanceButton(autoAdvanceIsRunning,
                click = {
                    autoAdvanceIsRunning = !autoAdvanceIsRunning
                }
            ) {
                imgNum++
                if (imgNum == 4) {
                    imgNum = 1
                }
            }


        }
    }
}


@Composable
fun TimerBox(){
    var activatetimer by remember { mutableStateOf(false) }
    var time by remember { mutableLongStateOf(0) }
    LaunchedEffect(key1 = activatetimer) {
        while (activatetimer){
            delay(100L)
            time += 100L
        }
    }

    Column(
        modifier = Modifier.size(125.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Timer: ${time / 1000.0}",
            fontSize = 20.sp
        )
        Button(
            onClick = {
                activatetimer = !activatetimer
            },

        ) {
            Text(
                text = if(activatetimer) "Pause" else "Start",
                fontSize = 20.sp
            )
        }
        Button(
            onClick = {
                time = 0L
            },

            ) {
            Text(
                text = "Reset",
                fontSize = 20.sp
            )
        }
    }
}

@Composable
fun AutoAdvanceButton(autoAdvanceIsRunning: Boolean, click: () -> Unit, advance: () -> Unit) {
    LaunchedEffect(key1 = autoAdvanceIsRunning) {
        while (autoAdvanceIsRunning) {
            delay(2000L)
            advance()  // must add () to invoke lambda
        }
    }
    Button(
        onClick = click
    ) {
        Text(
            text = "Auto Advance",
            fontSize = 24.sp
        )
    }
}

@Composable
fun ImageDisplay(
    imageRes: Int,
    visible: Boolean,
    alpha: Float,
    onImageTap: () -> Unit,
    background: Color
) {
    Surface(
        modifier = Modifier.size(300.dp),
        color = background
    ) {
        if (visible) {
            Image(
                painter = painterResource(imageRes),
                contentDescription = "image",
                alpha = alpha,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onImageTap() }
            )
        }
    }
}

@Composable
fun NextButton(autoAdvance: Boolean, click: () -> Unit) {
    Button(
        onClick = click,
        enabled = !autoAdvance
    ) {
        Text(
            text = "Next",
            fontSize = 24.sp,
        )
    }
}


@Composable
fun VisibilityToggleButton(visible: Boolean, onToggle: () -> Unit) {
    Button(onClick = onToggle) {
        Row {
            Icon(
                painter = painterResource(R.drawable.baseline_broken_image_24),
                contentDescription = "toggle visibility"
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = if (visible) "Hide" else "Unhide",
                fontSize = 24.sp
            )
        }
    }
}

@Composable
fun ColorToggleButton(current: Color, onToggle: () -> Unit) {
    Button(onClick = onToggle) {
        val label = if (current == Color.Cyan) "Change to yellow" else "Change to cyan"
        Text(text = label, fontSize = 24.sp)
    }
}

@Preview
@Composable
fun MainLayoutPreview() {
    MainLayout()
}
