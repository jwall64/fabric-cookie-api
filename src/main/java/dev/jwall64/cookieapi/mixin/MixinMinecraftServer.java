package dev.jwall64.cookieapi.mixin;

import com.mojang.datafixers.DataFixer;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.Services;
import net.minecraft.server.WorldStem;
import net.minecraft.server.level.progress.LevelLoadListener;
import net.minecraft.server.notifications.NotificationManager;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.world.level.storage.LevelStorageSource;
import net.minecraft.world.level.gamerules.GameRules;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import dev.jwall64.cookieapi.api.ConfigurationCookieEvents;
import dev.jwall64.cookieapi.impl.ServerExtension;

import java.util.Optional;
import java.net.Proxy;

@Mixin(MinecraftServer.class)
@Implements(@Interface(iface = ServerExtension.class, prefix = "cookieapi$"))
public class MixinMinecraftServer {

    @Unique
    private Event<ConfigurationCookieEvents.RequestCookies> cookieapi$serverEvent;

    @Inject(method="<init>", at=@At("TAIL"))
    private void onInit(Thread thread, LevelStorageSource.LevelStorageAccess levelStorageAccess, PackRepository packRepository, WorldStem worldStem, Optional<GameRules> gameRules, Proxy proxy, DataFixer dataFixer, Services services, LevelLoadListener levelListener, boolean propagatesCrashes, NotificationManager notificationManager, CallbackInfo ci) {
        cookieapi$serverEvent = EventFactory.createArrayBacked(ConfigurationCookieEvents.RequestCookies.class, listeners -> (server, packetListener) -> {
            for(ConfigurationCookieEvents.RequestCookies cookie : listeners) {
                cookie.request(server, packetListener);
            }
        });

    }

    public Event<ConfigurationCookieEvents.RequestCookies> cookieapi$getRequestCookiesEvent() {
        return cookieapi$serverEvent;
    }


}
