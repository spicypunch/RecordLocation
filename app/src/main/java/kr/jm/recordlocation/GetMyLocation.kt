package kr.jm.recordlocation

import android.content.Context
import com.google.android.gms.maps.model.LatLng

fun getMyLocation(context: Context): LatLng {
    val locationProvider = LocationProvider(context)
    val latitude = locationProvider.getLocationLatitude()
    val longitude = locationProvider.getLocationLongitude()
    return LatLng(latitude, longitude)
}
