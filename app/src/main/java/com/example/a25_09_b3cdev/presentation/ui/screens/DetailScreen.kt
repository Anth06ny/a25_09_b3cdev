package com.example.a25_09_b3cdev.presentation.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.example.a25_09_b3cdev.R
import com.example.a25_09_b3cdev.data.remote.DescriptionEntity
import com.example.a25_09_b3cdev.data.remote.TempEntity
import com.example.a25_09_b3cdev.data.remote.WeatherEntity
import com.example.a25_09_b3cdev.data.remote.WindEntity
import com.example.a25_09_b3cdev.presentation.ui.theme.A25_09_b3cdevTheme

@Preview(showBackground = true, showSystemUi = true)
@Preview(
    showBackground = true, showSystemUi = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
            or android.content.res.Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
fun DetailScreenPreview() {
    A25_09_b3cdevTheme {
        DetailScreen(
            //jeu de donnée pour la Preview
            data = WeatherEntity(
                id = 2,
                name = "Toulouse",
                main = TempEntity(temp = 22.3),
                weather = listOf(
                    DescriptionEntity(description = "partiellement nuageux", icon = "https://picsum.photos/201")
                ),
                wind = WindEntity(speed = 3.2)
            ),
            navHostController = null
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable //id du WeatherEntity à afficher
fun DetailScreen(
    modifier: Modifier = Modifier,
    data: WeatherEntity,
    navHostController: NavHostController?
) {

    Scaffold(modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = data.name) },
                navigationIcon = {
                    if (navHostController?.previousBackStackEntry != null) {
                        IconButton(onClick = { navHostController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                },
            )
        }


        ) { innerPadding ->

        Column(modifier = Modifier.padding(innerPadding), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                data.name,
                fontSize = 30.sp,
                color = MaterialTheme.colorScheme.primary
            )

            //Permission Internet nécessaire
            AsyncImage(
                model = data.weather.firstOrNull()?.icon,
                //Pour aller le chercher dans string.xml R de votre package com.nom.projet
                //contentDescription = getString(R.string.picture_of_cat),
                //En dur
                contentDescription = "une photo de chat",
                contentScale = ContentScale.FillWidth,

                //Pour toto.png. Si besoin de choisir l'import pour la classe R, c'est celle de votre package
                //Image d'échec de chargement qui sera utilisé par la preview
                error = painterResource(R.drawable.error),
                //Image d'attente.
                //placeholder = painterResource(R.drawable.toto),

                onError = { println(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
            )

            Text(
                data.getResume(), modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), fontSize = 20.sp

            )

            Button(
                onClick = {
                    navHostController?.popBackStack()
                },
                contentPadding = ButtonDefaults.ButtonWithIconContentPadding
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Localized description",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text("Retour")
            }
        }
    }

}