package com.zhelearn.mods.noteconverter;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class NoteConverterCommand {

    private static final Map<String, Integer> noteToNum = new HashMap<>();

    static {
        noteToNum.put("F#1", 0);
        noteToNum.put("G1", 1);
        noteToNum.put("G#1", 2);
        noteToNum.put("A1", 3);
        noteToNum.put("A#1", 4);
        noteToNum.put("B1", 5);
        noteToNum.put("C2", 6);
        noteToNum.put("C#2", 7);
        noteToNum.put("D2", 8);
        noteToNum.put("D#2", 9);
        noteToNum.put("E2", 10);
        noteToNum.put("F2", 11);
        noteToNum.put("F#2", 12);
        noteToNum.put("G2", 13);
        noteToNum.put("G#2", 14);
        noteToNum.put("A2", 15);
        noteToNum.put("A#2", 16);
        noteToNum.put("B2", 17);
        noteToNum.put("C3", 18);
        noteToNum.put("C#3", 19);
        noteToNum.put("D3", 20);
        noteToNum.put("D#3", 21);
        noteToNum.put("E3", 22);
        noteToNum.put("F3", 23);
        noteToNum.put("F#3", 24);
        noteToNum.put("G3", 25);
        noteToNum.put("G#3", 26);
        noteToNum.put("A3", 27);
        noteToNum.put("A#3", 28);
        noteToNum.put("B3", 29);
        noteToNum.put("C4", 30);
        noteToNum.put("C#4", 31);
        noteToNum.put("D4", 32);
        noteToNum.put("D#4", 33);
        noteToNum.put("E4", 34);
        noteToNum.put("F4", 35);
        noteToNum.put("F#4", 36);
        noteToNum.put("G4", 37);
        noteToNum.put("G#4", 38);
        noteToNum.put("A4", 39);
        noteToNum.put("A#4", 40);
        noteToNum.put("B4", 41);
        noteToNum.put("C5", 42);
        noteToNum.put("C#5", 43);
        noteToNum.put("D5", 44);
        noteToNum.put("D#5", 45);
        noteToNum.put("E5", 46);
        noteToNum.put("F5", 47);
        noteToNum.put("F#5", 48);
        noteToNum.put("G5", 49);
        noteToNum.put("G#5", 50);
        noteToNum.put("A5", 51);
        noteToNum.put("A#5", 52);
        noteToNum.put("B5", 53);
        noteToNum.put("C6", 54);
        noteToNum.put("C#6", 55);
        noteToNum.put("D6", 56);
        noteToNum.put("D#6", 57);
        noteToNum.put("E6", 58);
        noteToNum.put("F6", 59);
        noteToNum.put("F#6", 60);
        noteToNum.put("G6", 61);
        noteToNum.put("G#6", 62);
        noteToNum.put("A6", 63);
        noteToNum.put("A#6", 64);
        noteToNum.put("B6", 65);
        noteToNum.put("C7", 66);
        noteToNum.put("C#7", 67);
        noteToNum.put("D7", 68);
        noteToNum.put("D#7", 69);
        noteToNum.put("E7", 70);
        noteToNum.put("F7", 71);
        noteToNum.put("F#7", 72);

    }

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("noteconverter")
                .then(Commands.argument("note", StringArgumentType.string())
                        .executes(NoteConverterCommand::execute)));
        dispatcher.register(Commands.literal("nc")
                .then(Commands.argument("note", StringArgumentType.string())
                        .executes(NoteConverterCommand::execute)));
    }

    private static int execute(CommandContext<CommandSource> command) {
        String note = StringArgumentType.getString(command, "note").toUpperCase();
        Integer num = noteToNum.get(note);
        if (num == null) {
            command.getSource().sendSuccess(new StringTextComponent("Unknown note: " + note), false);
            return 1;
        }
        String boxType;
        if (num < 24) {
            boxType = "Lower 2 Octaves";
        } else if (num < 48) {
            boxType = "Normal";
        } else if (num < 72) {
            boxType = "Higher 2 Octaves";
        } else {
            boxType = "Higher 4 Octaves";
        }
        LocalDateTime now = LocalDateTime.now();
        String time = String.format("%02d:%02d:%02d", now.getHour(), now.getMinute(), now.getSecond());
        command.getSource().sendSuccess(new StringTextComponent(TextFormatting.GREEN + "Output time: " + time + TextFormatting.RESET + " " + TextFormatting.YELLOW + "Input note: " + note + TextFormatting.RESET + " " + TextFormatting.RED + "Result: " + (num % 24) + ", " + boxType), false);
        return 1;
    }
}
