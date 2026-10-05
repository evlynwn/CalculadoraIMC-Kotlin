package com.example.calculadoraimc

import android.R.attr.fontWeight
import android.R.attr.label
import android.R.attr.onClick
import android.R.attr.x
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculadoraimc.ui.theme.CalculadoraIMCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculadoraIMCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    IMCScreen(
                        modifier = Modifier
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun IMCScreen(modifier: Modifier = Modifier) {
    var altura by remember {
        mutableStateOf("")
    }
    var peso by remember {
        mutableStateOf("")
    }
    var resultado by remember {
        mutableStateOf(0.0)
    }
    var classificacao by remember {
        mutableStateOf("Insira o peso")
    }
    fun decisaoIMC(imc: Double): String {
        return when {
            imc < 18.5 -> "Abaixo do peso"
            imc < 25.0 -> "Peso ideal"
            imc < 30.0 -> "Levemente acima do peso"
            imc < 35.0 -> "Obesidade grau 1"
            imc < 40.0 -> "Obesidade grau 2"
            else -> "Obesidade grau 3"
        }
    }
    Column(modifier = modifier
        .fillMaxSize()
        .background(color = Color(0xFFEFEDED))
    ) {
        //  -- header --
        Column(modifier = Modifier.fillMaxWidth()
            .height(160.dp)
            .background(color = colorResource(id = R.color.cor_app)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.bmi),
                contentDescription = "Logo App",
                modifier = Modifier.size(80.dp)
                    .padding(vertical = 16.dp)
            )
            Text(
                text = "Calculadora IMC",
                fontSize = 24.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
        // -- form --
        Column(modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
                .offset(y = (-30).dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFFFFFF)
                ),

            ) {
                Column(modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = "Seus dados",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4CA6D5),
                        modifier = Modifier
                            . fillMaxWidth(),
                        textAlign = TextAlign.Center,
                    )

                    OutlinedTextField(
                        value = altura,
                        onValueChange = { altura = it },
                        singleLine = true,
                        modifier = Modifier.width(300.dp),
                        placeholder = {
                            Text(text = "Altura")
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(
                            15.dp
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color(0xFF4CA6D5)
                        )
                    )
                    OutlinedTextField(
                        value = peso,
                        onValueChange = { peso = it },
                        singleLine = true,
                        modifier = Modifier. width(300.dp),
                        placeholder = {
                            Text(text = "Peso")
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        shape = RoundedCornerShape(
                            15.dp
                        ),
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color(0xFF4CA6D5)
                        )
                    )
                    Button(
                        modifier = Modifier
                            .width(300.dp)
                            .height(48.dp),
                        onClick = {
                            var pesoConvertido = peso.toDouble()
                            val alturaConvertida = altura.toDouble()/ 100

                            resultado = pesoConvertido/ ( alturaConvertida * alturaConvertida)

                            classificacao = decisaoIMC(resultado)

                            Color(0xFF4CA6D5)},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF4CA6D5)
                        ),
                        shape = RoundedCornerShape(
                            15.dp
                        )
                    ) {
                        Text(text = "CALCULAR")
                    }

                }
                }



                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF4CAF50)
                    ),

                    ) {

                    Row(modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically

                    )
                    {
                        Text(
                            text = String.format("%.2f",resultado),
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFFFFFF),
                        )
                        Text(
                            text = classificacao,
                            fontSize = 26.sp,
                            color = Color(0xFFFFFFFF),
                        )
                    }


                }

        }
    }
}
