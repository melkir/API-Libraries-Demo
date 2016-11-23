package com.melkir.googlesamplesdemo.view;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.melkir.googlesamplesdemo.util.ActivityLauncher;
import com.melkir.googlesamplesdemo.R;
import com.melkir.googlesamplesdemo.activity.DetailActivity;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    public static final String TAG = CardAdapter.class.getSimpleName();

    /**
     * Provide a reference to the type of views we are using (custom ViewHolder)
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView picture;
        private final TextView name;
        private final TextView description;

        ViewHolder(View view) {
            super(view);
            picture = (ImageView) view.findViewById(R.id.card_image);
            name = (TextView) view.findViewById(R.id.card_title);
            description = (TextView) view.findViewById(R.id.card_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Context context = view.getContext();
                    final Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra(DetailActivity.EXTRA_POSITION, getAdapterPosition());
                    context.startActivity(intent);
                }
            });
            Button button = (Button) itemView.findViewById(R.id.action_launch);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityLauncher.start(view.getContext(), getAdapterPosition());
                }
            });
        }
    }

    private final String[] mModules;
    private final String[] mModuleDesc;
    private final Drawable[] mModulePictures;

    /**
     * Adapter to display recycler view.
     */
    public CardAdapter(Context context) {
        Resources resources = context.getResources();
        mModules = resources.getStringArray(R.array.modules);
        mModuleDesc = resources.getStringArray(R.array.modules_desc);
        TypedArray a = resources.obtainTypedArray(R.array.modules_picture);
        mModulePictures = new Drawable[a.length()];
        for (int i = 0; i < mModulePictures.length; i++) {
            mModulePictures[i] = a.getDrawable(i);
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
        holder.picture.setImageDrawable(mModulePictures[position % mModulePictures.length]);
        holder.name.setText(mModules[position % mModules.length]);
        holder.description.setText(mModuleDesc[position % mModuleDesc.length]);
    }

    @Override
    public int getItemCount() {
        return mModules.length;
    }

}
