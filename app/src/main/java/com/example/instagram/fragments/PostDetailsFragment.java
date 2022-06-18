package com.example.instagram.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.instagram.R;
import com.example.instagram.models.Post;
import com.parse.ParseFile;

import org.parceler.Parcel;
import org.parceler.Parcels;

public class PostDetailsFragment extends Fragment {

    private TextView tvPostDetailsDescription;
    private TextView tvRelativeTime;
    private ImageView ivPostDetailsImage;
    private View view;
    private Post post;

    public PostDetailsFragment() {
        // Required empty public constructor
    }

    public static PostFragment newInstance() {
        PostFragment fragment = new PostFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_post_details, container, false);
        post = Parcels.unwrap(getArguments().getParcelable("post"));
        initialize();
        bind();
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initialize() {
        tvPostDetailsDescription =  view.findViewById(R.id.tvPostDetailsDescription);
        ivPostDetailsImage = view.findViewById(R.id.ivPostDetailsImage);
        tvRelativeTime = view.findViewById(R.id.tvRelativeTime);
    }

    private void bind() {
        ParseFile image = post.getImage();
        if (image != null) {
            Glide.with(view).load(image.getUrl()).into(ivPostDetailsImage);
        }
        tvPostDetailsDescription.setText(post.getDescription());
        tvRelativeTime.setText(post.getRelativeTimeAgo(String.valueOf(post.getCreatedAt())));
    }


}