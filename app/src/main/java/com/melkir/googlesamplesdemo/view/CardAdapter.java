package com.melkir.googlesamplesdemo.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;

import com.melkir.googlesamplesdemo.BR;
import com.melkir.googlesamplesdemo.R;
import com.melkir.googlesamplesdemo.model.Module;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardViewHolder> implements Filterable {

    public static final String TAG = CardAdapter.class.getSimpleName();

    private List<Module> mModules;
    private final CardFilter cardFilter;

    /**
     * Adapter to display recycler view.
     */
    public CardAdapter(Context context) {
        this.mModules = initModules(context);
        this.cardFilter = new CardFilter(this, mModules);
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        Module module = mModules.get(position);
        holder.getBinding().setVariable(BR.module, module);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mModules.size();
    }

    @Override
    public Filter getFilter() {
        return cardFilter;
    }

    public void setList(List<Module> list) {
        this.mModules = list;
    }

    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

    private List<Module> initModules(Context context) {
        List<Module> modules = new ArrayList<>();
        Resources resources = context.getResources();
        final String[] mModulesTitle = resources.getStringArray(R.array.modules_title);
        final String[] mModulesDesc = resources.getStringArray(R.array.modules_desc);
        final String[] mModulesCategory = resources.getStringArray(R.array.modules_category);
        final String[] mModulesLink = resources.getStringArray(R.array.modules_link);
        final String[] mModulesAction = resources.getStringArray(R.array.modules_action);
        final TypedArray a = resources.obtainTypedArray(R.array.modules_picture);
        for (int i = 0; i < mModulesTitle.length; ++i) {
            String[] categories = mModulesCategory[i].split(";");
            Module module = new Module(mModulesTitle[i], mModulesDesc[i], mModulesLink[i],
                    mModulesAction[i], categories, a.getResourceId(i, R.drawable.card_demo));
            modules.add(module);
        }
        a.recycle();
        return modules;
    }

}
