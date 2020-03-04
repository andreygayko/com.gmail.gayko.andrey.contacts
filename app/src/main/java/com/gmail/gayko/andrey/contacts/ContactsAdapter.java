package com.gmail.gayko.andrey.contacts;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedHashMap;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    private Context parent;
    private int numberItems;
    //private ArrayList<String> contacts;
    private LinkedHashMap<Integer, String> contacts;
    private static int viewHolderCount;

    //public ContactsAdapter(Context parent, ArrayList<String> contacts) {
    public ContactsAdapter(Context parent, LinkedHashMap<Integer, String> contacts) {
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
        viewHolderCount++;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsViewHolder holder, int position) {
        holder.bind(position);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Integer[] keys = contacts.keySet().toArray(new Integer[0]);
                int key = keys[position-1];
                System.out.println(key);
                Intent intent = new Intent(parent, ContactActivity.class);
                intent.putExtra("id", key);
                parent.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return numberItems;
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ItemClickListener itemClickListener;
        TextView name;

        public ContactsViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.tv_preview);
            view.setOnClickListener(this);
        }

        public void bind(int index) {
            name.setText(contacts.get(index));
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            System.out.println("11111111" + getLayoutPosition());
            this.itemClickListener.onItemClick(view, getLayoutPosition());
        }
    }
}