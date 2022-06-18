package com.example.instagram.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.instagram.R;
import com.example.instagram.fragments.FeedFragment;
import com.example.instagram.fragments.PostDetailsFragment;
import com.example.instagram.models.Post;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private List<Post> mPosts;
    private Context context;
    private static final String TAG = "Post_Adapter";

    public FeedAdapter(Context context, List<Post> posts) {
        mPosts = posts;
        this.context = context;
    }

    @NonNull
    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = mPosts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvUsername;
        private TextView tvDescription;
        private ImageView ivImage;
        private ImageButton ibLike;
        private TextView tvLikeCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDescription =  itemView.findViewById(R.id.tvDescription);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ibLike =  itemView.findViewById(R.id.ibLike);
            tvLikeCount =  itemView.findViewById(R.id.tvLikeCount);
        }

        public void bind(Post post) {
            tvDescription.setText(post.getDescription());
            tvUsername.setText(post.getUser().getUsername());
            tvLikeCount.setText(post.likeCountsDisplayText());
            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivImage);
                imageOnClickListener(post);
            }
            likeButtonOnClickListener(post);
        }

        private void imageOnClickListener(Post post) {
            ivImage.setOnClickListener(v -> {
                AppCompatActivity activity =  (AppCompatActivity) itemView.getContext();
                FragmentTransaction ft =  activity.getSupportFragmentManager().beginTransaction();
                PostDetailsFragment postDetailsFragment = new PostDetailsFragment();
                Bundle args = new Bundle();
                args.putParcelable("post", Parcels.wrap(post));
                postDetailsFragment.setArguments(args);
                ft.replace(R.id.container, postDetailsFragment).commit();
            });
        }

        private void likeButtonOnClickListener(Post post) {
            List listLikedBy =  post.getLikedBy();
            if (listLikedBy.contains(post.getUser().getObjectId())) {
                ibLike.setColorFilter(R.color.white);
            } else {
                listLikedBy.add(post.getUser().getObjectId());
                ibLike.setColorFilter(R.color.black);
            }

            ibLike.setOnClickListener(v -> {
                if (listLikedBy.contains(post.getUser().getObjectId())) {
                    listLikedBy.remove(post.getUser().getObjectId());
                    ibLike.setColorFilter(R.color.white);
                } else {
                    listLikedBy.add(post.getUser().getObjectId());
                    ibLike.setColorFilter(R.color.black);
                }
                post.setLikedBy(listLikedBy);
                post.saveInBackground();
                tvLikeCount.setText(post.likeCountsDisplayText());
            });
        }

    }

    // Clean all elements of the recycler
    public void clear() {
        mPosts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> posts) {
        mPosts.addAll(posts);
        notifyDataSetChanged();
    }
}
