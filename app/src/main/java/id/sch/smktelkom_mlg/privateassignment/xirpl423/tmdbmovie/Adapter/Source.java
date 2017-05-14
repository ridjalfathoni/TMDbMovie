package id.sch.smktelkom_mlg.privateassignment.xirpl423.tmdbmovie.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl423.tmdbmovie.Model.Hasil;
import id.sch.smktelkom_mlg.privateassignment.xirpl423.tmdbmovie.R;

/**
 * Created by Ridjal Fathoni on 14/05/2017.
 */

public class Source extends RecyclerView.Adapter<Source.ViewHolder> {
    ArrayList<Hasil> list;
    ISource mISourceAdapter;
    Context context;

    public Source(Context context, ArrayList<Hasil> list) {
        this.list = list;
        this.context = context;
        mISourceAdapter = (Source.ISource) context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Hasil result = list.get(position);
        holder.tvName.setText(result.title);
        holder.tvDesc.setText(result.overview);
        holder.tvVote.setText(result.vote_average);
        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w500" + result.backdrop_path)
                .into(holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    public interface ISource {
        void showArticles(String title, String overview, String poster_path);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPoster;
        TextView tvName;
        TextView tvDesc;
        TextView tvVote;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPoster = (ImageView) itemView.findViewById(R.id.imageViewPoster);
            tvName = (TextView) itemView.findViewById(R.id.textViewName);
            tvDesc = (TextView) itemView.findViewById(R.id.textViewDesc);
            tvVote = (TextView) itemView.findViewById(R.id.textViewVote);
        }
    }
}
