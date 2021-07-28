package com.example.taskmanager;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private List<mytasks> localDataSet;
    private Context context;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView desc;
        private final FloatingActionButton delete;
        private final FloatingActionButton edit;
        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View
            desc = (TextView) view.findViewById(R.id.desc);
            title = (TextView) view.findViewById(R.id.title);
            delete = (FloatingActionButton) view.findViewById(R.id.button2);
            edit = (FloatingActionButton) view.findViewById(R.id.floatingActionButton3);
        }

        public TextView getTitle() {
            return title;
        }
        public TextView getDesc(){return desc;}
        public FloatingActionButton getDelete(){return delete;}
        public FloatingActionButton getEdit(){return edit;}
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView.
     */
    public CustomAdapter(List<mytasks> dataSet, Context context) {
        localDataSet = dataSet;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.my_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder,final int position ) {
        viewHolder.getTitle().setText(localDataSet.get(position).getTitle());
        viewHolder.getDesc().setText(localDataSet.get(position).getDescription());
        viewHolder.getTitle().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MainActivity3.class);
                intent.putExtra(MainActivity2.Extra, localDataSet.get(position).getTitle());
                intent.putExtra(MainActivity2.Extra2,localDataSet.get(position).getDescription());
                intent.putExtra(MainActivity2.Extra3, localDataSet.get(position).getId());
                context.startActivity(intent);
            }
        });
        viewHolder.getDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDbHandler db = new myDbHandler(v.getContext(),"Tasks",null,1);
                db.deleteTask(localDataSet.get(position));
                Intent intent = new Intent(context,MainActivity2.class);
                context.startActivity(intent);
            }
        });
        viewHolder.getEdit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,MainActivity3.class);
                intent.putExtra(MainActivity2.Extra, localDataSet.get(position).getTitle());
                intent.putExtra(MainActivity2.Extra2,localDataSet.get(position).getDescription());
                intent.putExtra(MainActivity2.Extra3, localDataSet.get(position).getId());
                context.startActivity(intent);
            }
        });
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

}
