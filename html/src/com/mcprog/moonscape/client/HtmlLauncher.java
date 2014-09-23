package com.mcprog.moonscape.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.mcprog.moonscape.Moonscape;
import com.mcprog.moonscape.utility.Constants;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(Constants.APP_WIDTH, Constants.APP_HEIGHT);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new Moonscape();
        }
}