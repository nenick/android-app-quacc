package de.nenick.quacc.speechrecognition;

import android.content.Intent;
import android.speech.SpeechRecognizer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class SpeechRecognitionWrapperTest {

    @Mock
    Intent speechRecognizerIntent;


    @Mock
    SpeechRecognizer _speechRecognizer;


    @Mock
    SpeechListener recognitionListener;



    @InjectMocks
    SpeechRecognitionWrapper speechRecognitionWrapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldBeNotListeningAtDefault() throws Exception {
        assertThat(speechRecognitionWrapper.isListening()).isFalse();
    }

    @Test
    public void shouldToggleOnIfNotListening() throws Exception {
        speechRecognitionWrapper.toggle();
        assertThat(speechRecognitionWrapper.isListening()).isTrue();
        verify(_speechRecognizer).startListening(speechRecognizerIntent);
    }

    @Test
    public void shouldToggleOffIfListening() throws Exception {
        speechRecognitionWrapper.toggle();
        assertThat(speechRecognitionWrapper.isListening()).isTrue();
        speechRecognitionWrapper.toggle();
        assertThat(speechRecognitionWrapper.isListening()).isFalse();
        verify(_speechRecognizer).stopListening();
    }
}