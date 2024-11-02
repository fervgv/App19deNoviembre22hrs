package com.example.app.presentation.home
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.app.presentation.model.Artist
import com.example.app.ui.theme.Black


@Composable
fun HomeScreen(viewmodel: HomeViewmodel = HomeViewmodel()){
    val artist: State<List<Artist>> = viewmodel.artist.collectAsState()

Column (
    Modifier
        .fillMaxSize()
        .background(Black)

    ){
        Text(
            "popular artist",
        color = Color.White,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        modifier = Modifier.padding(19.dp)
    )

    LazyRow {
        items(artist.value)    {
            ArtistItem(it)
            }
        }


}
}

@Composable
fun ArtistItem(artist: Artist) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        AsyncImage(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape),
            model = artist.image,
            contentDescription = "Artists image",
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = artist.name.orEmpty(), color = Color.White)
    }
}

@Composable
fun ArtistItemPreview() {
    val artist = Artist(
        "Pepe",
        "El mejor",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwyXeKDN29AmZgZPLS7n0Bepe8QmVappBwZCeA3XWEbWNdiDFB",
         //emptyList()
    )
    ArtistItem(artist=artist)
}





