package com.example.convertingunits

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.convertingunits.ui.theme.ConvertingUnitsTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ConvertingUnitsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitConverter() {

    var inputValue by remember { mutableStateOf(" ") }
    var outputValue by remember { mutableStateOf(" ") }
    var inputunit by remember { mutableStateOf("meters") }
    var outputunit by remember { mutableStateOf("meters") }
    var iExpended by remember { mutableStateOf(false) }
    var oExpended by remember { mutableStateOf(false)}
    val conversionfactor = remember { mutableStateOf(1.0) }
    val oconversionfactor = remember { mutableStateOf(1.0) }

    fun convertUnit()
    {
        val inputValueDouble=inputValue.toDoubleOrNull()?:0.0
        val result=(inputValueDouble*conversionfactor.value *100/oconversionfactor.value)/100
        outputValue=result.toString()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(text = "Unit Converter")
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue=it
        },
            label = {"Enter value"})
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Box {
                        Button(onClick = { iExpended=true }) {
                            Text(text = inputunit.toString())
                            Icon(
                                Icons.Default.ArrowDropDown,
                                contentDescription = "Arrow Down"
                            )
                        }

                        DropdownMenu(expanded = iExpended, onDismissRequest = { iExpended=false }) {
                            DropdownMenuItem(
                                text = { Text( "cm")},
                                onClick = {
                                    iExpended=false
                                    inputunit="cm"
                                    conversionfactor.value=0.01
                                    convertUnit()
                                })
                            DropdownMenuItem(
                                text = { Text( "meter")},
                                onClick = {
                                    iExpended=false
                                    inputunit="meter"
                                    conversionfactor.value=1.0
                                    convertUnit()
                                })
                            DropdownMenuItem(
                                text = { Text( "mm")},
                                onClick = {
                                    iExpended=false
                                    inputunit="mm"
                                    conversionfactor.value=0.001
                                    convertUnit()
                                })

                            DropdownMenuItem(
                                text = { Text( "feet")},
                                onClick = {
                                    iExpended=false
                                    inputunit="feet"
                                    conversionfactor.value=0.3048
                                    convertUnit()
                                })
                        }
                    }
            Spacer(modifier = Modifier.width(30.dp))
            Box {
                            Button(onClick = { oExpended=true }) {
                                Text(text = outputunit.toString())
                                Icon(
                                    Icons.Default.ArrowDropDown,
                                    contentDescription = "Arrow Down"
                                )
                            }

                            DropdownMenu(expanded = oExpended, onDismissRequest = { oExpended=false}) {
                                DropdownMenuItem(
                                    text = { Text( "meters")},
                                    onClick = {
                                        oExpended=false
                                        outputunit="meter"
                                        oconversionfactor.value=1.0
                                        convertUnit()
                                    })
                                DropdownMenuItem(
                                    text = { Text( "cm")},
                                    onClick = {
                                        oExpended=false
                                        outputunit="cm"
                                        oconversionfactor.value=0.01
                                        convertUnit()
                                    })
                                DropdownMenuItem(
                                    text = { Text( "mm")},
                                    onClick = {
                                        oExpended=false
                                        outputunit="mm"
                                        oconversionfactor.value=0.001
                                        convertUnit()
                                    })

                                DropdownMenuItem(
                                    text = { Text( "feet")},
                                    onClick = {
                                        oExpended=false
                                        outputunit="feet"
                                        oconversionfactor.value=0.3048
                                        convertUnit()
                                    })
                            }
                        }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = outputValue+" "+outputunit)
    }
}
