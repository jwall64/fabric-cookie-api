package dev.jwall64.cookieapi.impl;

import net.minecraft.resources.Identifier;

import java.util.concurrent.CompletableFuture;

public interface CookieExtension {

    CompletableFuture<byte[]> requestCookie(Identifier location);

    boolean cookieReceived(Identifier location, byte[] cookie);

    void storeCookie(Identifier location, byte[] cookie);

}
