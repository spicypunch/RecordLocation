package kr.jm.recordlocation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
                    App()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen() { result ->
                if (result) {
                    navController.navigate("map")
                } else {

                }
            }
        }
        composable(route = "map") {
            GoogleMapScreen() {
                navController.popBackStack()
            }
        }
    }
}

@Composable
fun RequestLocationPermissions() {
    val context = LocalContext.current
    val permissionList: Array<String> = arrayOf(
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.ACCESS_COARSE_LOCATION
    )
    var grantList by remember { mutableStateOf(mutableListOf(false)) }
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            grantList = it.values.toMutableList()
        }

    LaunchedEffect(permissionList) {

    }

//    when {
//        // 두 권한 모두 허용됐을 경우
//        permissionStateFineLocation.hasPermission && permissionStateCoarseLocation.hasPermission -> {
//            Text("위치 접근 권한이 승인되었습니다.")
//            // TODO: 위치 정보를 사용하는 로직을 여기에 추가하세요.
//        }
//        // 두 권한 중 하나라도 거부되었을 경우, 재요청 다이얼로그 표시
//        (permissionStateFineLocation.shouldShowRationale ||
//                permissionStateCoarseLocation.shouldShowRationale) -> {
//
//            AlertDialog(
//                onDismissRequest = { /*TODO*/ },
//                title = { Text("권한 필요") },
//                text = { Text("이 앱은 위치 정보에 접근해야 합니다.") },
//                confirmButton = {
//                    Button(onClick = {
//                        // 재요청
//                        LaunchedEffect(permissionStateFineLocation, permissionStateCoarseLocation) {
//                            permissionStateFineLocation.launchPermissionRequest()
//                            permissionStaateCoarseLocaiton.launchPermissionRequest()
//                        }
//                    }) {
//                        Text("확인")
//                    }
//                },
//                dismissButton = {
//                    Button(onClick = { /*TODO*/ }) { Text("취소") }
//                }
//            )
//        }
//
//        else -> {
//            if (!permissionStaateFineLocaiton.permissionRequested || !permissionStaateCoarseLocaiton.permissionRequested) {
//                LaunchedEffect(permissionStaateFineLocaiton, permissionStaateCoarseLocaiton) {
//                    // 처음으로 요청하거나 거부 후 '더 이상 묻지 않음' 선택 시 재요청
//                    permissioanStaateFinelocaiton.launchPermissioanReqeust()
//                    permissioanstaetcoarselocaitn.laucnhpermissiaoreqest()
//                }
//            } else {
//                Toast.makeText(context, "위치관련 굿단이 필요합니다.", Toast.LENGTH_LONG).show()
//            }
//        }
//    }
}

private fun recordLocation(context: Context) {
    val locationProvider = LocationProvider(context)
    val latitude = locationProvider.getLocationLatitude()
    val longitude = locationProvider.getLocationLongitude()
    Log.e("latitude", latitude.toString())
    Log.e("longitude", longitude.toString())
}