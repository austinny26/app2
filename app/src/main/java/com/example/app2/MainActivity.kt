package com.example.app2


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.app2.ui.theme.App2Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainLayout()
        }
    }
}




@Composable
fun MainLayout() {

    var timesClicked = 0

    Surface(
        color = Color.LightGray,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            var message by remember { mutableStateOf("Button Taps: 0") }
            Log.i("MainActivity", "1. $message")
            Text(
                text = message,
                fontSize = 36.sp
            )
            Button(
                onClick = {
                    timesClicked++
                    message = "Button Taps: $timesClicked"
                    Log.i("main activity", "button Clicked!")
                }
            ) {
                Text(
                    text = "Click here",
                    fontSize = 24.sp
                )
            }
            val context = LocalContext.current
            Button(
                onClick = {
                    val toast = Toast.makeText(context, "button 2 clicked", Toast.LENGTH_SHORT)
                    toast.show()
                    timesClicked++
                    message = "Button Taps: $timesClicked"
                    Log.i("main activity","button 2 clicked")}
            ) {
                Row () {
                    Icon(
                        painter = painterResource(R.drawable.baseline_broken_image_24),
                        "image"
                    )
                    Spacer(modifier = Modifier.width(12.dp))


                    Text(
                        text = "Click for toast",
                        fontSize = 24.sp


                    )
                }
            }


        }
    }




}




@Preview
@Composable
fun MainLayoutPreview() {
    MainLayout()
}
