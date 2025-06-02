package com.example.co_sanjorjo_tpfinals

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val nameField =  findViewById<EditText>(R.id.edtTextName)
        val passwordField = findViewById<EditText>(R.id.password)
        val emailField = findViewById<EditText>(R.id.edtTextEmail)
        val confirmPassword = findViewById<EditText>(R.id.ConfirmPassword)
        val save = findViewById<Button>(R.id.btnSave)

        save.setOnClickListener{
            val name = nameField.text.toString()
            val password = passwordField.text.toString()
            val email = emailField.text.toString()
            val confirmPass = confirmPassword.text.toString()
            saveUserData(name, email, password, confirmPass)
            Toast.makeText(this, "Saving user...", Toast.LENGTH_SHORT).show()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

    }
    fun saveUserData(name: String, email: String, password: String, confirmPass: String) {
        val client = OkHttpClient()

        val formBody = FormBody.Builder()
            .add("name", name)
            .add("email", email)
            .add("password", password)
            .add("confirmPass", confirmPass)
            .build()

        val request = Request.Builder()
            .url("http://10.0.2.2:8000/api/save/")
            .post(formBody)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val responseText = response.body?.string()
                runOnUiThread {
                    Toast.makeText(this@MainActivity, "Server response: $responseText", Toast.LENGTH_LONG).show()
                }
            }
        })
    }
}