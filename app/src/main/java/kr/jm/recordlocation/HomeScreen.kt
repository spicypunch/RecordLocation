package kr.jm.recordlocation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(onClicked: (Int) -> Unit) {
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
                        .combinedClickable(
                            onClick = { onClicked(1) },
                        )
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
                        .clickable { onClicked(2) }
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                        Text(text = "현재 주소\n기록하기", textAlign = TextAlign.Center)
                    }
                }
            }
        }
    }
}