package net.trevorskullcrafter.util;

import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class Pillars {
	public record Pillar(String name, Color... color) {
		public Text tooltipText(@Nullable World world){
			Color currentColor = color()[0];
			if(world != null){ currentColor = color()[(int) (world.getTime() % color().length)]; }
			return TextUtil.coloredText(Text.translatable("pillar.trevorssentinels." + name().toLowerCase()).formatted(Formatting.ITALIC), currentColor);
		}
	}

	public static final Pillar CERSA = new Pillar("Cersa", TextUtil.RED);
	public static final Pillar SPEHA = new Pillar("Speha", TextUtil.GOLD);
	public static final Pillar ONODE = new Pillar("Onode", TextUtil.YELLOW);
	public static final Pillar BYNDI = new Pillar("Byndi", TextUtil.GREEN);
	public static final Pillar FOLJA = new Pillar("Folja", TextUtil.AQUA);
	public static final Pillar TSANE = new Pillar("Tsane", TextUtil.TRANQUIL);

	public static final Pillar POWER = new Pillar("Power", TextUtil.BLOOD_RED, TextUtil.RED, TextUtil.GOLD, TextUtil.YELLOW, TextUtil.GOLD);
	public static final Pillar PURITY = new Pillar("Power", TextUtil.WHITE);
}
