package com.vnrvjiet.edcell.MBIC;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

/**
 * Created by baswarajmamidgi on 10/03/17.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {
    public Context context;
    private List<EventClass> workshopclassList;

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        MyViewHolder(View view){
            super(view);
            textView = (TextView) view.findViewById(R.id.details);


        }
    }
    public EventAdapter(Context context, List<EventClass> workshopclassList){
        this.context=context;
        this.workshopclassList=workshopclassList;

    }

    @Override
    public EventAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_event,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(final EventAdapter.MyViewHolder holder, int position) {
        final EventClass workshopclass=workshopclassList.get(position);
        /*Glide.with(context).load(workshopclass.getImageUrl())
                .placeholder(R.drawable.navicon)
                .into(holder.imageView);
                */


        holder.textView.setText(workshopclass.getTitle()+"\n");

        if(workshopclass.getContent()!=null) {
            holder.textView.append(workshopclass.getContent()+"\n");
            Linkify.addLinks(holder.textView, Linkify.ALL);

        }

       /* holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

           String url=workshopclass.getImageUrl();
                final Dialog dialog = new Dialog(context,R.style.Theme_Dialog);
                dialog.setContentView(R.layout.imageview);
                ImageView imageView = (ImageView) dialog.findViewById(R.id.imageView);
                ImageView cancel = (ImageView) dialog.findViewById(R.id.cancel);

                Glide.with(context).load(url)
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.noimage)
                        .into(imageView);


                dialog.show();
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });
        */

    }

    @Override
    public int getItemCount() {
        return workshopclassList.size();
    }

}

