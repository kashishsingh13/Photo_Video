package com.example.module5application.Que2

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.module5application.Adapter.itemAdapter
import com.example.module5application.Database.ItemDatabase
import com.example.module5application.Model.Item
import com.example.module5application.Que3.MainActivity
import com.example.module5application.Que3.TaskAddActivity
import com.example.module5application.Que6.ImagePickActivity
import com.example.module5application.R
import com.example.module5application.databinding.ActivityNoteBinding
import java.util.Random
import kotlin.concurrent.thread

class NoteActivity : AppCompatActivity() {
    private lateinit var binding:ActivityNoteBinding
    private var itemList= mutableListOf<Item>()
    private lateinit var itemAdapter: itemAdapter
    var db: ItemDatabase? = null
    private var item: Item? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        itemAdapter = itemAdapter(this, itemList)
        binding.recycleview.layoutManager = LinearLayoutManager(this)
        binding.recycleview.adapter = itemAdapter

        db = ItemDatabase.getInstance(this)

        item = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("item", Item::class.java)
        } else {
            intent.getParcelableExtra("item")
        }

        item?.let {
            binding.add.text = "Update item"
            binding.title.setText(it.title)
            binding.message.setText(it.message)
        }
        itemAdapter.itemDeleteClickListener = { position, item ->
            showAlertDialog(item, position)

        }
        binding.random.setOnClickListener {
            val random = Random().nextInt(3) // Generates a random number between 0 and 2

            when (random) {
                0 -> startActivity(Intent(this, MainActivity::class.java))
                1 -> startActivity(Intent(this, TaskAddActivity::class.java))
                2 -> startActivity(Intent(this, ImagePickActivity::class.java))
            }
        }

        binding.add.setOnClickListener {

            var title = binding.title.text.toString().trim()
            var message = binding.message.text.toString().trim()

            updaterecord(title, message)
        }


        itemAdapter.itemeditclicklistener={position, item ->
            intent = Intent(this,NoteActivity::class.java)
            intent.putExtra("item",item )
            startActivity(intent)
        }

    }

    private fun readCuisineList() {
        itemList = ItemDatabase.getInstance(this)?.itemDao()!!.getAllItem()
        itemAdapter.setItems(itemList)
    }
    override fun onResume() {
        super.onResume()
        readCuisineList()
    }
    private fun showAlertDialog(item: Item,position:Int) {
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Delete")
        builder.setMessage("Are you sure you want to delete this item?")
        builder.setPositiveButton("Delete", DialogInterface.OnClickListener { dialog, which ->
            ItemDatabase.getInstance(this)?.itemDao()!!.deleteItem(item)
            Toast.makeText(this, "Item deleted", Toast.LENGTH_SHORT).show()
            itemAdapter.deleteItem(position)
        })
        builder.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->

        })
        var dialog = builder.create()
        dialog.show()

    }



    private fun updaterecord(title: String, message: String) {
        var mess = ""

        thread(start = true) {
            var itemobject = Item(
                title = title, message = message,
                id = if (item != null) item!!.id else 0,
                createdAt = if (item != null) item!!.createdAt else System.currentTimeMillis(),
            )
            try {
                if (item != null) {
                    //update
                    db!!.itemDao().updateItem(itemobject)
                    mess = "Record updated successfully"
                } else {
                    //add
                    db!!.itemDao().insertItem(itemobject)
                    mess = "Record added successfully"

                }

                runOnUiThread {
                    Toast.makeText(this, "$mess", Toast.LENGTH_SHORT).show()
                    onBackPressedDispatcher.onBackPressed()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
//import android.content.Intent
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import kotlinx.android.synthetic.main.activity_second.*
//import kotlin.random.Random
//
//class SecondActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_second)
//
//        button.setOnClickListener {
//            startRandomActivity()
//        }
//    }
//
//    private fun startRandomActivity() {
//        val randomActivityIndex = Random.nextInt(4)
//        when (randomActivityIndex) {
//            0 -> startActivityWithFlag(FirstActivity::class.java)
//            1 -> startActivityWithFlag(SecondActivity::class.java)
//            2 -> startActivityWithFlag(ThirdActivity::class.java)
//            3 -> startActivityWithFlag(FourthActivity::class.java)
//        }
//    }
//
//    private fun startActivityWithFlag(activityClass: Class<*>) {
//        val intent = Intent(this, activityClass)
//        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
//        startActivity(intent)
//    }
//
//    override fun onBackPressed() {
//        startActivityWithFlag(MainActivity::class.java)
//        finish() // Finish this activity
//    }
//}
//import android.content.Intent
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import kotlinx.android.synthetic.main.activity_main.*
//import kotlin.random.Random
//
//class MainActivity : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        button.setOnClickListener {
//            val randomActivityIndex = Random.nextInt(4)
//            when (randomActivityIndex) {
//                0 -> startActivityWithFlag(FirstActivity::class.java)
//                1 -> startActivityWithFlag(SecondActivity::class.java)
//                2 -> startActivityWithFlag(ThirdActivity::class.java)
//                3 -> startActivityWithFlag(FourthActivity::class.java)
//            }
//        }
//    }
//
//    private fun startActivityWithFlag(activityClass: Class<*>) {
//        val intent = Intent(this, activityClass)
//        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
//        startActivity(intent)
//    }
//}