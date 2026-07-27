package dev.jwall64.cookieapi.api;

import net.minecraft.network.protocol.configuration.ServerConfigurationPacketListener;
import net.minecraft.resources.Identifier;
import dev.jwall64.cookieapi.impl.ConfigurationCookiesImpl;

import java.util.concurrent.CompletableFuture;

public interface ConfigurationCookies {

    static CompletableFuture<byte[]> getCookie(ServerConfigurationPacketListener packetListener, Identifier location) {
        return ConfigurationCookiesImpl.getCookie(packetListener, location);
    }

    static void setCookie(ServerConfigurationPacketListener packetListener, Identifier location, byte[] cookie) {
        ConfigurationCookiesImpl.setCookie(packetListener, location, cookie);
    }

    static void clearCookie(ServerConfigurationPacketListener packetListener, Identifier location) {
        setCookie(packetListener, location, new byte[0]);
    }

}
