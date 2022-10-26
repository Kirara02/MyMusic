package com.kirara.mymusic;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MusicListAdapter extends RecyclerView.Adapter<MusicListAdapter.ViewHolder>{
    ArrayList<AudioModel> songList;
    Context context;
    Notification notificationManager;
    NotificationManager Manager;


    public MusicListAdapter(ArrayList<AudioModel> songList, Context context) {
        this.songList = songList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view,parent,false );
        return new MusicListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MusicListAdapter.ViewHolder holder, int position ) {
        AudioModel songData = songList.get(position);
        holder.titleText.setText(songData.title);

        if(MyMediaPlayer.currentIndex == position){
            holder.titleText.setTextColor(Color.parseColor("#FF0000"));
        }else{
            holder.titleText.setTextColor(Color.parseColor("#000000"));
        }

        holder.titleText.setOnClickListener(v->{
            //navigate to other activity
            Notification.createNotification(context, songList.get(1), R.drawable.ic_baseline_pause_circle_outline_24,1, songList.size() -1);
            MyMediaPlayer.getInstance().reset();
            MyMediaPlayer.currentIndex = holder.getAdapterPosition();
            Intent intent = new Intent(context,MusicPlayerActivity.class);
            intent.putExtra("List",songList);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView titleText;
        public ViewHolder(View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.icon_view);
            titleText = itemView.findViewById(R.id.music_title);
        }
    }


    private void createChannel() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(Notification.CHANNEL_ID,
                    "KOD Dev", NotificationManager.IMPORTANCE_LOW);
            Manager = context.getSystemService(NotificationManager.class);
            if(Manager != null){
                Manager.createNotificationChannel(notificationChannel);
            }
        }
    }
}
