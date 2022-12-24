package com.example.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.zip.Inflater;



public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{


    Context context ;
    List<Post> posts;
    DatabaseReference Mreference;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,desc ;
        ImageView imageView;
        Button btnAdd,btnDelet,btnadd ;

        public ViewHolder(View itemView){
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.title);
            desc = (TextView) itemView.findViewById(R.id.desc);
            btnAdd = itemView.findViewById(R.id.addbtn);

            btnadd = itemView.findViewById(R.id.btn_enter);

        }
    }


    public PostAdapter(Context c, List<Post> postList){
      this.context=c;
     this.posts=postList;
     Mreference= FirebaseDatabase.getInstance().getReference();
    }


    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.items_post,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapter.ViewHolder holder, int position) {
            Post p = posts.get(position);
         holder.title.setText(p.getTitle());
        holder.desc.setText(p.getDescription());
        final String txt =p.getTitle();


        final int myindex =position;

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posts.add(myindex,new Post("Apple ","fruit"));
                notifyDataSetChanged();
            }
        });

        holder.btnDelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posts.remove(myindex);
                notifyItemRemoved(myindex);
                notifyItemRangeRemoved(myindex,getItemCount());
            }
        });




    }

    @Override
    public int getItemCount() {
        return posts.size();
    }
}
