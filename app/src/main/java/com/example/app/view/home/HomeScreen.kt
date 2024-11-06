
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.app.view.ui.theme.Black
import com.example.app.view.ui.theme.Green
import com.example.app.viewmodel.HomeViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), navigateToContact: () -> Unit) {

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Text(
            "Helper",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            modifier = Modifier.padding(16.dp)
        )
        MyGoogleMaps()
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {  },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 32.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Green)
        ) {
            Text(text = "Pedir ayuda", color = Color.Black, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {navigateToContact()},
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .padding(horizontal = 32.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Green)
        ) {
            Text(text = "Añadir contacto de emergencia", color = Color.Black, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun UpdateDialog(onUpdateClick: () -> Unit) {
    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {
        Card(colors = CardDefaults.cardColors(containerColor = Color.White)) {
            Column(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth()
                    .height(300.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "ACTUALIZA",
                    fontSize = 22.sp,
                    color = Black,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(24.dp))
                Text(
                    text = "Para poder seguir utilizando helper  actualice la app",
                    fontSize = 16.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = onUpdateClick) {
                    Text(text = "¡Actualizar!")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}


@Composable
fun MyGoogleMaps() {
    val mapProperties = remember { mutableStateOf(MapProperties(maxZoomPreference = 10f, minZoomPreference = 5f)) }
    val mapUiSettings = remember { mutableStateOf(MapUiSettings(mapToolbarEnabled = false)) }

    val singapore = LatLng(1.35, 103.87)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 11f)
    }
    val circleCenter = remember { mutableStateOf(singapore) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp)
    ) {
        GoogleMap(
            properties = mapProperties.value,
            uiSettings = mapUiSettings.value,
            cameraPositionState = cameraPositionState
        ) {
            Circle(
                center = circleCenter.value,
                fillColor = MaterialTheme.colorScheme.secondary,
                strokeColor = MaterialTheme.colorScheme.secondaryContainer,
                radius = 1000.0,
            )
        }

        Column {
            Button(onClick = {
                mapProperties.value = mapProperties.value.copy(
                    isBuildingEnabled = !mapProperties.value.isBuildingEnabled
                )
            }) {
                Text(text = "Toggle isBuildingEnabled")
            }
            Button(onClick = {
                mapUiSettings.value = mapUiSettings.value.copy(
                    mapToolbarEnabled = !mapUiSettings.value.mapToolbarEnabled
                )
            }) {
                Text(text = "Toggle mapToolbarEnabled")
            }
            Button(onClick = {
                cameraPositionState.move(CameraUpdateFactory.zoomIn())
            }) {
                Text(text = "Zoom In")
            }
            Button(onClick = {
                circleCenter.value = LatLng(1.36, 103.85)
            }) {
                Text(text = "Move Circle Center")
            }
        }
    }
}



