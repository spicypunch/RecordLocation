package kr.jm.recordlocation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GoogleMapScreen() {
    val context = LocalContext.current
    val latLng = getMyLocation(context)
    val dataStore = DataStoreModule(context)
    var savedLatitude by remember {
        mutableDoubleStateOf(0.0)
    }
    var savedLongitude by remember {
        mutableDoubleStateOf(0.0)
    }
    LaunchedEffect(Unit) {
        dataStore.getLatitude.collect {
            savedLatitude = it
        }
    }

    LaunchedEffect(Unit) {
        dataStore.getLongitude.collect {
            savedLongitude = it
        }
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(latLng, 17f)
    }
    val uiSettings = remember {
        MapUiSettings(myLocationButtonEnabled = true)
    }
    val properties by remember {
        mutableStateOf(MapProperties(isMyLocationEnabled = true))
    }
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = properties,
        uiSettings = uiSettings
    ) {
        if (savedLatitude != 0.0 && savedLongitude != 0.0) {
            Marker(
                state = MarkerState(position = LatLng(savedLatitude, savedLongitude)),
            )
        }
    }
}