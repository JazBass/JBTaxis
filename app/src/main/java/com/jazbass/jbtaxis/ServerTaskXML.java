package com.jazbass.jbtaxis;

import android.os.AsyncTask;

import org.xml.sax.InputSource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ServerTaskXML extends AsyncTask {

    ArrayList<Taxi> availableTaxis;
    URL url;

    public ServerTaskXML(String s) throws MalformedURLException {
        availableTaxis = new ArrayList<>();
        url = new URL(s);
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            InputSource inputSource = new InputSource(url.openStream());
            XMLAnalyzer xmlAnalyzer = new XMLAnalyzer(inputSource);
            availableTaxis = xmlAnalyzer.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        PrincipalActivity.availableTaxis = availableTaxis;
    }
}
