package com.example.app2


import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
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

    var backcolor by remember { mutableStateOf(Color.LightGray) }
    var imgNum by remember { mutableIntStateOf(1) }
    var alpha by remember { mutableFloatStateOf(1.0f) }
    var visible by remember { mutableStateOf(true) }
    var image = when (imgNum) {
        1 -> R.drawable.pumpkin
        2 -> R.drawable.spooky
        else -> R.drawable.pacman
    }

    if (imgNum > 3){
        imgNum = 1
    }

    Surface(
        color = backcolor,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {

            val context = LocalContext.current

            Surface(
                modifier = Modifier
                    .size(300.dp),
                color = backcolor
            ) {
                if (visible) {
                    Image(
                        painter = painterResource(image),
                        contentDescription = "scary",
                        alpha = alpha,
                        modifier = Modifier.fillMaxSize()
                            .clickable(
                                onClick = {
                                    alpha = alpha - .2f
                                }
                            )
                    )
                }
            }

            Button(
                onClick = {
                    imgNum++
                }
            ) {
                Text(
                    text = "Next",
                    fontSize = 24.sp
                )
            }


            Button(
                onClick = {
                    val toast = Toast.makeText(context, "button 2 clicked", Toast.LENGTH_SHORT)
                    toast.show()
                    Log.i("main activity","button 2 clicked")
                    visible = !visible
                }
            ) {
                Row () {
                    Icon(
                        painter = painterResource(R.drawable.baseline_broken_image_24),
                        "image"
                    )
                    Spacer(modifier = Modifier.width(12.dp))


                    Text(
                        text = "Hide/Unhide",
                        fontSize = 24.sp


                    )
                }
            }

            Button(
                onClick = {
                    if(backcolor == Color.LightGray){
                        backcolor = Color.DarkGray
                    }
                    else{
                        backcolor = Color.LightGray
                    }
                }
            ){
                Text(
                    text = "Change color",
                    fontSize = 24.sp
                )
            }

        }
    }




}




@Preview
@Composable
fun MainLayoutPreview() {
    MainLayout()
}
