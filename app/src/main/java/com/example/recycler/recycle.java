package com.example.recycler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class recycle extends AppCompatActivity {

    FirebaseDatabase Mdatabase ;
    DatabaseReference Mreference ;
    RecyclerView recyclerView ;
    PostAdapter postAdapter;
    ArrayList<Post> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        recyclerView = findViewById(R.id.RCview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        Mreference=FirebaseDatabase.getInstance().getReference("users");

        posts=new ArrayList<>();




        Mreference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                     /*   final String getFN = dataSnapshot.child("first name").getValue(String.class);
                        final String getLN = dataSnapshot.child("last name").getValue(String.class);
                        posts.add(new Post(getFN,getLN) ); */
                        Post post = dataSnapshot.getValue(Post.class);
                        posts.add(post);

                    }
                postAdapter = new PostAdapter(recycle.this,posts);
                recyclerView.setAdapter(postAdapter);
                postAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

      /*  public void onItemClick (int position) {

            Intent intent = new Intent(recycle.this,activity.class);
            intent.putExtra("first name",posts.get(position).getTitle()+"");
            intent.putExtra("last name",posts.get(position).getDescription()+"");
            startActivity(intent);

        }
*/

}