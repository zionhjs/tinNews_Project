package com.laioffer.tinnews.ui.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.laioffer.tinnews.model.Article;

import androidx.recyclerview.widget.RecyclerView;

import com.laioffer.tinnews.R;
import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.List;

public class SearchNewsAdapter extends
        RecyclerView.Adapter<SearchNewsAdapter.SearchNewsViewHolder>{
    private List<Article> articles = new LinkedList<>();

    @NonNull
    @Override
    public SearchNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View)LayoutInflater.from(parent.getContext()).inflate(R.layout.search_news_item, parent, false);
        return new SearchNewsViewHolder(view);
    }

    public void setArticles(List<Article> newsList){
        this.articles.clear();
        this.articles.addAll(newsList);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull SearchNewsViewHolder holder, int position) {
        Article article = articles.get(position);
        if(article.urlToImage == null){
            holder.newsImage.setImageResource(R.drawable.ic_empty_image);
        }else{
            Picasso.get().load(article.urlToImage).into(holder.newsImage);
        }

        if(article.favorite){
            holder.favorite.setImageResource(R.drawable.ic_favorite_black_24dp);
            holder.favorite.setOnClickListener(null);
        }else{
            holder.favorite.setImageResource(R.drawable.ic_favorite_black_24dp);
            holder.favorite.setOnClickListener(
                    v -> {
                        article.favorite = true;
                        likeListener.onLike(article);
                    });
        }
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public static class SearchNewsViewHolder extends RecyclerView.ViewHolder{
        ImageView newsImage;
        ImageView favorite;
        public SearchNewsViewHolder(@NonNull View itemView){
            super((android.view.View) itemView);
            newsImage = ((android.view.View) itemView).findViewById(R.id.image);
            favorite = ((android.view.View) itemView).findViewById(R.id.favorite);
        }
    }
}
