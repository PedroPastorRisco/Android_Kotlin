package com.example.userssp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userssp.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferences = getPreferences(Context.MODE_PRIVATE)
        val isFirstTime = preferences.getBoolean(getString(R.string.sp_first_time),true)

        if(isFirstTime){
            val dialogView = layoutInflater.inflate(R.layout.material_dialog,null)
            MaterialAlertDialogBuilder(this)
                .setTitle(R.string.dialog_title)
                .setView(dialogView)
                .setCancelable(true)
                .setPositiveButton(R.string.dialog_confirm) { _, _ ->
                    val userName = dialogView.findViewById<TextInputEditText>(R.id.etUserName)
                        .text.toString()
                    with(preferences.edit()) {
                        putBoolean(getString(R.string.sp_first_time), false)
                        putString(getString(R.string.sp_username), userName)
                            .apply()
                    }
                    Toast.makeText(this,"Bienvenido $userName",Toast.LENGTH_SHORT).show()
                }
                .setNeutralButton(R.string.neutral_option,null)
                .show()
        }else{
            val userName = preferences.getString(getString(R.string.sp_username)
                ,getString(R.string.etUser_hint))
            Toast.makeText(this,"Bienvenido $userName",Toast.LENGTH_SHORT).show()
        }


        userAdapter = UserAdapter(getUsers(),this)
        linearLayoutManager = LinearLayoutManager(this)

        binding.rvUsers.apply {
            setHasFixedSize(true)
            adapter = userAdapter
            layoutManager = linearLayoutManager
        }
    }

    private fun getUsers(): MutableList<User>{
        val users = mutableListOf<User>()

        val cristiano = User(1,"Cristiano Ronaldo","Dos Santos Aveiro","https://storage.googleapis.com/exafm-uploads/2019/12/111220191576027385.png")
        val bruce = User(2,"Bruce","Wayne","https://static.wikia.nocookie.net/batman/images/4/49/1929848-bruce_wayne.jpg/revision/latest?cb=20140625152104&path-prefix=es")
        val rosita = User(3,"Rosita","Bandolero","https://c10.patreonusercontent.com/3/eyJ3Ijo0MDB9/patreon-media/p/reward/5796494/8dd02b84f1a6451795470c61ef5e64e0/1.png?token-time=2145916800&token-hash=lqN69rXIGcOWhxtssGgtbpZheTZNGJBXYPznQ3Ft5OQ%3D")
        val cthulhu = User(4,"Cthulhu","UwU","https://preview.redd.it/1gqvc8lyxv831.jpg?width=700&format=pjpg&auto=webp&s=bb34be294087b19f550258c3b043e230ca59e64a")

        users.add(cristiano)
        users.add(bruce)
        users.add(rosita)
        users.add(cthulhu)
        users.add(cristiano)
        users.add(bruce)
        users.add(rosita)
        users.add(cthulhu)
        users.add(cristiano)
        users.add(bruce)
        users.add(rosita)
        users.add(cthulhu)
        users.add(cristiano)
        users.add(bruce)
        users.add(rosita)
        users.add(cthulhu)
        users.add(cristiano)
        users.add(bruce)
        users.add(rosita)
        users.add(cthulhu)


        return users
    }

    override fun onClick(user: User) {
        Toast.makeText(this, user.getFullName(), Toast.LENGTH_SHORT).show()
    }
}