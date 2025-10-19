package pnm.tigad.createartspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import pnm.tigad.createartspace.ui.theme.CreateArtSpaceTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreateArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

data class Artwork(
    val title: String,
    val artist: String,
    val year: String,
    val imageRes: Int
)

@Composable
fun ArtSpaceApp() {
    var index by remember { mutableStateOf(0) }

    val artworks = listOf(
        Artwork(
            title = "Starry Night",
            artist = "Vincent Van Gogh",
            year = "1997",
            imageRes = R.drawable.art1

        ),
        Artwork(
            title = "Fresh Flower in the Garden",
            artist = "Celine Dion",
            year = "2019",
            imageRes = R.drawable.art2

        ),
        Artwork(
            title = "Snow on the Beach",
            artist = "Taylor Swift",
            year = "2020",
            imageRes = R.drawable.art3

        ),

    )

    val artwork = artworks[index]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = artwork.imageRes),
                contentDescription = artwork.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F1F6)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(

                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = artwork.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "${artwork.artist} (${artwork.year})",
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    if (index > 0) index--
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3E4AFA))
            ) {
                Text("Previous")
            }

            Button(
                onClick = {
                    if (index < artworks.size - 1) index++
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3E4AFA))
            ) {
                Text("Next")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtGalleryPreview() {
    CreateArtSpaceTheme {
        ArtSpaceApp()
    }
}