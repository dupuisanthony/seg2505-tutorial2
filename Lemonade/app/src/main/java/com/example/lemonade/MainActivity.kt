package com.example.lemonade

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                lemonadeApp()
            }
        }
    }
}

@Composable
fun lemonadeApp() {

    var currentStep by remember { mutableStateOf(1) }

    var squeezeCount by remember { mutableStateOf(0) }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when(currentStep) {
            1-> {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(R.drawable.lemon_tree),
                            contentDescription = stringResource(R.string.lemon_select),
                            modifier = Modifier
                                .padding(16.dp)
                                .wrapContentSize()
                                .clickable {
                                    currentStep = 2
                                    squeezeCount = (2..4).random()
                                }
                        )
                        Text(text = LocalContext.current.getString(R.string.lemon_select))
                    }
                }
            }
            2->{
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ){
                    Image(
                        painter = painterResource(R.drawable.lemon_squeeze),
                        contentDescription = stringResource(R.string.lemon_description),
                        modifier = Modifier.wrapContentSize()
                            .padding(16.dp)
                            .wrapContentSize()
                            .clickable {
                                squeezeCount--
                                if(squeezeCount ==0){
                                    currentStep = 3
                                }
                            }
                    )
                    Text(text = stringResource(R.string.lemon_squeeze))
                }
            }
            3->{
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ){Image(
                    painter = painterResource(R.drawable.lemon_drink),
                    contentDescription = stringResource(R.string.lemonade_description),
                    modifier = Modifier.wrapContentSize()
                        .padding(16.dp)
                        .wrapContentSize()
                        .clickable { currentStep=4 }
                )
                    Text(text = stringResource(R.string.lemon_drink))
                }
            }
            4->{
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ){Image(
                painter = painterResource(R.drawable.lemon_restart),
                contentDescription = stringResource(R.string.empty_glass_description),
                modifier = Modifier.wrapContentSize()
                    .padding(16.dp)
                    .wrapContentSize()
                    .clickable { currentStep=1 }
            )
                Text(text = stringResource(R.string.lemon_empty_glass))
            }
        }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LemonadeTheme {
        lemonadeApp()
    }
}