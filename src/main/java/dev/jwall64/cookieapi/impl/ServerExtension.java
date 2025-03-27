package dev.jwall64.cookieapi.impl;

import net.fabricmc.fabric.api.event.Event;
import dev.jwall64.cookieapi.api.ConfigurationCookieEvents;

public interface ServerExtension {

    Event<ConfigurationCookieEvents.RequestCookies> getRequestCookiesEvent();

}
