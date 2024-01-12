package kr.jm.recordlocation

import android.content.Context
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStoreModule(private val context: Context) {

    /**
     * DataStore는 무조건 싱글톤으로 관리되어야 한다.
     * 당연한 얘기지만 DataStore 객체가 중복으로 생성됐을 경우 저장한 값의 안정성이 위반된다.
     */
    companion object {
        // DataSotre 생성
        private val Context.dataStore by preferencesDataStore(name = "dataStore")

        // 위도 경도 저장 키값 타입에 맞게 double로 선언하였다.
        private val latitudeKey = doublePreferencesKey("latitude")
        private val longitudeeKey = doublePreferencesKey("longitude")
    }

    /**
     * DataStore에서 값 읽기
     * Flow로 값을 받아온다.
     * 예외가 발생했을 경우를 대비해 catch를 사용하여 예외처리를 한다.
     * map을 이용해서 저장되어있는 값을 키를 통해 키를 이용해 가져온다.
     */
    val getLatitude: Flow<Double> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preference ->
            preference[latitudeKey] ?: 0.0
        }

    val getLongitude: Flow<Double> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preference ->
            preference[longitudeeKey] ?: 0.0
        }

    /**
     * DataStore 값 쓰기
     * 쓰기는 비동기로 처리되기 때문에 suspend를 붙여준다.
     */
    suspend fun saveLatitude(latitude: Double) {
        context.dataStore.edit { preference ->
            preference[latitudeKey] = latitude
        }
    }

    suspend fun saveLongitude(longitude: Double) {
        context.dataStore.edit { preference ->
            preference[longitudeeKey] = longitude
        }
    }
}