package com.gmail.gayko.andrey.contacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedHashMap;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    private LinkedHashMap<Integer, String> contacts;
    private ItemClickListener itemClickListener;

    public ContactsAdapter(LinkedHashMap<Integer, String> contacts, ItemClickListener itemClickListener) {
        this.contacts = contacts;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_preview, parent, false);
        return new ContactsViewHolder(view, itemClickListener);
    }


    @Override
    public int getItemCount() {
        return contacts.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        holder.bind(position);
    }

    class ContactsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ItemClickListener itemClickListener;
        TextView name;

        public ContactsViewHolder(View view, ItemClickListener itemClickListener) {
            super(view);
            this.itemClickListener = itemClickListener;
            name = view.findViewById(R.id.tv_preview);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(getAdapterPosition());
        }

        public void bind(int index) {
            name.setText(contacts.get(index + 1));
        }
    }

    interface ItemClickListener {
        void onItemClick(int position);
    }

}