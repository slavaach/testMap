package ru.e2k.chechina.xkeeperch.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

import java.util.List;

public interface JSONPlaceHolderApi {
    @GET("/posts/{id}")
    public Call<Post> getPostWithID(@Path("id") int id);

    @GET("/posts")
    public Call<List<Post>> getAllPosts();
}
