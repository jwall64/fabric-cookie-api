package dev.jwall64.cookieapi.api;

import net.minecraft.network.protocol.login.ServerLoginPacketListener;
import net.minecraft.resources.Identifier;
import dev.jwall64.cookieapi.impl.LoginCookiesImpl;

import java.util.concurrent.CompletableFuture;

public interface LoginCookies {

    static CompletableFuture<byte[]> getCookie(ServerLoginPacketListener packetListener, Identifier location) {
        return LoginCookiesImpl.getCookie(packetListener, location);
    }

    static void setCookie(ServerLoginPacketListener packetListener, Identifier location, byte[] cookie) {
        LoginCookiesImpl.setCookie(packetListener, location, cookie);
    }

    static void clearCookie(ServerLoginPacketListener packetListener, Identifier location) {
        setCookie(packetListener, location, new byte[0]);
    }

}
