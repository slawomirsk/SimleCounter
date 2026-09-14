package com.skrzypczak.simlec

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skrzypczak.simlec.ui.theme.SimleCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SimleCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
                    CounterScreen2(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SimleCTheme {
        //Greeting("Android")
        CounterScreen2()
    }
}
@Composable
fun CounterScreen(modifier: Modifier = Modifier) {

    var counter by remember { mutableIntStateOf(0) }
    var total by remember { mutableIntStateOf(0) }
    var text by remember { mutableStateOf("") }

    val m=Modifier.fillMaxWidth()
        .padding(horizontal = 20.dp)
        .height(60.dp)

    fun add(subject: Int) {
        counter += subject
    }
    fun reset(){
        counter=0
    }
    fun updateTotal(){
        total=total+counter*text.toInt()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)

    ) {
        Text(text="Łącznie: " + total.toString()+ "kg." ,modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(20.dp)
            ,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold)



        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Wpisz masę sztangi w kg.") }
        )

        Button(onClick = {updateTotal()},m,shape = RoundedCornerShape(20.dp)){
            Text("Dodaj do łącznej masy.")
        }
        Spacer(modifier = Modifier.height(30.dp))

        Text(text = counter.toString() +" powtórzeń",modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
            .padding(20.dp),
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold)

        Button(onClick = { add(1) },m, shape = RoundedCornerShape(20.dp)) {
            Text("Dodaj 1")
        }

        Button(onClick = { add(2) },m,shape = RoundedCornerShape(20.dp)) {
            Text("Dodaj 2")
        }

        Button(onClick = { add(5) },m,shape = RoundedCornerShape(20.dp)) {
            Text("Dodaj 5")
        }

        Button(onClick = { add(10) },m,shape = RoundedCornerShape(20.dp)) {
            Text("Dodaj 10")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {reset()},m,shape = RoundedCornerShape(20.dp)){
            Text("Reset powtórzeń")
        }
        Spacer(modifier = Modifier.height(10.dp))

    }
}

@Composable
fun CounterScreen2(modifier: Modifier = Modifier) {

    var counter by remember { mutableIntStateOf(0) }
    var total by remember { mutableIntStateOf(0) }
    var text by remember { mutableStateOf("") }

    val m=Modifier.fillMaxWidth()
        .padding(horizontal = 20.dp)
        .height(50.dp)

    fun add(subject: Int) {
        counter += subject
    }
    fun reset(){
        counter=0
    }
    fun updateTotal() {
        total += counter * (text.toIntOrNull() ?: 0)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp)

    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
                    .fillMaxWidth()
            ) {
                Text(
                        text = stringResource(R.string.total) + total.toString() + stringResource(R.string.kg), modifier = Modifier
                        .background(Color.LightGray)
                        .fillMaxWidth()
                        .padding(20.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }


        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text(text=stringResource(R.string.EnterWeight),
                        textAlign = TextAlign.Center) }
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = { updateTotal() }, m, shape = RoundedCornerShape(20.dp)) {
                    Text(stringResource(R.string.AddToTotal))
                }
            }
        }


        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(20.dp).fillMaxWidth()
            ) {
                Text(
                    text = counter.toString() +" "+ stringResource(R.string.repeats), modifier = Modifier
                        .background(Color.LightGray)
                        .fillMaxWidth()
                        .padding(20.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = { add(1) }, m, shape = RoundedCornerShape(20.dp)) {
                    Text(stringResource(R.string.add)+" 1")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = { add(2) }, m, shape = RoundedCornerShape(20.dp)) {
                    Text(stringResource(R.string.add)+" 2")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = { add(5) }, m, shape = RoundedCornerShape(20.dp)) {
                    Text(stringResource(R.string.add)+" 5")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = { add(10) }, m, shape = RoundedCornerShape(20.dp)) {
                    Text(stringResource(R.string.add)+"  10")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = { reset() }, m, shape = RoundedCornerShape(20.dp)) {
                    Text(stringResource((R.string.reset)))
                }
            }
        }

    }
}