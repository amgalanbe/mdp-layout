package edu.miu.mdp_layout

import android.app.Activity
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import edu.miu.mdp_layout.databinding.ActivityShopBinding
import edu.miu.mdp_layout.databinding.ActivitySignupBinding
import kotlin.math.ln

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupBtn.setOnClickListener {
            var username = binding.emailET.text.toString()
            var password = binding.passwordET.text.toString()
            var fname = binding.fnameET.text.toString()
            var lname = binding.lnameET.text.toString()
            if(username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Username and password is required", Toast.LENGTH_LONG).show()
            } else {
                val rintent = intent
                var user = UserAccount(username, password, fname, lname)
                rintent.putExtra("user", user as java.io.Serializable)
                setResult(Activity.RESULT_OK, rintent)
                finish()
            }

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(Activity.RESULT_CANCELED)
        finish()
    }
}