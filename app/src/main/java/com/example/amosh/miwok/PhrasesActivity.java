package com.example.amosh.miwok;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.amosh.android.miwok.Word;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
    private MediaPlayer mMediaPlayer;

    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {

        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);


        final ArrayList<com.amosh.android.miwok.Word> Phrases = new ArrayList<Word>();

        //Phrases.add("one");
        Phrases.add(new Word(
                "Where are you going?", "minto wuksus", R.raw.phrase_where_are_you_going));
        Phrases.add(new Word(
                "What is your name?", "tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        Phrases.add(new Word(
                "My name is...", "oyaaset", R.raw.phrase_my_name_is));
        Phrases.add(new Word(
                "How are you feeling?", "michәksәs?", R.raw.phrase_how_are_you_feeling));
        Phrases.add(new Word("I’m feeling good.", "kuchi achit", R.raw.phrase_im_feeling_good));
        Phrases.add(new Word(
                "Are you coming?", "әәnәs'aa?", R.raw.phrase_are_you_coming));
        Phrases.add(new Word(
                "Yes, I’m coming.", "hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        Phrases.add(new Word
                ("I’m coming.", "әәnәm", R.raw.phrase_im_coming));
        Phrases.add(new Word(
                "Let’s go.", "yoowutis", R.raw.phrase_lets_go));
        Phrases.add(new Word(
                "Come here.", "әnni'nem", R.raw.phrase_come_here));

        WordAdapter adapter =
                new WordAdapter(this, Phrases, R.color.category_phrases);

        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Get the {@link Word} object at the given position the user clicked on
                Word word = Phrases.get(position);
                // Create and setup the {@link MediaPlayer} for the audio resource associated
                // with the current word
                releaseMediaPlayer();

                mMediaPlayer = MediaPlayer.create(PhrasesActivity.this, word.getSoundResourceId());
                // Start the audio file
                mMediaPlayer.start();
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();

    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
        }


    }
}
