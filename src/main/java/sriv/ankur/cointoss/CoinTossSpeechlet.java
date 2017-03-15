package sriv.ankur.cointoss;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.Reprompt;
import com.amazon.speech.ui.SimpleCard;

import java.util.Random;

/**
 * Created by asriv on 3/14/17.
 */
public class CoinTossSpeechlet implements Speechlet {

    public void onSessionStarted(SessionStartedRequest request, Session session) throws SpeechletException { }

    public SpeechletResponse onLaunch(LaunchRequest request, Session session) throws SpeechletException {
        return getWelcomeResponse();
    }

    public SpeechletResponse onIntent(IntentRequest request, Session session) throws SpeechletException {
        Intent intent = request.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        if ("CoinToss".equals(intentName)) {
            return getCoinTossResponse();
        } else if ("AMAZON.HelpIntent".equals(intentName)) {
            return getHelpResponse();
        } else {
            throw new SpeechletException("Invalid Intent");
        }
    }

    public void onSessionEnded(SessionEndedRequest request, Session session) throws SpeechletException { }

    private SpeechletResponse getWelcomeResponse() {
        String speechText = "Hello, you can ask me for a coin toss";

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("CoinToss");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }

    private SpeechletResponse getCoinTossResponse() {
        Random random = new Random();
        String speechText = (random.nextBoolean() ? "HEADS" : "TAILS");

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("CoinToss");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return SpeechletResponse.newTellResponse(speech, card);
    }

    private SpeechletResponse getHelpResponse() {
        String speechText = "You can ask me for a coin toss!";

        // Create the Simple card content.
        SimpleCard card = new SimpleCard();
        card.setTitle("CoinToss");
        card.setContent(speechText);

        // Create the plain text output.
        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        // Create reprompt
        Reprompt reprompt = new Reprompt();
        reprompt.setOutputSpeech(speech);

        return SpeechletResponse.newAskResponse(speech, reprompt, card);
    }
}
