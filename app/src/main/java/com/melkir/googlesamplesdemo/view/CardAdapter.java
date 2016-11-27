package com.melkir.googlesamplesdemo.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.melkir.googlesamplesdemo.R;
import com.melkir.googlesamplesdemo.activity.DetailActivity;
import com.melkir.googlesamplesdemo.model.Module;
import com.melkir.googlesamplesdemo.util.ActivityLauncher;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> implements Filterable {

    public static final String TAG = CardAdapter.class.getSimpleName();

    private final List<Module> mModules;
    private CardFilter cardFilter;

    /**
     * Provide a reference to the type of views we are using (custom ViewHolder)
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView picture;
        private final TextView name;
        private final TextView description;

        ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.card_title);
            picture = (ImageView) view.findViewById(R.id.card_image);
            description = (TextView) view.findViewById(R.id.card_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Context context = view.getContext();
                    final Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(DetailActivity.MODULE, mModules.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
            Button button = (Button) itemView.findViewById(R.id.action_launch);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityLauncher.start(view.getContext(),
                            mModules.get(getAdapterPosition()).getAction());
                }
            });
        }
    }

    /**
     * Adapter to display recycler view.
     */
    public CardAdapter(Context context) {
        Resources resources = context.getResources();
        final String[] mModulesTitle = resources.getStringArray(R.array.modules_title);
        final String[] mModulesDesc = resources.getStringArray(R.array.modules_desc);
        final String[] mModulesCategory = resources.getStringArray(R.array.modules_category);
        final String[] mModulesLink = resources.getStringArray(R.array.modules_link);
        final String[] mModulesAction = resources.getStringArray(R.array.modules_action);
        final TypedArray a = resources.obtainTypedArray(R.array.modules_picture);
        mModules = new ArrayList<>();
        for (int i = 0; i < mModulesTitle.length; ++i) {
            String[] categories = mModulesCategory[i].split(";");
            Module module = new Module(mModulesTitle[i], mModulesDesc[i], mModulesLink[i],
                    mModulesAction[i], categories, a.getResourceId(i, R.drawable.card_demo));
            mModules.add(module);
        }
        a.recycle();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Module module = mModules.get(position);
        holder.picture.setImageResource(module.getPicture());
        holder.name.setText(module.getTitle());
        holder.description.setText(module.getDescription());
    }

    @Override
    public int getItemCount() {
        return mModules.size();
    }

    @Override
    public Filter getFilter() {
        if (null == cardFilter) cardFilter = new CardFilter(this, mModules);
        return cardFilter;
    }

}
