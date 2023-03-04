package com.example.composebasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composebasics.ui.theme.ComposeBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        UiImage()
                        CBHeader()
                        CBIntro()
                        CBDescp()
                    }
                }
            }
        }
    }
}

@Composable
fun UiImage(){
    val image = painterResource(R.drawable.jetpackct)
    Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier
                .fillMaxWidth()

    )
}

@Composable
fun CBHeader(){
    Box(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "JETPACK COMPOSE TUTORIAL",
            fontSize = 24.sp
        )
    }
}

@Composable
fun CBIntro(){
    Box(modifier = Modifier.padding(16.dp)){
        Text(
            text = "Jetpack Compose is a modern toolkit for building native Android UI. Compose simplifies and accelerates UI development on Android with less code, powerful tools, and intuitive Kotlin APIs.",
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun CBDescp(){
    Box(modifier = Modifier.padding(16.dp)){
        Text(
            text = "In this tutorial, you build a simple UI component with declarative functions. You call Compose functions to say what elements you want and the Compose compiler does the rest. Compose is built around Composable functions. These functions let you define your app\\'s UI programmatically because they let you describe how it should look and provide data dependencies, rather than focus on the process of the UI\\'s construction, such as initializing an element and then attaching it to a parent. To create a Composable function, you add the @Composable annotation to the function name.",
            textAlign = TextAlign.Justify
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeBasicsTheme {
        Column {
            UiImage()
            CBHeader()
            CBIntro()
            CBDescp()
        }


    }
}