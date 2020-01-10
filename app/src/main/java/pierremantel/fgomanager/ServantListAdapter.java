package pierremantel.fgomanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ServantListAdapter extends RecyclerView.Adapter<ServantListAdapter.ServantViewHolder> {
    class ServantViewHolder extends RecyclerView.ViewHolder {
        private final TextView servant_nameTView;
        private final TextView servant_rarityTView;
        private final TextView servant_idTView;
        private final ImageView servant_iconIView;

        private ServantViewHolder(View itemView) {
            super(itemView);
            servant_iconIView = itemView.findViewById(R.id.servantIcon);
            servant_idTView = itemView.findViewById(R.id.servantID);
            servant_nameTView = itemView.findViewById(R.id.servantName);
            servant_rarityTView = itemView.findViewById(R.id.servantRarity);
        }
    }

    private final LayoutInflater mInflater;
    private List<Servant> mServants; // Cached copy of servants

    ServantListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ServantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.servant_list_item, parent, false);
        return new ServantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ServantViewHolder holder, int position) {
        if (mServants != null) {
            Servant current = mServants.get(position);
            holder.servant_nameTView.setText(current.getName());
            holder.servant_rarityTView.setText(current.getRarity());
            String id = String.valueOf(current.getId());
            holder.servant_idTView.setText(id);
            String url = current.getIconUrl()   ;
            ImageView imgview = holder.servant_iconIView;
            ImageLoadTask loadTask = new ImageLoadTask(url, imgview);
            loadTask.execute();

        } else {
            // Covers the case of data not being ready yet.
            holder.servant_nameTView.setText("No data");
        }
    }

    void setServants(List<Servant> servants) {
        mServants = servants;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mServants != null)
            return mServants.size();
        else return 0;
    }
}
