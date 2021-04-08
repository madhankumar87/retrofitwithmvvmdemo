package com.mvi.apiwithmvvm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mvi.apiwithmvvm.R;
import com.mvi.apiwithmvvm.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.BaseViewHolder> {
    private static final String TAG = "Adapter";
    private List<User> userList;
    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_item, parent, false));
    }
    @Override
    public int getItemViewType(int position) {
        return 0;
    }
    @Override
    public int getItemCount() {
        if (userList != null && userList.size() > 0) {
            return userList.size();
        } else {
            return 0;
        }
    }
    public class ViewHolder extends BaseViewHolder {

        TextView txtName;
        TextView txtEmail;
        TextView txtPhone;
        public ViewHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txt_name);
            txtEmail = itemView.findViewById(R.id.txt_email);
            txtPhone = itemView.findViewById(R.id.txt_phone);
        }
        protected void clear() {
            txtName.setText("");
            txtEmail.setText("");
            txtPhone.setText("");
        }
        public void onBind(int position) {
            super.onBind(position);
            final User user = userList.get(position);

            if (user.getName() != null) {
                txtName.setText(user.getName());
            }
            if (user.getEmail() != null) {
                txtEmail.setText(user.getEmail());
            }
            if (user.getPhone() != null) {
                txtPhone.setText(user.getPhone());
            }

        }
    }

    public static abstract class BaseViewHolder extends RecyclerView.ViewHolder {
        private int mCurrentPosition;
        public BaseViewHolder(View itemView) {
            super(itemView);
        }
        protected abstract void clear();
        public void onBind(int position) {
            mCurrentPosition = position;
            clear();
        }
        public int getCurrentPosition() {
            return mCurrentPosition;
        }
    }
}