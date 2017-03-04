package com.melkir.libraries.moduledetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.melkir.libraries.R;
import com.melkir.libraries.data.Module;
import com.melkir.libraries.util.ActivityLauncher;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

public class ModuleDetailFragment extends Fragment implements ModuleDetailContract.View {
    private ModuleDetailContract.Presenter mPresenter;
    @BindView(R.id.module_detail_title)
    TextView mDetailTitle;
    @BindView(R.id.module_detail_description)
    TextView mDetailDescription;
    @BindView(R.id.module_detail_link)
    TextView mDetailLink;

    private ImageView mDetailImage;

    public ModuleDetailFragment() {
        // Requires empty public constructor
    }

    public static ModuleDetailFragment newInstance() {
        return new ModuleDetailFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment, container, false);
        ButterKnife.bind(this, view);

        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab_action);
        fab.setOnClickListener(v -> mPresenter.startModule());

        mDetailImage = (ImageView) getActivity().findViewById(R.id.module_image);

        return view;
    }

    @Override
    public void setPresenter(ModuleDetailContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void showMissingModule() {
        mDetailTitle.setText("");
        mDetailDescription.setText("No data");
    }

    @Override
    public void showModule(Module module) {
        mDetailTitle.setText(module.getTitle());
        mDetailDescription.setText(module.getDescription());
        mDetailLink.setText(module.getLink());
        mDetailImage.setImageResource(module.getPicture());
    }

    @Override
    public void startModule(String action) {
        ActivityLauncher.start(getContext(), action);
    }
}
