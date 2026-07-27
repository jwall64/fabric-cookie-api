package dev.jwall64.cookieapi.impl;

import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;

import java.util.concurrent.CompletableFuture;

public class PlayerCookiesImpl {


    public static CompletableFuture<byte[]> getCookie(ServerPlayer player, Identifier location) {
        return ((CookieExtension) player.connection).requestCookie(location);
    }

    public static void setCookie(ServerPlayer player, Identifier location, byte[] cookie) {
        ((CookieExtension) player.connection).storeCookie(location, cookie);
    }

}
