package kr.jm.recordlocation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import kotlinx.coroutines.launch
import kr.jm.recordlocation.ui.theme.RecordLocationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context: Context = this
        requestPermission(context)
        setContent {
            RecordLocationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App(context)
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App(context: Context) {
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val dataStore = DataStoreModule(context)
    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen() { result ->
                when (result) {
                    1 -> navController.navigate("map")
                    2 -> {
                        val latLng = getMyLocation(context)
                        scope.launch {
                            dataStore.saveLatitude(latLng.latitude)
                            dataStore.saveLongitude(latLng.longitude)
                            Toast.makeText(context, "기록 되었습니다.", Toast.LENGTH_SHORT).show()
                        }
                    }
                    3 -> navController.navigate("sua")
                }
            }
        }
        composable(route = "map") {
            GoogleMapScreen()
        }
        composable(route = "sua") {
            SuaScreen()
        }
    }
}

private fun requestPermission(context: Context) {
    TedPermission.create()
        .setPermissionListener(object : PermissionListener {
            override fun onPermissionGranted() {
                // TODO:
            }

            override fun onPermissionDenied(deniedPermissions: List<String>) {
                Toast.makeText(
                    context,
                    "권한을 허가해주세요.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        .setDeniedMessage("권한을 허용해주세요. [설정] > [앱 및 알림] > [고급] > [앱 권한]")
        .setPermissions(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )
        .check()
}