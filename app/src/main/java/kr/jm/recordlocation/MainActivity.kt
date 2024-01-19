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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.normal.TedPermission
import kotlinx.coroutines.launch
import kr.jm.recordlocation.ui.theme.RecordLocationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context: Context = this
        MobileAds.initialize(this)
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
    var openDialog by rememberSaveable {
        mutableStateOf(false)
    }
    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen { result ->
                when (result) {
                    1 -> navController.navigate("map")
                    2 -> {
                        val latLng = getMyLocation(context)
                        scope.launch {
                            dataStore.saveLatitude(latLng.latitude)
                            dataStore.saveLongitude(latLng.longitude)
                            openDialog = true
                        }
                    }
                }
            }
        }
        composable(route = "map") {
            GoogleMapScreen()
        }
    }
    if (openDialog) {
        ShowDialog {
            openDialog = false
        }
    }
}

private fun requestPermission(context: Context) {
    TedPermission.create()
        .setPermissionListener(object : PermissionListener {
            override fun onPermissionGranted() {
            }

            override fun onPermissionDenied(deniedPermissions: List<String>) {
                Toast.makeText(
                    context,
                    "permission request",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        .setDeniedMessage("Please grant permission. [Settings] > [Apps & notifications] > [Advanced] > [App permissions]")
        .setPermissions(
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        )
        .check()
}