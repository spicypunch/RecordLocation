package kr.jm.recordlocation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kr.jm.recordlocation.ui.theme.RecordLocationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RecordLocationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SelectMode() {
                        if (it) {

                        } else {
                            recordLocation(this)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SelectMode(onClicked: (Boolean) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "마루") })
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                        .height(100.dp)
                        .clickable { onClicked(true) }
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Text(text = "지도로 위치 보기", textAlign = TextAlign.Center)
                    }
                }
                Card(
                    modifier = Modifier
                        .padding(8.dp)
                        .weight(1f)
                        .height(100.dp)
                        .clickable { onClicked(false) }
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Text(text = "현재 주소 찍기", textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}

private fun recordLocation(context: Context) {
    val locationProvider = LocationProvider(context)
    val latitude = locationProvider.getLocationLatitude()
    val longitude = locationProvider.getLocationLongitude()
    Log.e("latitude", latitude.toString())
    Log.e("longitude", longitude.toString())
}