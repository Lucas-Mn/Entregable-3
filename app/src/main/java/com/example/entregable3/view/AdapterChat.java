package com.example.entregable3.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.entregable3.R;
import com.example.entregable3.model.pojo.Message;

import java.util.List;

public class AdapterChat extends RecyclerView.Adapter {

    private List<Message> messages;

    public void setList(List<Message> messages)
    {
        this.messages = messages;
        notifyDataSetChanged();
    }

    public void newMessage(Message message)
    {
        messages.add(message);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = new ViewHolderMessage(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_message, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderMessage vh = (ViewHolderMessage)holder;
        vh.bind(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    private class ViewHolderMessage extends RecyclerView.ViewHolder {

        Message message;
        TextView lblSenderName, lblContent, lblDate;

        public ViewHolderMessage(@NonNull View itemView) {
            super(itemView);
            lblSenderName = itemView.findViewById(R.id.cell_message_sender_name);
            lblContent = itemView.findViewById(R.id.cell_message_content);
            lblDate = itemView.findViewById(R.id.cell_message_date);
        }

        public void bind(Message message)
        {
            this.message = message;
            lblSenderName.setText(message.getSender_name());
            lblContent.setText(message.getContent());
            lblDate.setText(message.getDate().toString());
        }
    }
}
