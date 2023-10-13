package kr.jm.recordlocation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuaScreen() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "김수아💕") }, modifier = Modifier.padding(start = 8.dp))
        }
    ) {
        Image(
            painter = painterResource(R.drawable.sua),
            contentDescription = "sua",
            modifier = Modifier.fillMaxSize()
        )
    }
}