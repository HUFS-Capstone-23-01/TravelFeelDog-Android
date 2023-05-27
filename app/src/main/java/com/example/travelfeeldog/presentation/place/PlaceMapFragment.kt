package com.example.travelfeeldog.presentation.place

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import com.example.travelfeeldog.R
import com.example.travelfeeldog.databinding.FragmentPlaceMapBinding
import com.example.travelfeeldog.presentation.common.BaseFragment
import com.example.travelfeeldog.presentation.place.viewmodel.PlaceViewModel
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.*
import com.naver.maps.map.overlay.Marker
import org.jetbrains.annotations.Nullable
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class PlaceMapFragment : BaseFragment<FragmentPlaceMapBinding>(R.layout.fragment_place_map),
    OnMapReadyCallback {

    private lateinit var mapView: MapView
    private val placeViewModel: PlaceViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mapView = view.findViewById(R.id.mv_place)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    override fun onMapReady(naverMap: NaverMap) {
        val marker = Marker()
        val cord = getLatLng()

        marker.position = cord
        marker.map = naverMap
        naverMap.cameraPosition = CameraPosition(cord, 18.0)
    }

    private fun getLatLng(): LatLng {
        return LatLng(
            placeViewModel.placeInfo.value!!.latitude,
            placeViewModel.placeInfo.value!!.longitude
        )
    }

    // Map 생명주기 적용

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

}