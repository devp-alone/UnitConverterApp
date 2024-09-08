package com.devp.unitconvertor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devp.unitconvertor.ui.theme.UNitCONVErtorTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UNitCONVErtorTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverterUI()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConverterUI() {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Centimeters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.0) }
    val oConversionFactor = remember { mutableStateOf(1.0) }

    fun convertUnits() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value / oConversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }

    // Background gradient colors
    val gradientBackground = Brush.verticalGradient(
        colors = listOf(Color(0xFF74EBD5), Color(0xFFACB6E5))
    )

    // Main column
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBackground)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .shadow(8.dp, RoundedCornerShape(16.dp))
                .background(Color.White, RoundedCornerShape(16.dp))
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                " Unit Converter",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF333333),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Input field
            OutlinedTextField(
                value = inputValue,
                onValueChange = {
                    inputValue = it
                    convertUnits()
                },
                label = { Text("Enter Value :$inputUnit", color = Color.Gray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .shadow(2.dp, RoundedCornerShape(10.dp)),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF3A7BD5),
                    unfocusedBorderColor = Color.Gray
                ),
                shape = RoundedCornerShape(8.dp)
            )

            // Input Unit Dropdown
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box {
                    Button(
                        onClick = { iExpanded = true },
                        modifier = Modifier.shadow(4.dp, RoundedCornerShape(8.dp)),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3A7BD5)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = inputUnit, color = Color.White)
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = "Arrow Down",
                            tint = Color.White
                        )
                    }
                    DropdownMenu(
                        expanded = iExpanded,
                        onDismissRequest = { iExpanded = false },
                    ) {
                        DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                            inputUnit = "Centimeters"
                            iExpanded = false
                            conversionFactor.value = 0.01
                            convertUnits()
                        })
                        DropdownMenuItem(text = { Text("Meters") }, onClick = {
                            inputUnit = "Meters"
                            iExpanded = false
                            conversionFactor.value = 1.0
                            convertUnits()
                        })
                        DropdownMenuItem(text = { Text("Feet") }, onClick = {
                            inputUnit = "Feet"
                            iExpanded = false
                            conversionFactor.value = 0.3048
                            convertUnits()
                        })
                        DropdownMenuItem(text = { Text("Millimeters") }, onClick = {
                            inputUnit = "Millimeters"
                            iExpanded = false
                            conversionFactor.value = 0.001
                            convertUnits()
                        })
                        DropdownMenuItem(text = { Text("Decameters") }, onClick = {
                            inputUnit = "Decameters"
                            iExpanded = false
                            conversionFactor.value = 10.0
                            convertUnits()
                        })
                    }
                }

                // Output Unit Dropdown
                Box {
                    Button(
                        onClick = { oExpanded = true },
                        modifier = Modifier.shadow(4.dp, RoundedCornerShape(8.dp)),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF3A7BD5)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(text = outputUnit, color = Color.White)
                        Icon(
                            Icons.Default.ArrowDropDown,
                            contentDescription = "Arrow Down",
                            tint = Color.White
                        )
                    }
                    DropdownMenu(
                        expanded = oExpanded,
                        onDismissRequest = { oExpanded = false },
                    ) {
                        DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                            outputUnit = "Centimeters"
                            oExpanded = false
                            oConversionFactor.value = 0.01
                            convertUnits()
                        })
                        DropdownMenuItem(text = { Text("Meters") }, onClick = {
                            outputUnit = "Meters"
                            oExpanded = false
                            oConversionFactor.value = 1.0
                            convertUnits()
                        })
                        DropdownMenuItem(text = { Text("Feet") }, onClick = {
                            outputUnit = "Feet"
                            oExpanded = false
                            oConversionFactor.value = 0.3048
                            convertUnits()
                        })
                        DropdownMenuItem(text = { Text("Millimeters") }, onClick = {
                            outputUnit = "Millimeters"
                            oExpanded = false
                            oConversionFactor.value = 0.001
                            convertUnits()
                        })
                        DropdownMenuItem(text = { Text("Decameters") }, onClick = {
                            outputUnit = "Decameters"
                            oExpanded = false
                            oConversionFactor.value = 10.0
                            convertUnits()
                        })
                    }
                }
            }

            // Output result
            Row {
                Text(
                    text = "Result: $outputValue  ",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0xFF333333),
                    modifier = Modifier.padding(top = 16.dp)
                )
                Text(
                    text = " $outputUnit",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF333333),
                    modifier = Modifier.padding(top= 16.dp)
                )

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverterUI()
}
