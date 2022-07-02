package com.example.compose

import android.content.res.Configuration
import android.os.Bundle
//import android.os.Message
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Conversation(SampleData.conversationSample)
//            MessageCard(Message("123", "jetpack compose"))
//            MessageCard("Mamu")
//            Text("Hello World!")
//            ComposeTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    Greeting("Android")
//                }
//            }
        }
    }
}

//@Composable
//fun MessageCard(name: String) {
//    Text(text="Hello $name!")
//}

//data class Message(
//    val author : String,
//    val body : String
//)

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) {
                message -> MessageCard(message)
        }
    }
}

@Preview
@Composable
fun PreviewConversation() {
    Conversation(SampleData.conversationSample)
//    ComposeTutorialTheme {
//
//    }
}



//data class Mymessage (
//    val author: String,
//    val body: String
//    )

@Composable
fun MessageCard(msg: Message){
    Row(modifier = Modifier.padding(all = 8.dp)) { //add padding around img
        Image(
            painter = painterResource(R.drawable.profile_picture),
            contentDescription = "My picture",
            modifier = Modifier
                .size(40.dp) // image size
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)) // clip image to circle


        Spacer(modifier = Modifier.width(8.dp)) //左右物件

        // We keep track if the message is expanded or not in this
        // variable
        // 您必須時刻關注訊息是否已展開
        var isExpanded by remember { mutableStateOf(false) }

        // We toggle the isExpanded variable when we click on this Column
        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(text = msg.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle1)

            Spacer(modifier = Modifier.height(4.dp)) //行高
            Surface(shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                // surfaceColor color will be changing gradually from primary to surface
                color = if (isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.background,
                // animateContentSize will change the Surface size gradually
                modifier = Modifier.animateContentSize().padding(1.dp)
                ) {
                Text(text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    // If the message is expanded, we display all its content
                    // otherwise we only display the first line
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2)
            }

        }

    }


}

@Preview(name = "Light Mode")
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)

@Preview
@Composable
fun PreviewMessageCard() {
    MessageCard(msg=Message("Mamu", "Jetpackcompose"))
//    MessageCard("Android")
}



//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    ComposeTheme {
//        Greeting("Android")
//    }
//}