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
import android.widget.Toast;

import com.melkir.accelerometerplay.AccelerometerPlayActivity;
import com.melkir.googlesamplesdemo.DetailActivity;
import com.melkir.googlesamplesdemo.R;
import com.melkir.materialdesigncodelab.MaterialDesignActivity;
import com.melkir.ourstreets.activity.OurStreetsActivity;
import com.melkir.texttospeech.TextToSpeechActivity;
import com.melkir.vision.barcodereader.BarcodeReaderActivity;
import com.melkir.vision.facetracker.FaceTrackerActivity;
import com.melkir.vision.googlyeyes.GooglyEyesActivity;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private static final String TAG = CardAdapter.class.getSimpleName();

    /**
     * Provide a reference to the type of views we are using (custom ViewHolder)
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView picture;
        private final TextView name;
        private final TextView description;
        private final List<Class> activity = getActivityList();

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
                    final Context context = view.getContext();
                    final int position = getAdapterPosition();
                    if (position <= activity.size() - 1) {
                        context.startActivity(new Intent(context, activity.get(position)));
                    } else {
                        Toast.makeText(context, "Not implemented yet", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        private List<Class> getActivityList() {
            List<Class> activityList = new ArrayList<>();
            activityList.add(BarcodeReaderActivity.class);
            activityList.add(FaceTrackerActivity.class);
            activityList.add(GooglyEyesActivity.class);
            activityList.add(AccelerometerPlayActivity.class);
            activityList.add(OurStreetsActivity.class);
            activityList.add(MaterialDesignActivity.class);
            activityList.add(TextToSpeechActivity.class);
            return activityList;
        }

    }

    private final String[] mModules;
    private final String[] mModuleDesc;
    private final Drawable[] mModulePictures;

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
