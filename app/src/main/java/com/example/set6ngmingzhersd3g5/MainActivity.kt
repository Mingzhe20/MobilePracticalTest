package com.example.set6ngmingzhersd3g5

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var loanAmount: EditText
    lateinit var downPayment: EditText
    lateinit var loanPeriod: EditText
    lateinit var bankSelection: Spinner
    lateinit var btnCalculate: Button
    lateinit var interestResult: TextView
    lateinit var monthlyPaymentResult: TextView
    lateinit var interestRateResult: TextView
    lateinit var btnCall: Button
    lateinit var btnEmail: Button
    lateinit var phoneNumber:String
    lateinit var emailAddress:String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loanAmount = findViewById(R.id.loanAmount_edit)
        downPayment = findViewById(R.id.downPayment_edit)
        loanPeriod = findViewById(R.id.loanPeriod_edit)
        bankSelection = findViewById(R.id.bank_selection)
        btnCalculate = findViewById(R.id.btnCalculate)
        interestResult = findViewById(R.id.interest_result)
        monthlyPaymentResult = findViewById(R.id.monthlyPayment_result)
        interestRateResult = findViewById(R.id.interestRate_result)
        btnCall = findViewById(R.id.btnCall)
        btnEmail = findViewById(R.id.btnEmail)


        ArrayAdapter.createFromResource(this, R.array.banks, android.R.layout.simple_spinner_item)
            .also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                bankSelection.adapter = adapter
            }
        btnCalculate.setOnClickListener { calculateLoan() }
        btnCall.setOnClickListener { makeCall() }
        btnEmail.setOnClickListener { sendEmail() }


    }


    fun calculateLoan() {
        var interestRate = 0.0
        var loan = loanAmount.text.toString().toDouble()
        var down = downPayment.text.toString().toDouble()
        var period = loanPeriod.text.toString().toInt()

        if (bankSelection.selectedItemPosition == 0) {
            interestRate = 0.0301
            phoneNumber = "tel:03-3110 1111"
            emailAddress = "customersupport@hongleong.com"
        } else if (bankSelection.selectedItemPosition == 1) {
            interestRate = 0.0296
            phoneNumber = "tel:03-3110 2222"
            emailAddress = "customersupport@maybank.com"
        } else if (bankSelection.selectedItemPosition == 2) {
            interestRate = 0.0286
            phoneNumber = "tel:03-3110 3333"
            emailAddress = "customersupport@publicbank.com"
        }

        var interest = (loan - down) * period * interestRate
        var monthlyPayment = (loan - down + interest) / 12 * period



        interestRateResult.text = (interestRate * 100).toString() + "%"
        interestResult.text = "RM" + interest.toString()
        monthlyPaymentResult.text = "RM" + monthlyPayment.toString()




    }

    fun makeCall() {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse(phoneNumber)

        startActivity(intent)

    }

    fun sendEmail() {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:$emailAddress")

        startActivity(intent)
    }
}