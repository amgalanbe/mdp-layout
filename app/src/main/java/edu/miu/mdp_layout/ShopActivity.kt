package edu.miu.mdp_layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import edu.miu.mdp_layout.databinding.ActivityShopBinding

@Suppress("DEPRECATION")
class ShopActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShopBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val currentUser = intent.getSerializableExtra("currentUser") as UserAccount
        binding.welcomeTV.text = "Welcome " + currentUser.username

        Toast.makeText(this, "Hi there! This is a Toast.", Toast.LENGTH_LONG).show()
    }

    fun btnClick(view: View) {
        var msg = ""
        when(view.id){
            R.id.booksIV -> msg = "Books menu selected"
            R.id.clothingIV -> msg = "Clothing menu selected"
            R.id.foodsIV -> msg = "Foods menu selected"
            R.id.medicineIV -> msg = "Medicine menu selected"
        }

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}