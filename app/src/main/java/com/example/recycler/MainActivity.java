package com.example.recycler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView ;
    private PostAdapter postAdapter;
    private ArrayList<Post> posts;
    EditText editText;
    Button btnDelet;
    FirebaseDatabase Mdatabase ;
    DatabaseReference Mreference ;
    FirebaseStorage Mstorage ;
    Button btnadd ;
    EditText edit1, edite2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.RV_posts);
        editText = findViewById(R.id.et_item);

        btnadd = findViewById(R.id.btn_enter);
        edit1 = findViewById(R.id.et_item);
        edite2 = findViewById(R.id.et_item2);

        posts=new ArrayList<>();

        Mdatabase=FirebaseDatabase.getInstance();
        Mreference=FirebaseDatabase.getInstance().getReference().child("student");
        Mstorage=FirebaseStorage.getInstance();


     /*   posts.add(new Post("Apple ","fruit"));
        posts.add(new Post("Banana","fruit"));
        posts.add(new Post("blueberry ","fruit"));
        posts.add(new Post("cherries ","fruit"));
        posts.add(new Post("durian ","fruit"));
        posts.add(new Post("grapes ","fruit"));
        posts.add(new Post("kiwi ","fruit"));
        posts.add(new Post("lemon ","fruit"));
        posts.add(new Post("mango ","fruit"));
        posts.add(new Post("orang ","fruit"));
        posts.add(new Post("passion ","fruit"));
        posts.add(new Post("pineapple ","fruit"));
        posts.add(new Post("strawberry ","fruit"));
        posts.add(new Post("watermelon ","fruit")); */


        postAdapter =new PostAdapter(this,posts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(postAdapter);




        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upload();

        }
        });


    }
              public void upload() {
                  String FT = edit1.getText().toString();
                  String LT = edite2.getText().toString();
                  Post post = new Post(FT, LT);

                  if (!FT.isEmpty() && !LT.isEmpty()) {
                      Mreference.addListenerForSingleValueEvent(new ValueEventListener() {
                          @Override
                          public void onDataChange(@NonNull DataSnapshot snapshot) {

                             /* String ref = Mreference.push().getKey();
                              Mreference.child(ref).child("first name").setValue(FT);
                              Mreference.child(ref).child("last name").setValue(LT);*/
                              Mreference.child("USERS").child("hh").setValue(new Post(post.Title,post.Description));
                              Toast.makeText(MainActivity.this, "succsufull", Toast.LENGTH_SHORT).show();
                              Intent intent = new Intent(MainActivity.this,recycle.class);
                              startActivity(intent);

                          }

                          @Override
                          public void onCancelled(@NonNull DatabaseError error) {
                              Toast.makeText(MainActivity.this, "Fail !", Toast.LENGTH_SHORT).show();

                          }
                      });



                  }
              }






    public void adding() {
        posts.add(new Post(editText.getText().toString(), "fruit"));
        postAdapter =new PostAdapter(this,posts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        editText.setText("");

    }


}