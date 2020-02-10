package th.ac.su.konthanan.bmihw

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tvResult    = findViewById<TextView>(R.id.tvResult)
        var tvResult2 = findViewById<TextView>(R.id.tvResult2)

        var edtHeight = findViewById<EditText>(R.id.edtH)
        var edtWeight = findViewById<EditText>(R.id.edtW)

        var btnCal    = findViewById<Button>(R.id.btnCal)

        btnCal.setOnClickListener {

            var weight = edtWeight.text.toString().toDouble()
            var height = edtHeight.text.toString().toDouble()
            height     = height/100

            var bmi:Double = weight/(height*height)

            var result = "fat"

            if(bmi>30)
                result = "Obese"
            else if (bmi> 25)
                result = "Overweight"
            else if (bmi> 18)
                result = "Healthy"
            else
                result = "Underweight"

//            tvBmi.setText(bmi.toString())
//            tvResult.setText(result)

            hideKeyboard()

            var intent = Intent(this@MainActivity,SecondActivity::class.java)
            intent.putExtra("bmi",bmi)
            intent.putExtra("result",result)
            intent.putExtra("height",height)
            intent.putExtra("weight",weight)

            startActivity(intent)

        }

    }
    fun Double.round(decimals: Int): Double {
        var multiplier = 1.0
        repeat(decimals) { multiplier *= 10 }
        return kotlin.math.round(this * multiplier) / multiplier
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

}

