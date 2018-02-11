package com.example.senamit.bakingapp.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.senamit.bakingapp.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveVideoTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

/**
 * Created by senamit on 3/2/18.
 */

public class FragmentRecipeBakingProcess extends Fragment {

    private static final String LOG_TAG = FragmentRecipeBakingProcess.class.getSimpleName();
    private static final String SELECTED_POSITION = "selectedPosition";
    private static final String SEEKBAR_POSITION = "seekbarPosition";
    Context context;
    TextView text2;
    String clickItemIndex;
    String videoUrl;
    long seekbarPosition;
    SimpleExoPlayerView simpleExoPlayerView;
    SimpleExoPlayer player;

    public FragmentRecipeBakingProcess() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_baking_process, container, false);
//        context = container.getContext();
        text2 = rootView.findViewById(R.id.text2);
        simpleExoPlayerView = rootView.findViewById(R.id.simpleExoPlayerView);
        return rootView;
    }



    private void exoPlayerSetup(String videoUrl) {

        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveVideoTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        LoadControl loadControl = new DefaultLoadControl();
        player = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);
        simpleExoPlayerView.setPlayer(player);

        DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter();
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context, "exoplayertest"), defaultBandwidthMeter);
        if (TextUtils.isEmpty(videoUrl)) {
            return;
        }
        Uri uri = Uri.parse(videoUrl);
        Log.i(LOG_TAG, "the uri is " + uri);
//        if (TextUtils.isEmpty(uri.toString())){
//            Log.i(LOG_TAG, "inside the second uri method");
//        }

        MediaSource mediaSource = new ExtractorMediaSource(uri, dataSourceFactory,
                new DefaultExtractorsFactory(), null, null);
        player.seekTo(seekbarPosition);
        player.prepare(mediaSource);
        player.setPlayWhenReady(true);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        context = getContext();

        if (savedInstanceState != null) {
            videoUrl = savedInstanceState.getString(SEEKBAR_POSITION);
            seekbarPosition = savedInstanceState.getLong(SELECTED_POSITION);
            clickItemIndex=savedInstanceState.getString("key8", "no savedInstance");
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        text2.setText(clickItemIndex);
        exoPlayerSetup(videoUrl);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setClickItemIndex(String clickItemIndex, String videoUrl) {
        this.clickItemIndex = clickItemIndex;
        this.videoUrl = videoUrl;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (player != null) {
            seekbarPosition = player.getCurrentPosition();
            player.stop();
            player.release();
            player = null;
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SEEKBAR_POSITION, videoUrl);
        outState.putLong(SELECTED_POSITION, seekbarPosition);
        outState.putString("key8", clickItemIndex);
    }

    @Override
    public void setRetainInstance(boolean retain) {
        super.setRetainInstance(retain);

    }
}
