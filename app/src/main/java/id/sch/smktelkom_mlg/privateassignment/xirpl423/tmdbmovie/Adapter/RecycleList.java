package id.sch.smktelkom_mlg.privateassignment.xirpl423.tmdbmovie.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import id.sch.smktelkom_mlg.privateassignment.xirpl423.tmdbmovie.R;

public class RecycleList extends RecyclerView.Adapter<RecycleList.ViewHolder> {
    private final Context context;
    private List<TrailerList> urls = new ArrayList<TrailerList>();
    private Activity activity;

    public RecycleList(Context context, List<TrailerList> urls) {
        this.context = context;
        this.urls = urls;
    }

    public RecycleList(Context context, List<TrailerList> urls, Activity activity) {
        this.context = context;
        this.urls = urls;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.list_trailer, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final TrailerList trailerList = urls.get(position);
        holder.vTitle.setText(trailerList.getTitle());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(trailerList.getUrl())));
            }
        });
    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView vTitle;
        public View view;

        public ViewHolder(View v) {
            super(v);
            this.view = v;
            vTitle = (TextView) v.findViewById(R.id.txt_tittle);
        }


    }
}
