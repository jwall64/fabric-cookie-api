package dev.jwall64.cookieapi.api;

import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import dev.jwall64.cookieapi.impl.PlayerCookiesImpl;

import java.util.concurrent.CompletableFuture;

public interface PlayerCookies {

    static CompletableFuture<byte[]> getCookie(ServerPlayer player, Identifier location) {
        return PlayerCookiesImpl.getCookie(player, location);
    }

    static void setCookie(ServerPlayer player, Identifier location, byte[] cookie) {
        PlayerCookiesImpl.setCookie(player, location, cookie);
    }

    static void clearCookie(ServerPlayer packetListener, Identifier location) {
        setCookie(packetListener, location, new byte[0]);
    }


}
