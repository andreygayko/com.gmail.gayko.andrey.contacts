package com.gmail.gayko.andrey.contacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    private Context parent;
    private int numberItems;
    private ArrayList<String> contacts;
    private static int viewHolderCount;

    public ContactsAdapter(Context parent, ArrayList<String> contacts) {
        this.numberItems = contacts.size();
        this.parent = parent;
        this.contacts = contacts;
        viewHolderCount = 0;
    }

    @NonNull
    @Override
    public ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutForListItem = R.layout.contact_preview;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutForListItem, parent, false);
        ContactsViewHolder viewHolder = new ContactsViewHolder(view);
        viewHolderCount ++;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return numberItems;
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder {

        TextView name;

        public ContactsViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.tv_preview);
        }

        public void bind(int index) {
            name.setText(contacts.get(index));
        }

    }
}