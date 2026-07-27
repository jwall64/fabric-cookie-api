package dev.jwall64.cookieapi.impl;


import net.fabricmc.fabric.api.event.Event;
import net.minecraft.network.protocol.configuration.ServerConfigurationPacketListener;
import net.minecraft.resources.Identifier;
import net.minecraft.server.MinecraftServer;
import dev.jwall64.cookieapi.api.ConfigurationCookieEvents;

import java.util.concurrent.CompletableFuture;

public class ConfigurationCookiesImpl {

    public static CompletableFuture<byte[]> getCookie(ServerConfigurationPacketListener packetListener, Identifier location) {
        return ((CookieExtension) packetListener).requestCookie(location);
    }

    public static void setCookie(ServerConfigurationPacketListener packetListener, Identifier location, byte[] cookie) {
        ((CookieExtension) packetListener).storeCookie(location, cookie);
    }

    public static Event<ConfigurationCookieEvents.RequestCookies> getServerRequestCookiesEvent(MinecraftServer server) {
        return ((ServerExtension) server).getRequestCookiesEvent();
    }
}
