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

public class FragmentRecipeBakingProcess extends Fragment{

    private static final String LOG_TAG = FragmentRecipeBakingProcess.class.getSimpleName();
    private static final String SELECTED_POSITION = "selectedPosition";
    Context context;
    TextView text2;
    String clickItemIndex;
    String videoUrl;
    long seekbarPosition;
    SimpleExoPlayerView simpleExoPlayerView;
    SimpleExoPlayer player;
    int test1= 5;



    public FragmentRecipeBakingProcess() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recipe_baking_process, container, false);
        context = container.getContext();
        text2= rootView.findViewById(R.id.text2);
         simpleExoPlayerView = rootView.findViewById(R.id.simpleExoPlayerView);


        Log.i(LOG_TAG, "inside the oncreate mehtod "+clickItemIndex);


        return  rootView;
    }

    private void exoPlayerSetup(String videoUrl) {

//        Handler mainHandler = new Handler();
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveVideoTrackSelection.Factory(bandwidthMeter);
        TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
        LoadControl loadControl = new DefaultLoadControl();
        player = ExoPlayerFactory.newSimpleInstance(context, trackSelector, loadControl);

        simpleExoPlayerView.setPlayer(player);


        DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter();
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context, Util.getUserAgent(context,"exoplayertest"), defaultBandwidthMeter);
        Log.i(LOG_TAG, "the video url is "+videoUrl);
        if (TextUtils.isEmpty(videoUrl)){
            return;
        }

        Uri uri = Uri.parse(videoUrl);
        Log.i(LOG_TAG, "the uri is "+uri);
//        if (TextUtils.isEmpty(uri.toString())){
//            Log.i(LOG_TAG, "inside the second uri method");
//              uri = Uri.parse("https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd97a_1-mix-marscapone-nutella-creampie/1-mix-marscapone-nutella-creampie.mp4");
//        }

        Log.i(LOG_TAG, "the seekbar postion inside function is before loading "+ seekbarPosition);

        MediaSource mediaSource = new ExtractorMediaSource(uri,dataSourceFactory,
                new DefaultExtractorsFactory(), null, null);
        Log.i(LOG_TAG, "the seekbar postion inside function is after loading "+ seekbarPosition);
        Log.i(LOG_TAG, "the test value is function"+test1);
        player.seekTo(seekbarPosition);
        player.prepare(mediaSource);
        player.setPlayWhenReady(true);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(LOG_TAG, "inisde on activity create "+clickItemIndex);

        if (savedInstanceState !=null){
            videoUrl = savedInstanceState.getString("key3");
            seekbarPosition = savedInstanceState.getLong(SELECTED_POSITION);
            test1= savedInstanceState.getInt("key4");
            Log.i(LOG_TAG, "the seekbar postion in onactivity created is "+seekbarPosition);
        }


    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(LOG_TAG, "inisde onstart method and video url is "+videoUrl);
        Log.i(LOG_TAG, "inside onstart the postion of video is "+seekbarPosition);
        text2.setText(clickItemIndex);
        exoPlayerSetup(videoUrl);
        Log.i(LOG_TAG, "the test value is onStart"+test1);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void setClickItemIndex(String clickItemIndex, String videoUrl) {
        this.clickItemIndex = clickItemIndex;
        this.videoUrl = videoUrl;
        Log.i(LOG_TAG, "the clickitemindext is "+this.clickItemIndex);
        Log.i(LOG_TAG, "the video url in setclickitemindex is "+videoUrl);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (player!=null){
            seekbarPosition =  player.getCurrentPosition();
            Log.i(LOG_TAG, "the seekbar postion in onPause is "+seekbarPosition);
            player.stop();
            player.release();
            player =null;
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("key3", videoUrl);
        outState.putLong(SELECTED_POSITION, seekbarPosition);
        outState.putInt("key4", 8888);
        Log.i(LOG_TAG, "inside Onnnnnnnnnnnnnnnnnnnnsaveinstance state");

    }


}
