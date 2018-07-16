package dharanyuvi.android.com.articles.adpaters;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import dharanyuvi.android.com.articles.Article_Webview;
import dharanyuvi.android.com.articles.MainActivity;
import dharanyuvi.android.com.articles.R;
import dharanyuvi.android.com.articles.click;
import dharanyuvi.android.com.articles.models.TheHinduArticle;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private List<TheHinduArticle> list;
    private Context context;
    private AlertDialog dialog;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView title, description, time,label;
        public Button save;
        private click clickListener;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title1);
            description = (TextView) view.findViewById(R.id.description);
            time = (TextView) view.findViewById(R.id.time);
            label = (TextView) view.findViewById(R.id.label);
            save =(Button)view.findViewById(R.id.save);

            view.setTag(view);
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
            save.setOnClickListener(this);
        }
        public void setClickListener(click itemClickListener) {
            this.clickListener = itemClickListener;
        }
        @Override
        public void onClick(View v) {

            if(v.getId()==save.getId())
            {
                Toast.makeText(context,"Available from the stable version",Toast.LENGTH_LONG).show();
            }
            else
                clickListener.onClick(v,getAdapterPosition(),getItemId(),false);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onClick(v, getAdapterPosition(),getItemId(),true);
            return true;
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

        holder.setClickListener(new click() {
            @Override
            public void onClick(View view, int position,long id, Boolean longClick) {
                //Toast.makeText(context,position+"   "+longClick,Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(context,Article_Webview.class);
                    intent.putExtra("Bhaskar",false+"");
                    intent.putExtra("ETNow",false+"");
                    intent.putExtra("DinaMani",false+"");


                if((list.get(position).getCategory().equalsIgnoreCase("Bhaskar -  editorial")))
                    {
                        intent.putExtra("Bhaskar",true+"");
                    }
                    else if(list.get(position).getCategory().equalsIgnoreCase("ETNow -  opinion"))
                    {
                        intent.putExtra("ETNow",true+"");
                    }
                    else if(list.get(position).getCategory().equalsIgnoreCase("DinaMani -  editorial"))
                    {
                        intent.putExtra("DinaMani",true+"");
                    }

                        intent.putExtra("URL",list.get(position).getLink());
                    context.startActivity(intent);

            }
        });
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

