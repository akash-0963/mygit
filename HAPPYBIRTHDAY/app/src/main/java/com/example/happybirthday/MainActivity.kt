package com.example.happybirthday

import android.os.Bundle
//import android.os.Message
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
//import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.happybirthday.ui.theme.HAPPYBIRTHDAYTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HAPPYBIRTHDAYTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                      BirthdayGreetingWithImage("HAPPY BIRTHDAY TAI","from AJ")
                }
            }
        }
    }
}

@Composable
fun BirthdayGreetingWithText(message: String, from: String){
    Column{
        Text(text = message, fontSize = 36.sp, color = Color.Magenta, modifier = Modifier.padding(start = 15.dp))
        Text(text = from, fontSize = 24.sp, modifier = Modifier.padding(top = 700.dp, start = 280.dp))
    }
}


@Composable
fun BirthdayGreetingWithImage(message: String, from: String){
    val image = painterResource(R.drawable.androidparty)
    Box {
        Image(
            painter = image,
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            contentScale = ContentScale.Crop

        )
    }
        BirthdayGreetingWithText(message = message, from = from)


}


@Preview(showBackground = false)
@Composable
fun BirthdayCardPreview(){
    HAPPYBIRTHDAYTheme{
        BirthdayGreetingWithImage("HAPPY BIRTHDAY", "from Akash")
    }
}
