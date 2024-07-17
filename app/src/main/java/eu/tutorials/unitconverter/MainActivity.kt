 package eu.tutorials.unitconverter

//import android.os.Build
import android.os.Bundle
//import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.modifier.modifierLocalConsumer
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.unit.sp
import eu.tutorials.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

 class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),    //this will fill the background color of the screen
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}


 @Composable
 fun UnitConverter(){

     var inputValue by remember { mutableStateOf("")}
     var outputValue by remember { mutableStateOf("")}
     var inputUnit by remember { mutableStateOf("Meters")}
     var outputUnit by remember { mutableStateOf("Meters")}
     var iExpanded by remember { mutableStateOf(false)}
     var oExpanded by remember { mutableStateOf(false)}
     val conversionFactor = remember { mutableDoubleStateOf(1.00)}
     val oConversionFactor= remember { mutableDoubleStateOf(1.00)}

     fun convertUnits(){
         val  inputValueDouble = inputValue.toDoubleOrNull() ?:0.0 // if we don't enter any value the app will not get crashed
         val result=(inputValueDouble * conversionFactor.doubleValue *100.0 / oConversionFactor.doubleValue).roundToInt()/100.0
         outputValue=result.toString()
     }

//      val customTextStyle = TextStyle(
//          fontFamily = FontFamily.Default,
//          fontSize = 16.sp,
//          color = Color.Red
//      )

     Column (modifier = Modifier.fillMaxSize(),
         verticalArrangement = Arrangement.Center,
         horizontalAlignment = Alignment.CenterHorizontally
         ) {     // in the same column, stack below each other
         Text("Unit Converter", style = MaterialTheme.typography.headlineLarge)

         Spacer(modifier = Modifier.height(16.dp))
         OutlinedTextField(value = inputValue,
             onValueChange = {
                inputValue =it   // when we entered the value in text field it will be of type it
            // here goes  what should happen, when  the value of our OutlinedTextField changes
                 convertUnits()
            },
             label = { Text("Enter Value")})
         Spacer(modifier = Modifier.height(16.dp))
         Row {      // in the same row, on the same line
             Box{
                 Button(onClick = { iExpanded= true }) {
                     Text(inputUnit)
                     Icon(Icons.Default.ArrowDropDown, contentDescription = "DropDown")
                 }
                 DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded= false }) {
                    // expanded means if the dropdown is expanded or not?
                     DropdownMenuItem(text = { Text("Millimeters" )},
                         onClick = {
                             iExpanded= false
                             inputUnit="Millimeters"
                             conversionFactor.doubleValue= 0.001
                             convertUnits()
                         }
                     )

                     DropdownMenuItem(text = { Text("Centimeters" )},
                         onClick = {
                             iExpanded= false
                             inputUnit="Centimeters"
                             conversionFactor.doubleValue= 0.01
                             convertUnits()
                         }
                     )

                     DropdownMenuItem(text = { Text("Meters" )},
                         onClick = {
                             iExpanded= false
                             inputUnit="Meters"
                             conversionFactor.doubleValue= 1.0
                             convertUnits()
                         }
                     )


                     DropdownMenuItem(text = { Text("Feet" )},
                         onClick = {
                             iExpanded= false
                             inputUnit="Feet"
                             conversionFactor.doubleValue= 0.3048
                             convertUnits()
                         }
                     )

                     DropdownMenuItem(text = { Text("Inches" )},
                         onClick = { iExpanded= false
                             inputUnit="Inches"
                             conversionFactor.doubleValue= 0.0254
                             convertUnits() }
                     )
                 }
             }
             
             Spacer(modifier = Modifier.width(32.dp))

             Box{
                 Button(onClick = { oExpanded= true }) {
                     Text(outputUnit)
                     Icon(Icons.Default.ArrowDropDown, contentDescription = "DropDown")
                 }
                 DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                     // expanded means if the dropdown is expanded or not?
                     DropdownMenuItem(text = { Text("Millimeters" )},
                         onClick = {
                             oExpanded= false
                             outputUnit="Millimeters"
                             oConversionFactor.doubleValue= 0.001
                             convertUnits()
                         }
                     )

                     DropdownMenuItem(text = { Text("Centimeters" )},
                         onClick = {
                             oExpanded= false
                             outputUnit="Centimeters"
                             oConversionFactor.doubleValue= 0.01
                             convertUnits()
                         }
                     )

                     DropdownMenuItem(text = { Text("Meters" )},
                         onClick = {
                             oExpanded= false
                             outputUnit="Meters"
                             oConversionFactor.doubleValue= 1.0
                             convertUnits()
                         }
                     )


                     DropdownMenuItem(text = { Text("Feet" )},
                         onClick = {
                             oExpanded= false
                             outputUnit="Feet"
                             oConversionFactor.doubleValue= 0.3048
                             convertUnits()
                         }
                     )

                     DropdownMenuItem(text = { Text("Inches" )},
                         onClick = {
                             oExpanded= false
                             outputUnit="Inches"
                             oConversionFactor.doubleValue= 0.0254
                             convertUnits()
                         }
                     )
                 }
             }
         }
         Spacer(modifier = Modifier.height(16.dp))
         Text("Result: $outputValue $outputUnit", style = MaterialTheme.typography.headlineMedium)
     }
 }




 @Preview(showBackground = true)
 @Composable
 fun UnitConverterPreview(){
    UnitConverter()
 }