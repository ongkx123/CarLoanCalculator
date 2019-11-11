package com.example.carloancalculator

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.LocaleList
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.core.os.ConfigurationCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCalculate.setOnClickListener(){
            calculateLoan()
        }

    }

    fun calculateLoan() {

        if(editTextCarPrice.text.isEmpty()){
            editTextCarPrice.setError(getString(R.string.error_input))
            return
        }
        val locale = Locale.getDefault()
        val current = Currency.getInstance(locale)

        val carPrice: Int = editTextCarPrice.text.toString().toInt()
        val downPayment: Int = editTextDownPayment.text.toString().toInt()
        val loan: Int = carPrice - downPayment
        val interestRate: Double = editTextInterestRate.text.toString().toDouble()
        val period: Int = editTextLoanPeriod.text.toString().toInt()
        val interest:Double = loan * (interestRate/100) * period
        val repayment:Double = (loan+interest) / period/ 12

        textViewLoan.setText(getString(R.string.loan) + current +"${loan}")
        textViewInterest.setText(getString(R.string.interest) + current +"${interest}")
        textViewMonthlyRepayment.setText(getString(R.string.monthly_repayment)+ current + "${repayment}")


    }

    fun reset(view: View){

        editTextCarPrice.setText("")
        editTextDownPayment.setText("")
        editTextLoanPeriod.setText("")
        editTextInterestRate.setText("")
        textViewLoan.setText(R.string.loan)
        textViewInterest.setText(R.string.interest)
        textViewMonthlyRepayment.setText(R.string.monthly_repayment)


        Toast.makeText(this,"Reset",Toast.LENGTH_SHORT).show()


    }
}
