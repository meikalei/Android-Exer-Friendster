package io.acssuplb.github.friendster.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.acssuplb.github.friendster.Friend;
import io.acssuplb.github.friendster.R;

public class FriendListAdapter extends RecyclerView.Adapter<FriendListAdapter.FriendHolder> {

    private List<Friend> friendList;
    private LayoutInflater inflater;

    private ItemClickCallback itemClickCallback;

    public interface ItemClickCallback {
        void onItemClick(int p);
    }

    public void setItemClickCallback(final ItemClickCallback itemClickCallback) {
        this.itemClickCallback = itemClickCallback;
    }

    public FriendListAdapter(List<Friend> friendList, Context c) {
        inflater = LayoutInflater.from(c);
        this.friendList = friendList;
    }

    class FriendHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        View container;

        public FriendHolder(View v) {
            super(v);
            name = (TextView)v.findViewById(R.id.lbl_item_text);
            container = (View)v.findViewById(R.id.cont_item_root);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickCallback.onItemClick(getAdapterPosition());
        }
    }

    @Override
    public FriendListAdapter.FriendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item,parent,false);
        return new FriendHolder(view);
    }

    @Override
    public void onBindViewHolder(FriendHolder holder, int position) {
        Friend item = friendList.get(position);
        holder.name.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return friendList.size();
    }

}
