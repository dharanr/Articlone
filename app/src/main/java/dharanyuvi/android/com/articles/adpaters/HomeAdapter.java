package dharanyuvi.android.com.articles.adpaters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import dharanyuvi.android.com.articles.R;
import dharanyuvi.android.com.articles.models.TheHinduArticle;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private List<TheHinduArticle> list;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, description, time,label;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title1);
            description = (TextView) view.findViewById(R.id.description);
            time = (TextView) view.findViewById(R.id.time);
            label = (TextView) view.findViewById(R.id.label);
        }
    }

    @NonNull
    @Override
    public HomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.feed_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            TheHinduArticle theHinduArticle =list.get(position);
            holder.title.setText(theHinduArticle.getTitle());
            holder.description.setText(theHinduArticle.getDescription());
            holder.time.setText(theHinduArticle.getPubDate());
            holder.label.setText(theHinduArticle.getCategory());
    }

    public HomeAdapter(Context context,List<TheHinduArticle> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
