package org.hyperskill.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText.isEnabled = false

        dotButton.setOnClickListener {
            with(editText) {
                val currentText = this.text.toString()
                val dot = context.getString(R.string.dot)
                if (!currentText.contains(dot)) this.append((it as AppCompatButton).text)
            }
        }

        var expression = ""

        clearButton.setOnClickListener {
            editText.text.clear()
            expression = ""
            editText.hint = "0"
        }

        listOf(addButton, subtractButton, divideButton, multiplyButton).forEach { operator ->
            operator.setOnClickListener { button ->
                button as AppCompatButton
                if (editText.text.isBlank()) {
                    if (button.text == subtractButton.text) {
                        editText.append(subtractButton.text)
                    }
                }else if(editText.text.length >= 1  || editText.text.all { it.isDigit() }){
                    expression = editText.text.toString()
                    expression += button.text
                    editText.hint = editText.text
                    editText.text.clear()
                }

                println(expression)
            }
        }

        equalButton.setOnClickListener {
            if(editText.text.isNotBlank()){
                expression += editText.text
                val numbers = expression.split("(?<=\\d)[*÷+-]".toRegex()).map { it.toDouble() }
                when("(?<=\\d)[*÷+-]".toRegex().find(expression)!!.value){
                    applicationContext.getString(R.string.multiply) -> expression = (numbers[0] * numbers[1]).toString()
                    applicationContext.getString(R.string.add) -> expression = (numbers[0] + numbers[1]).toString()
                    applicationContext.getString(R.string.divide) -> expression = (numbers[0] / numbers[1]).toString()
                    applicationContext.getString(R.string.subtract) -> expression = (numbers[0] - numbers[1]).toString()
                }

                if(expression.contains(".0")) expression = expression.split(".")[0]

                editText.setText(expression)
            }
        }
    }
}
