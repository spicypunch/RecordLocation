package kr.jm.recordlocation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun ShowDialog(onClicked: () -> Unit) {
    Dialog(onDismissRequest = { onClicked() }) {
        Surface(
            shape = RoundedCornerShape(12.dp)
        ) {
            Column {
                Spacer(
                    modifier = Modifier
                        .height(12.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = "Latitude and longitude have been recorded.",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize()
                        .padding(vertical = 8.dp),
                    fontSize = 16.sp,
                    lineHeight = 17.sp
                )
                Spacer(
                    modifier = Modifier
                        .height(12.dp)
                        .fillMaxWidth()
                )
                Button(
                    onClick = { onClicked() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Text(text = "Enter", fontSize = 16.sp)
                }
                Spacer(
                    modifier = Modifier
                        .height(12.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewDialog() {
    ShowDialog() {

    }
}