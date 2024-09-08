package net.trevorskullcrafter.datagen.lang;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.data.DataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.StatType;
import net.minecraft.text.TextContent;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Identifier;
import net.trevorskullcrafter.TrevorsSentinels;
import net.trevorskullcrafter.util.JsonTextObject;

import java.awt.*;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

import static net.trevorskullcrafter.TrevorsSentinels.MOD_ID;
import static net.trevorskullcrafter.util.TextUtil.*;

public class OwoLanguageProvider extends FabricLanguageProvider {
	protected final FabricDataOutput dataOutput;
	private final String languageCode;

	protected OwoLanguageProvider(FabricDataOutput dataOutput, String languageCode, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
		super(dataOutput, languageCode, registryLookup);
		this.dataOutput = dataOutput;
		this.languageCode = languageCode;
	}

	@Override public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {}

	public void generateRichTranslations(OwoTranslationBuilder translationBuilder){}

	@Override public CompletableFuture<?> run(DataWriter writer) {
		TreeMap<String, JsonArray> translationEntries = new TreeMap<>();

		generateRichTranslations((String key, JsonArray jsonObject) -> {
			Objects.requireNonNull(key, "Attempt to index null translation key");
			if (jsonObject == null) { TrevorsSentinels.LOGGER.warn("Attempt to index null contents for '" + key + "' will be ignored."); }
			else if (translationEntries.containsKey(key)) { TrevorsSentinels.LOGGER.warn("Existing translation found for '" + key + "' - Duplicate will be ignored."); }
			else { translationEntries.put(key, jsonObject); }
		});

		JsonObject translationEntriesJson = new JsonObject();
		for (Map.Entry<String, JsonArray> entry : translationEntries.entrySet()) { translationEntriesJson.add(entry.getKey(), entry.getValue()); }

		return DataProvider.writeToPath(writer, translationEntriesJson, dataOutput
				.getResolver(DataOutput.OutputType.RESOURCE_PACK, "lang")
				.resolveJson(Identifier.of(dataOutput.getModId(), this.languageCode)));
	}

	@FunctionalInterface public interface OwoTranslationBuilder {
		void add(String translationKey, JsonArray value);
		default void add(String translationKey, JsonTextObject... values){ add(translationKey, jsonArrayOf(values)); }
		default void add(Translation... entries){
			for(Translation entry : entries){ if(entry.key != null){
				if(!entry.noName){
					if(entry.rawText == null){ if(entry.text == null){ entry.rawText = stringifyTranslationKey(entry.key); }
						else {
							StringBuilder builder = new StringBuilder();
							for(JsonTextObject object : entry.text){ builder.append(object.object.get("text")); }
							entry.rawText = builder.toString();
						}
					}

					JsonArray name = new JsonArray();
					if(entry.text == null){
						if(entry.textColor == null || entry.textColor.length == 1){ name = jsonArrayOf(new JsonTextObject(entry)); }
						else {
							int sectionSize = entry.rawText.length() / (entry.textColor.length - 1);
							for (int colorIndex = 0; colorIndex < entry.textColor.length - 1; colorIndex++) {
								String substring = entry.rawText.substring(sectionSize * colorIndex, sectionSize * (colorIndex + 1));
								for (int i = 0; i < substring.length(); i++) {
									float ratio = (float) i / (float) substring.length();
									int red = (int) (entry.textColor[colorIndex + 1].getRed() * ratio + entry.textColor[colorIndex].getRed() * (1 - ratio));
									int green = (int) (entry.textColor[colorIndex + 1].getGreen() * ratio + entry.textColor[colorIndex].getGreen() * (1 - ratio));
									int blue = (int) (entry.textColor[colorIndex + 1].getBlue() * ratio + entry.textColor[colorIndex].getBlue() * (1 - ratio));
									Color stepColor = new Color(red, green, blue);
									name.add(new JsonTextObject(entry).text(String.valueOf(substring.charAt(i))).color(stepColor).build());
								}
							}
						}
					} else { name = jsonArrayOf(entry.text); }
					add(entry.key, name);
				}

				if(entry instanceof Translation.ItemTranslation itemEntry){
					if (itemEntry.tooltip != null) { add(new Translation("tooltip." + entry.key).text(itemEntry.tooltip)); }
				} else if(entry instanceof Translation.EffectTranslation effectEntry){
					if(effectEntry.flavorText != null){ add(
							new Translation("tooltip." + effectEntry.key + ".applied").text(effectEntry.flavorText.get(0)),
							new Translation("tooltip." + effectEntry.key + ".removed").text(effectEntry.flavorText.get(1)),
							new Translation("tooltip." + effectEntry.key + ".description").text(effectEntry.flavorText.get(2)));
					}

					if(effectEntry.potion){ String key = entry.key.substring(("effect."+ MOD_ID).length()+1);
						add(new Translation("item.minecraft.potion.effect."+key+"_potion").text(entry.rawText + " Potion"),
								new Translation("item.minecraft.splash_potion.effect."+key+"_potion").text("Splash Potion of " + entry.rawText),
								new Translation("item.minecraft.lingering_potion.effect."+key+"_potion").text("Lingering Potion of " + entry.rawText),
								new Translation("item.minecraft.tipped_arrow.effect."+key+"_potion").text("Arrow of " + entry.rawText));
					}
				}
			}}
		}

