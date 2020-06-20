import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laioffer.tinnews.R;
import com.laioffer.tinnews.model.Article;

import java.util.ArrayList;
import java.util.List;

public class SavedNewsAdapter extends RecyclerView.Adapter<SavedNewsAdapter.SavedNewsViewHolder> {
    public static class SavedNewsViewHolder extends RecyclerView.ViewHolder {
        interface OnClickListener{
            void onClick(Article article);
            void unLike(Article article);
        }

        private List<Article> articles = new ArrayList<>();

        private OnClickListener onClickListener;

        public void setArticles(List<Article> articles){
            this.articles.clear();
            @@ -24,6 +32,10 @@ public class SavedNewsAdapter extends RecyclerView.Adapter<SavedNewsAdapter.Save
            notifyDataSetChanged();
        }

        public void setOnClickListener(OnClickListener listener){
            onClickListener = listener;
        }

        @NonNull
        @Override
        public SavedNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewTypr){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_news_item, parent, false);
            return new SavedNewsViewHolder(view);
        }

        @NonNull
        @Override
        public void onBindViewHolder(@NonNull SavedNewsViewHolder holder, int position){
            Article article = articles.get(position);
            holder.author.setText(article.author);
            holder.description.setText(article.description);
            if(article.favorite){
                holder.icon.setImageResource(R.drawable.ic_favorite_black_24dp);
            }else{
                holder.icon.setImageResource(R.drawable.ic_favorite_border_black_24dp);
            }
            holder.icon.setOnClickListener(v -> {
                onClickListener.unLike(article);
            });
        }

        @Override
        public int getItemCount(){
            return articles.size();
        }

        public SavedNewsViewHolder(View itemView) {
            super(itemView);
            author = itemView.findViewById(R.id.author);
            description = itemView.findViewById(R.id.description);
            icon = itemView.findViewById(R.id.image);
        }
    }
}
