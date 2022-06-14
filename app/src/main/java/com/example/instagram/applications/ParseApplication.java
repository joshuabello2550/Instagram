package com.example.instagram.applications;

import android.app.Application;

import com.example.instagram.models.Post;
import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Register parse models
        ParseObject.registerSubclass(Post.class);

        // Initializes Parse SDK as soon as the application is created
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("uJlys8H1DLzMSDnjsUbj4dOBOIYsmdMST06r5JsE")
                .clientKey("ildFeEEc8JsgL29ZgFqS5OhrsVlhGrKSbjLzA6Rj")
                .server("https://parseapi.back4app.com")
                .build()
        );

    }

}
