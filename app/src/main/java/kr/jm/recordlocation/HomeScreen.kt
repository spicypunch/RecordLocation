package kr.jm.recordlocation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(onClicked: (Int) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Record My Location") })
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(1f)
                            .height(100.dp)
                            .clickable { onClicked(1) },
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(text = "View Location on Map", textAlign = TextAlign.Center)
                        }
                    }
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(1f)
                            .height(100.dp)
                            .clickable { onClicked(2) }
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(text = "Record the current location", textAlign = TextAlign.Center)
                        }
                    }
                }
                BannersAds()
            }
        }
    }
}

@Composable
fun BannersAds() {
    AndroidView(
        factory = { context ->
            AdView(context).apply {
                setAdSize(AdSize.BANNER)
                adUnitId = ""
                loadAd(AdRequest.Builder().build())
            }
        },
        modifier = Modifier.fillMaxWidth(),
        update = { adView ->
            adView.loadAd(AdRequest.Builder().build())
        }
    )
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen() {
    }
}
