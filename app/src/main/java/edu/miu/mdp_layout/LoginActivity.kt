package edu.miu.mdp_layout

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import edu.miu.mdp_layout.databinding.ActivityLoginBinding

@Suppress("DEPRECATED")
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var userList: ArrayList<UserAccount>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        populateUserList()

        binding.signinBtn.setOnClickListener {
            for(user: UserAccount in userList) {
                if(user.username.equals(binding.emailET.text.toString(), true) &&
                    user.password.equals(binding.passwordET.text.toString(), false)) {
                    val shopIntent = Intent(this, ShopActivity::class.java)
                    shopIntent.putExtra("currentUser", user as java.io.Serializable)
                    startActivity(shopIntent)
                    break
                }
            }
        }

        var resultContracts = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.resultCode == Activity.RESULT_OK) {
                var user = result.data?.getSerializableExtra("user") as UserAccount
                userList.add(user)
                Toast.makeText(this, "Successfully Signed up", Toast.LENGTH_LONG).show()
            }
        }

        binding.signupBtn.setOnClickListener {

            val signupIntent = Intent(this, SignupActivity::class.java)
//            startActivity(signupIntent);
            resultContracts.launch(signupIntent)

        }

        binding.forgotPasswordTV.setOnClickListener {
            val email = binding.emailET.text.toString()
            if (email.isNotEmpty()) {
                var foundUser: UserAccount? = null
                for(user: UserAccount in userList) {
                    if(user.username.equals(email)) {
                        foundUser = user
                        break
                    }
                }

                if(foundUser != null) {
                    val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"))
                    intent.putExtra(Intent.EXTRA_EMAIL, email)
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Forgot Password")
                    intent.putExtra(Intent.EXTRA_TEXT, "Your password is: " + foundUser.password)
                    if (emailIntent.resolveActivity(packageManager) != null)
                        startActivity(emailIntent)
                } else {
                    Toast.makeText(this, "User not found!", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "Please enter email!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun populateUserList() {
        userList = ArrayList()
        userList.add(UserAccount("johnd@miu.edu", "pass", "John", "Doe"))
        userList.add(UserAccount("timb@miu.edu", "pass", "Tim", "Burton"))
        userList.add(UserAccount("janed@miu.edu", "pass", "Jane", "Doe"))
        userList.add(UserAccount("taylors@miu.edu", "pass", "Taylor", "Swift"))
        userList.add(UserAccount("bradp@miu.edu", "pass", "Brad", "Pitt"))
    }

}