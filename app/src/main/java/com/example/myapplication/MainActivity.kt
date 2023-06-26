package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MyApplicationTheme {
        // A surface container using the 'background' color from the theme
        Surface(
          modifier = Modifier.fillMaxSize(),
          color = MaterialTheme.colorScheme.background,
        ) {
          val items = remember {
            listOf(
              // Latin alphabet
              "1",
              "2",
              "3",
              ".",
              ",",
              " ",
              "ącik",
              "ﬀasdasd",
              "ffasdasd",
              "ęb",
              "ścisk",
              "żart",
              "łapa",
              "ńandu",
              "źreb",
              "ćma",
              "ósemka",
              "żuk",
              "Alice",
              "Ąlice",
              "Bob",
              "Charlie",
              "Charlie",
              "Charlie",
              "charlie",
              "Charlie",
              "Charlie",
              "Ćharlie",
              "Charlie",
              "David",
              "Emma",
              "Frank",
              "Grace",
              "Henry",
              "Isabella",
              "Jack",
              "Kate",
              "Liam",
              "Mia",
              "Noah",
              "Olivia",
              "Peter",
              "Quinn",
              "Rose",
              "Samuel",
              "Taylor",
              "Uma",
              "Victor",
              "Wendy",
              "Xavier",
              "Yara",
              "Zoe",

              // Cyrillic alphabet
              "Александр",
              "Борис",
              "Валентина",
              "Григорий",
              "Дарья",
              "Евгений",
              "Жанна",
              "Зинаида",
              "Иван",
              "Йосиф",
              "Ксения",
              "Леонид",
              "Мария",
              "Николай",
              "Ольга",
              "Павел",
              "Раиса",
              "Сергей",
              "Татьяна",
              "Ульяна",
              "Федор",
              "Харитон",
              "Цветана",
              "Чеслав",
              "Шарлотта",
              "Щедрик",

              // Greek alphabet
              "Αλέξανδρος",
              "Βασιλική",
              "Γιώργος",
              "Δήμητρα",
              "Ελένη",
              "Φώτης",
              "Γιάννης",
              "Ηλίας",
              "Ιωάννα",
              "Κωνσταντίνος",
              "Λουκάς",
              "Μαρία",
              "Νίκος",
              "Όλγα",
              "Παναγιώτης",
              "Ρένα",
              "Σπύρος",
              "Τίνα",
              "Υπατία",
              "Φίλιππος",
              "Χρήστος",
              "Ψυχή",
              "Ωραία",
              "Ζαχαρίας",
              "Θεόδωρος",

              // Devanagari alphabet
              "अनिता",
              "भारत",
              "चंद्र",
              "दीपक",
              "ईशा",
              "फरहान",
              "गौरी",
              "हरीश",
              "इन्दिरा",
              "जय",
              "कृष्ण",
              "लता",
              "मनीष",
              "नीलम",
              "ओम",
              "पूनम",
              "क्वीन",
              "रवि",
              "संजय",
              "तृषा",
              "उमा",
              "विनय",
              "वाणी",
              "योगेश",
              "ज़ीनत"
            ).shuffled()
          }
          Column {
            FastScrollList(
              modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
              items = items,
              key = { it.firstOrNull()?.takeIf { it.isLetterOrDigit() } ?: '#' },
              charContent = { char, requestFocus ->
                Text(
                  modifier = Modifier.clickable(onClick = requestFocus),
                  text = char.toString(),
                )
              },
            ) { item ->
              Greeting(name = item)
            }
          }
        }
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  MyApplicationTheme {
    Greeting("Android")
  }
}