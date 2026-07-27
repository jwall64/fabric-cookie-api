package dev.jwall64.cookieapi.impl;

import net.minecraft.network.protocol.login.ServerLoginPacketListener;
import net.minecraft.resources.Identifier;

import java.util.concurrent.CompletableFuture;

public class LoginCookiesImpl {

    public static CompletableFuture<byte[]> getCookie(ServerLoginPacketListener packetListener, Identifier location) {
        return ((CookieExtension) packetListener).requestCookie(location);
    }

    public static void setCookie(ServerLoginPacketListener packetListener, Identifier location, byte[] cookie) {
        ((CookieExtension) packetListener).storeCookie(location, cookie);
    }
}
