package com.adamco.chattingrecyclerview

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adamco.chattingrecyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var chattingAdapter: ChattingAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        with(binding){
            recyclerViewChatting.layoutManager = LinearLayoutManager(this@MainActivity)
            chattingAdapter= ChattingAdapter(chats)
            recyclerViewChatting.adapter = chattingAdapter

            btnSender.setOnClickListener {
                chats.add(Chat(viewType = 1, message = txtInput.text.toString()))
                chattingAdapter.notifyItemInserted(chats.size)
                txtInput.text?.clear()
            }

            btnReceiver.setOnClickListener {
                chats.add(Chat(viewType = 2, message = txtInput.text.toString()))
                chattingAdapter.notifyItemInserted(chats.size)
                txtInput.text?.clear()
            }

            btnReceiver2.setOnClickListener {
                chats.add(Chat(viewType = 3, message = txtInput.text.toString()))
                chattingAdapter.notifyItemInserted(chats.size)
                txtInput.text?.clear()
            }


            ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    chats.removeAt(position)
                    chattingAdapter.notifyItemRemoved(position)
                    Toast.makeText(this@MainActivity, "Item deleted", Toast.LENGTH_SHORT).show()
                }

            }).attachToRecyclerView(binding.recyclerViewChatting)
        }
    }

    private val chats = mutableListOf(
        Chat(viewType = 1, message = "Hi"),
        Chat(viewType = 2, message = "Hey"),
        Chat(viewType = 1, message = "How Are you?"),
        Chat(viewType = 2, message = "I am good? How are you?"),
        Chat(viewType = 3, message = "I am good? How are you?"),
        Chat(viewType = 2, message = "I am good? How are you?"),
        Chat(viewType = 3, message = "I am good? How are you?")
    )
}