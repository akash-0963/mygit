package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        ProfileImage()
                        ProfileName()
                        Box(
                            contentAlignment = Alignment.BottomStart
                        ){
                            Column() {
                                Phonenum()
                                Instaid()
                                EmailInfo()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProfileImage() {
    val image = painterResource(R.drawable.profile_image)
    Image(
        painter = image,
        contentDescription = null,
        modifier = Modifier
            .padding(start = 140.dp, end = 140.dp)
    )
}

@Composable
fun ProfileName(){
    Text(
        text = "Akash Jare",
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
    Text(
        text = "Android Developer",
        fontSize = 30.sp
    )
}

@Composable
fun Profile(){
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        ProfileImage()
        ProfileName()
    }
}

@Composable
fun Phonenum(){
    val call = painterResource(R.drawable.call)
    Row(
        modifier = Modifier
            .padding(top = 300.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Image(painter = call, contentDescription = null)
        // Text(text = "Phone number: ")
        Text(text = "8446270963")
    }
}

@Composable
fun Instaid(){
    val insta = painterResource(R.drawable.share)
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Image(painter = insta, contentDescription = null)
        //Text(text = "Instagram Handle: ")
        Text(text = "_akash_jare_963")
    }
}

@Composable
fun EmailInfo(){
    val emaildp = painterResource(R.drawable.email)
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Image(painter = emaildp, contentDescription = null,
            alignment = Alignment.BottomStart
            )
        //Text(text = "Email Id: ")
        Text(text = "akashjare316@gmail.com",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 2.dp)
        )
    }
}

@Composable
fun ContactInfo(){
    Phonenum()
    Instaid()
    EmailInfo()
}

/*@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BusinessCardTheme {
        Column() {
            Profile()
            Phonenum()
            Instaid()
            EmailInfo()
        }
    }
}*/

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BusinessCardTheme {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ProfileImage()
            ProfileName()
            Box(
                contentAlignment = Alignment.BottomStart
            ){
                Column() {
                    Phonenum()
                    Instaid()
                    EmailInfo()
                }
            }
        }
    }
}