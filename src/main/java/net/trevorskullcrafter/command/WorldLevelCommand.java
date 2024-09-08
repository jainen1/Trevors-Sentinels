package net.trevorskullcrafter.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.trevorskullcrafter.util.ServerState;

public class WorldLevelCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher, CommandRegistryAccess access, CommandManager.RegistrationEnvironment env) {
        dispatcher.register((CommandManager.literal("trevorssentinels:worldlevel"))
                .then(CommandManager.literal("get").requires((source) -> source.hasPermissionLevel(0)).executes(WorldLevelCommand::get))
                .requires((source) -> source.hasPermissionLevel(4))
                .then(CommandManager.literal("set")
                        .then(CommandManager.argument("level", IntegerArgumentType.integer(1))
                                .then(CommandManager.argument("broadcast", BoolArgumentType.bool())
                                        .executes(context -> set(context, IntegerArgumentType.getInteger(context, "level"), BoolArgumentType.getBool(context, "broadcast"))))))
                .then(CommandManager.literal("add")
                        .then(CommandManager.argument("level", IntegerArgumentType.integer(0))
                                .then(CommandManager.argument("broadcast", BoolArgumentType.bool())
                                        .executes(context -> add(context, IntegerArgumentType.getInteger(context, "level"), BoolArgumentType.getBool(context, "broadcast"))))))
                .then(CommandManager.literal("sub")
                        .then(CommandManager.argument("level", IntegerArgumentType.integer(0))
                                .then(CommandManager.argument("broadcast", BoolArgumentType.bool())
                                        .executes(context -> sub(context, IntegerArgumentType.getInteger(context, "level"), BoolArgumentType.getBool(context, "broadcast")))))));
    }

    private static int get(CommandContext<ServerCommandSource> context) {
        context.getSource().sendMessage(Text.empty().append(Text.literal("The current world level is "))
                .append(Text.literal(String.valueOf(ServerState.getServerState(context.getSource().getServer()).worldLevel)).formatted(Formatting.GOLD)));
        return 1;
    }

    private static int set(CommandContext<ServerCommandSource> context, int level, boolean broadcast) {
        ServerState serverState = ServerState.getServerState(context.getSource().getServer());
        int originalLevel = serverState.worldLevel; Text message;
        if(level > 0) {
            if(serverState.worldLevel == level){ message = Text.literal("World level unchanged!").formatted(Formatting.GRAY); }
            else { serverState.worldLevel = level; serverState.markDirty();
                message = Text.empty().append(Text.literal((serverState.worldLevel > originalLevel) ? "World level upgraded to " : "World level reverted to "))
                        .append(Text.literal(String.valueOf(serverState.worldLevel)).formatted(Formatting.GOLD)).append(Text.literal(" from "))
                        .append(Text.literal(String.valueOf(originalLevel)).formatted(Formatting.GRAY)).append(Text.literal("!"));
            }} else { message = Text.literal("World level cannot be below 1!").formatted(Formatting.GRAY); }
        if(broadcast) { for(ServerPlayerEntity player : context.getSource().getServer().getPlayerManager().getPlayerList()){ player.sendMessage(message, false); }}
        else { context.getSource().sendMessage(message); }
        return 1;
    }

    private static int add(CommandContext<ServerCommandSource> context, int level, boolean broadcast) {
        set(context, ServerState.getServerState(context.getSource().getServer()).worldLevel + level, broadcast);
        return 1;
    }

    private static int sub(CommandContext<ServerCommandSource> context, int level, boolean broadcast) {
        set(context, ServerState.getServerState(context.getSource().getServer()).worldLevel - level, broadcast);
        return 1;
    }
}
