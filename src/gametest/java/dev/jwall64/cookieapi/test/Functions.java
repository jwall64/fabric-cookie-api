package dev.jwall64.cookieapi.test;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.execution.ExecutionContext;
import net.minecraft.commands.execution.Frame;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dev.jwall64.cookieapi.api.PlayerCookies;

import java.nio.charset.StandardCharsets;


public class Functions {

    private static final Logger log = LoggerFactory.getLogger(Functions.class);
    private static final Identifier COOKIE_ID = Identifier.tryBuild("cookie_api", "test");
    private static final Identifier COOKIE_ID_UNKNOWN = Identifier.tryBuild("cookie_api", "unknown");

    public static void test(CommandSourceStack css,
                            CompoundTag tag,
                            Identifier id,
                            CommandDispatcher<CommandSourceStack> dispatcher,
                            ExecutionContext<CommandSourceStack> ctx,
                            Frame frame,
                            Void data) throws CommandSyntaxException {


        ServerPlayer player = css.getPlayerOrException();

        String message = "Test message";
        player.sendSystemMessage(Component.literal("Setting cookie to \"" + message + "\""));
        PlayerCookies.setCookie(player, COOKIE_ID, message.getBytes(StandardCharsets.UTF_8));


        player.sendSystemMessage(Component.literal("Awaiting cookie response..."));
        PlayerCookies.getCookie(player, COOKIE_ID).thenAccept(c -> {
            try {
                player.sendSystemMessage(Component.literal("Received cookie with \"" + new String(c) + "\""));
            } catch (Throwable th) {
                log.error("An error occurred while handling cookie response", th);
                player.sendSystemMessage(Component.literal("Unable to handle cookie response"));
            }
        });

        player.sendSystemMessage(Component.literal("Requesting unknown cookie..."));
        PlayerCookies.getCookie(player, COOKIE_ID_UNKNOWN).thenAccept(c -> {
            if(c != null) {
                log.error("Expected a null response! Got {}", c);
                player.sendSystemMessage(Component.literal("Error: Received known cookie"));
            } else {
                player.sendSystemMessage(Component.literal("Received empty cookie"));
            }
        });

    }

}