		default <TL extends Translation> void addWithParent(TL parentEntry, TL... entries){
			for (TL entry : entries) { if(entry != null && entry.key != null) {
				if(entry instanceof Translation.ItemTranslation itemEntry && parentEntry instanceof Translation.ItemTranslation parentItemEntry && itemEntry.tooltip == null) { itemEntry.tooltip(parentItemEntry.tooltip); }
				if(entry.textColor == null) { entry.textColor(parentEntry.textColor); }
				if(entry.text == null && parentEntry.text != null) { entry.text(parentEntry.text); }
				else if(entry.rawText == null) { entry.text(parentEntry.rawText); }
				add(entry);
			}}
		}

		default void addNumbered(Translation parent, Translation... nums){
			for (int i = 0; i < nums.length; i++) { add(new Translation(parent.key+(i+1)).text(nums[i].text)); }
		}

		default void addAdvancement(String advancement, Translation title, Translation description) {
			title.key = "advancements." + MOD_ID + "." + advancement + ".title";
			description.key = "advancements." + MOD_ID + "." + advancement + ".desc";
			add(title, description);
		}

		default void addPottedPlant(ItemConvertible r, ItemConvertible p, String name, String tooltip, Color color){
			if(r!=null) add(new Translation.ItemTranslation(r).text(name).tooltip(tooltip).textColor(color));
			if(p!=null) add(new Translation.ItemTranslation(p).text("Potted "+name).tooltip(tooltip).textColor(color));
		}

		default void addMusicDisc(Translation entry, JsonArray desc, SoundEvent sound){
			add(entry);
			add(entry.key+".desc", desc);
			add(new Translation(sound).text(entry.rawText + " plays"));
		}

		default void add(StatType<?> statType, JsonArray value) {
			add("stat_type." + Objects.requireNonNull(Registries.STAT_TYPE.getId(statType)).toString().replace(':', '.'), value);
		}
		default void add(RegistryKey<ItemGroup> registryKey, JsonArray value) {
			final ItemGroup group = Registries.ITEM_GROUP.getOrThrow(registryKey);
			final TextContent content = group.getDisplayName().getContent();
			if (content instanceof TranslatableTextContent translatableTextContent) { add(translatableTextContent.getKey(), value); return; }
			throw new UnsupportedOperationException("Cannot add language entry for ItemGroup (%s) as the display text is not translatable.".formatted(group.getDisplayName().getString()));
		}
		default void add(Path existingLanguageFile) throws IOException {
			try (Reader reader = Files.newBufferedReader(existingLanguageFile)) {
				JsonObject translations = JsonParser.parseReader(reader).getAsJsonObject();

				for (String key : translations.keySet()) {
					add(key, translations.get(key).getAsJsonArray());
				}
			}
		}
	}
}
