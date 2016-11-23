package com.melkir.texttospeech;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

/**
 * <p>Demonstrates text-to-speech (TTS). Please note the following steps:</p>
 * <p>
 * <ol>
 * <li>Construct the TextToSpeech object.</li>
 * <li>Handle initialization callback in the onInit method.
 * The activity implements TextToSpeech.OnInitListener for this purpose.</li>
 * <li>Call TextToSpeech.speak to synthesize speech.</li>
 * <li>Shutdown TextToSpeech in onDestroy.</li>
 * </ol>
 * <p>
 * <p>Documentation:
 * http://developer.android.com/reference/android/speech/tts/package-summary.html
 * </p>
 * <ul>
 */
public class TextToSpeechActivity extends Activity implements TextToSpeech.OnInitListener {

    private static final String TAG = "TextToSpeechDemo";

    private TextToSpeech mTts;
    private Button mRandomButton;
    private Button mSpeechButton;
    private EditText mTextFieldSpeech;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_to_speech);

        // Initialize text-to-speech. This is an asynchronous operation.
        // The OnInitListener (second argument) is called after initialization completes.
        mTts = new TextToSpeech(this, this); // TextToSpeech.OnInitListener

        mTextFieldSpeech = (EditText) findViewById(R.id.text_entry);
        mSpeechButton = (Button) findViewById(R.id.button_speech);

        // The button is disabled in the layout.
        // It will be enabled upon initialization of the TTS engine.
        mRandomButton = (Button) findViewById(R.id.random_button);

        mRandomButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sayHello();
            }
        });
    }

    @Override
    public void onPause() {
        // Don't forget to shutdown!
        if (mTts != null) {
            mTts.stop();
            mTts.shutdown();
        }

        super.onPause();
    }

    // Implements TextToSpeech.OnInitListener.
    public void onInit(int status) {
        // status can be either TextToSpeech.SUCCESS or TextToSpeech.ERROR.
        if (status == TextToSpeech.SUCCESS) {
            // Set preferred language to US english.
            // Note that a language may not be available, and the result will indicate this.
            int result = mTts.setLanguage(Locale.US);
            // Add available language to the spinner
            // Try this someday for some interesting results.
            // int result mTts.setLanguage(Locale.FRANCE);
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                // Lanuage data is missing or the language is not supported.
                Toast.makeText(this, "Language is not available.", Toast.LENGTH_LONG).show();
                Log.e(TAG, "Language is not available.");
            } else {
                // Check the documentation for other possible result codes.
                // For example, the language may be available for the locale,
                // but not for the specified country and variant.

                // The TTS engine has been successfully initialized.
                // Allow the user to press the button for the app to speak again.
                mRandomButton.setEnabled(true);
                mSpeechButton.setEnabled(true);
                // Greet the user.
                sayHello();
            }
        } else {
            // Initialization failed.
            Toast.makeText(this, "Could not initialize TextToSpeech.", Toast.LENGTH_LONG).show();
            Log.e(TAG, "Could not initialize TextToSpeech.");
        }
    }

    private static final Random RANDOM = new Random();
    private static final String[] HELLOS = {
            "Hello",
            "Salutations",
            "Greetings",
            "Howdy",
            "What's crack-a-lackin?",
            "That explains the stench!"
    };

    private void sayHello() {
        // Select a random hello.
        int helloLength = HELLOS.length;
        String hello = HELLOS[RANDOM.nextInt(helloLength)];
        mTts.speak(hello, TextToSpeech.QUEUE_FLUSH, null, null); // Drop all pending entries in the playback queue.
    }

    public void speechText(View view) {
        String toSpeak = mTextFieldSpeech.getText().toString();
        mTts.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, null);
    }
}