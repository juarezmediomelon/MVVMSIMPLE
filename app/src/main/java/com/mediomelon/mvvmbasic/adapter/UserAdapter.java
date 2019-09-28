package com.mediomelon.mvvmbasic.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mediomelon.mvvmbasic.R;
import com.mediomelon.mvvmbasic.model.Hero;
import com.mediomelon.mvvmbasic.model.User;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    Context mCtx;
    List<User> userList;
    private OnItemClickListener mListener;

    public UserAdapter(Context mCtx, List<User> userList) {
        this.mCtx = mCtx;
        this.userList = userList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.cardview_user, parent, false);
        return new UserViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);

        //Picasso.get().load(user.getImageurl()).into(holder.imageView);
        holder.userName.setText(user.getName());
        holder.userUsername.setText(user.getUsername());
        holder.userEmail.setText(user.getEmail());
        holder.userAddressCity.setText(userList.get(position).getAddress().getCity());
        holder.userPhone.setText(user.getName());
        holder.userWebsite.setText(user.getName());
        holder.userCompanyName.setText(userList.get(position).getCompany().getName());

    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.user_name)
        TextView userName;
        @BindView(R.id.user_username)
        TextView userUsername;
        @BindView(R.id.user_email)
        TextView userEmail;
        @BindView(R.id.user_address_city)
        TextView userAddressCity;
        @BindView(R.id.user_phone)
        TextView userPhone;
        @BindView(R.id.user_website)
        TextView userWebsite;
        @BindView(R.id.user_company_name)
        TextView userCompanyName;

        public UserViewHolder(View view, final OnItemClickListener listener) {
            super(view);
            ButterKnife.bind(this, view);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
