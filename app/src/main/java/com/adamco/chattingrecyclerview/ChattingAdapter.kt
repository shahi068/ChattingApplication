package com.adamco.chattingrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adamco.chattingrecyclerview.databinding.RecieverTwoViewBinding
import com.adamco.chattingrecyclerview.databinding.RecieverViewBinding
import com.adamco.chattingrecyclerview.databinding.SenderViewBinding

class ChattingAdapter(private val chats: List<Chat>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        // This is to see if the view type given is SENDER_VIEW (1), if it is then we are inflating the sender layout
        return when (viewType) {
            SENDER_VIEW -> {
                val senderBinding = SenderViewBinding.inflate(layoutInflater, parent, false)
                SenderViewHolder(senderBinding)
            }
            // This is to see if the view type given is RECIEVER_VIEW (2), if it is then we are inflating the receiver layout
            RECIEVER_VIEW -> {
                val receiverBinding = RecieverViewBinding.inflate(layoutInflater, parent, false)
                RecieverViewHolder(receiverBinding)
            }
            // Else RECIEVER_TWO_VIEW (3) is inflated
            RECIEVER_TWO_VIEW -> {
                val receiverTwoBinding = RecieverTwoViewBinding.inflate(layoutInflater, parent, false)
                RecieverTwoViewHolder(receiverTwoBinding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        // This checks the chat item and returns the view type of it depending on what was passed
        // It will inflate the appropriate layout
        return chats[position].viewType
    }

    override fun getItemCount() = chats.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        // Binding the holder view to the layout
        when (holder) {
            is SenderViewHolder -> holder.bind(chats[position])
            is RecieverViewHolder -> holder.bind(chats[position])
            is RecieverTwoViewHolder -> holder.bind(chats[position])
        }
    }

    inner class SenderViewHolder(private val view: SenderViewBinding) : RecyclerView.ViewHolder(view.root) {
        // This sets the text element in the sender_view.xml UI to whatever was passed for the message parameter
        // chat is of type Chat, which is the data class
        fun bind(chat: Chat) {
            view.txtSender.text = chat.message
        }
    }

    inner class RecieverViewHolder(private val view: RecieverViewBinding) : RecyclerView.ViewHolder(view.root) {
        // Same as above but for receiver
        fun bind(chat: Chat) {
            view.txtReciever.text = chat.message
        }
    }

    inner class RecieverTwoViewHolder(private val view: RecieverTwoViewBinding) : RecyclerView.ViewHolder(view.root) {
        // Also same as above but for receiver2
        fun bind(chat: Chat) {
            view.txtRecieverTwo.text = chat.message
        }
    }

    companion object {
        // Basically our Utils constant variables
        const val SENDER_VIEW = 1
        const val RECIEVER_VIEW = 2
        const val RECIEVER_TWO_VIEW = 3
    }
}
