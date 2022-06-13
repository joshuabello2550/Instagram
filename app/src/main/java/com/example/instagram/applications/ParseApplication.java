package com.example.instagram.applications;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {

    // Initializes Parse SDK as soon as the application is created
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("uJlys8H1DLzMSDnjsUbj4dOBOIYsmdMST06r5JsE")
                .clientKey("ildFeEEc8JsgL29ZgFqS5OhrsVlhGrKSbjLzA6Rj")
                .server("https://parseapi.back4app.com")
                .build()
        );

    }

}
